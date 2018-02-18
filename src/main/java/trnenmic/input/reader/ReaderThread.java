package trnenmic.input.reader;

import trnenmic.exceptions.WrongFormatException;
import trnenmic.input.currencyamount.parser.BaseLineParser;
import trnenmic.input.currencyamount.parser.LineParserFactory;
import trnenmic.manager.callback.InputDBCallback;
import trnenmic.output.printer.DefaultConsolePrinter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderThread extends Thread {

    private AtomicBoolean shouldExit;
    private InputDBCallback dbCallback;
    private File inputFile;
    private DefaultConsolePrinter printer;
    private BufferedReader reader;
    private final static Logger LOGGER = Logger.getLogger(ReaderThread.class.getName());
    private String FILE_NAME = "";
    private boolean IS_DEBUG = false;

    public ReaderThread(DefaultConsolePrinter printer, AtomicBoolean shouldExit, InputDBCallback dbCallback, String[] args) {
        this.printer = printer;
        this.dbCallback = dbCallback;
        this.shouldExit = shouldExit;
        if (args.length > 0) {
            inputFile = new File(args[0]);
        }

    }

    public void run() {
        try {
            //Reads and inserts exchange rates to the DB via InputDBCallback
            ExchangeRateFileReader reader = new ExchangeRateFileReader(printer, dbCallback);
            reader.readEntries();

            //Reading from file (defined as application argument) and increases amounts in DB
            readFileInput();

            //Reads users' input
            readConsoleInput();
        } catch (Exception e) {
            printer.print("-- Ups. Something went wrong. Please contact an IT guy. --");
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void readFileInput() throws IOException {
        if (inputFile != null) {
            if (inputFile.exists()) {
                try {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8));
                    String line = "";
                    int lineNumber = 0;
                    while ((line = reader.readLine()) != null) {
                        lineNumber++;
                        parse(line, lineNumber);
                    }
                } finally {
                    if (reader != null) {
                        reader.close();
                    }
                }
            } else {
                printer.print("-- File not found --");
            }
        }

    }

    private void readConsoleInput() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        int constantLineNumber = 0;
        while (!shouldExit.get()) {
            String line = reader.readLine();
            parse(line, constantLineNumber);
        }
    }

    private void parse(String line, int lineNumber) {
        BaseLineParser parser = LineParserFactory.createDefault(line);
        try {
            switch (parser.getLineType()) {
                case STANDARD:
                    dbCallback.increaseAmount(parser.getCurrencyCode(), parser.getAmount());
                    if (lineNumber != 0) {
                        printer.print("Inserted from file (line " + lineNumber + "): "
                                + parser.getCurrencyCode() + " " + parser.getAmount());
                    }
                    break;
                case QUIT:
                    if (lineNumber != 0) {
                        this.shouldExit.set(true);
                    }
                    break;
                case FORMAT_ERROR:
                    if (lineNumber == 0) {
                        printer.print(parser.getFailureMessage());
                    } else {
                        printer.print("-- Wrong format of input file at line " + lineNumber + " --");
                    }
                    break;
                default:
                    break;
            }
        } catch (WrongFormatException e) {
            if (lineNumber == 0) {
                printer.print("-- Ups. Something went wrong. Please contact an IT guy. --");
            } else {
                printer.print("-- Wrong format of input file at line " + lineNumber + " --");
            }
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
