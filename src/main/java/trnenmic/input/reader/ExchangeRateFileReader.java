package trnenmic.input.reader;

import trnenmic.exceptions.WrongFormatException;
import trnenmic.input.exchangerate.parser.BaseRateParser;
import trnenmic.input.exchangerate.parser.RateParserFactory;
import trnenmic.manager.callback.InputDBCallback;
import trnenmic.output.printer.DefaultConsolePrinter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExchangeRateFileReader {


    private static final Logger LOGGER = Logger.getLogger(ExchangeRateFileReader.class.getName());

    private static final String EXCHANGE_RATE_FILE = "exchange_rate.txt";

    private InputDBCallback dbCallback;
    private DefaultConsolePrinter printer;
    private File file;


    public ExchangeRateFileReader(DefaultConsolePrinter printer, InputDBCallback dbCallback) {
        this.printer = printer;
        this.dbCallback = dbCallback;
        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(classLoader.getResource(EXCHANGE_RATE_FILE).getFile());
    }

    public void readEntries() throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String line = "";
            while ((line = reader.readLine()) != null) {
                BaseRateParser parser = RateParserFactory.createDefault(line);
                try {
                    if (parser.isValid()) {
                        dbCallback.putRate(parser.getFromCurrencyCode(), parser.getToCurrencyCode(), parser.getRate());
                    } else {
                        parser.getFailureMessage();
                    }
                } catch (WrongFormatException e) {
                    printer.print("-- Ups. Something went wrong. Please contact an IT guy. --");
                    LOGGER.log(Level.SEVERE, e.getMessage(), e);
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }

        }

    }
}
