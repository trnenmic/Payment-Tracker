package trnenmic.output;

import trnenmic.manager.callback.OutputDBCallback;
import trnenmic.output.printer.DefaultConsolePrinter;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class PrinterThread implements Runnable {

    private OutputDBCallback retrieveCallback;
    private DefaultConsolePrinter printer;

    private final static Logger LOGGER = Logger.getLogger(PrinterThread.class.getName());
    private static final int SLEEP_TIME = 60;
    private static final int AWAKE_COEF = 1;
    private static final String EXCHANGE_CODE = "USD";

    public PrinterThread(DefaultConsolePrinter printer, OutputDBCallback retrieveCallback) {
        this.printer = printer;
        this.retrieveCallback = retrieveCallback;
    }

    public void run() {
        try {
            while (true) {

                //Printing the content of DB
                String message = retrieveCallback.retrieveContent(EXCHANGE_CODE);
                if ("".equals(message)) {
                    message = "-- Empty Database --";
                }
                printer.print(message);

                sleep(1000 * SLEEP_TIME);
            }
        } catch (InterruptedException e) {
            printer.print("-- Quitting the app --");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }


}
