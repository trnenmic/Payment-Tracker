package trnenmic.manager;

import trnenmic.input.reader.ReaderThread;
import trnenmic.manager.callback.InputDBCallback;
import trnenmic.manager.callback.OutputDBCallback;
import trnenmic.output.PrinterThread;
import trnenmic.output.printer.ConsolePrinterFactory;
import trnenmic.output.printer.DefaultConsolePrinter;

public class IOManager {

    private String[] args;

    private Thread readerThread;
    private Thread printerThread;
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
        printer = ConsolePrinterFactory.createDefaultConsolePrinter();
        ReaderThread rThread = new ReaderThread(printer, new InputDBCallback(this), args);
        PrinterThread pThread = new PrinterThread(printer, new OutputDBCallback(this));

        printerThread = new Thread(pThread);
        readerThread = new Thread(rThread);

        printerThread.start();
        readerThread.start();
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

    public void shutDown() {
        printerThread.interrupt();
        readerThread.interrupt();
    }
}
