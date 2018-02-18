package trnenmic.manager;

import trnenmic.input.reader.ReaderThread;
import trnenmic.manager.callback.InputDBCallback;
import trnenmic.manager.callback.OutputDBCallback;
import trnenmic.output.PrinterThread;
import trnenmic.output.printer.ConsolePrinterFactory;
import trnenmic.output.printer.DefaultConsolePrinter;

import java.util.concurrent.atomic.AtomicBoolean;

public class IOManager {

    private AtomicBoolean shouldExit;
    private String[] args;

    private ReaderThread readerThread;
    private PrinterThread printerThread;
    private DefaultConsolePrinter printer;
    private MainManager manager;


    private IOManager(String[] args, MainManager manager) {
        this.manager = manager;
        this.args = args;
    }

    public static IOManager init(String[] args, MainManager manager) {
        IOManager ioManager = new IOManager(args, manager);
        ioManager.init();
        return ioManager;
    }

    private void init() {
        shouldExit = new AtomicBoolean(false);
        printer = ConsolePrinterFactory.createDefaultConsolePrinter();
        readerThread = new ReaderThread(printer, shouldExit, new InputDBCallback(this), args);
        printerThread = new PrinterThread(printer, shouldExit, new OutputDBCallback(this));

        Thread t1 = new Thread(readerThread);
        Thread t2 = new Thread(printerThread);

        t1.start();
        t2.start();
    }

    public void increaseAmount(String code, long amount) {
        manager.increaseAmount(code, amount);
    }

    public void putRate(String codeFrom, String codeTo, double rate) {
        manager.putRate(codeFrom, codeTo, rate);
    }

    public String retrieveDBContent(String exchangeCode) {
        return manager.retrieveDBContent(exchangeCode);
    }

    public void print(String message) {
        printer.print(message);
    }
}
