package trnenmic.output;

import trnenmic.manager.callback.OutputDBCallback;
import trnenmic.output.printer.DefaultConsolePrinter;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class PrinterThread implements Runnable {

    private AtomicBoolean shouldExit;
    private OutputDBCallback retrieveCallback;
    private DefaultConsolePrinter printer;

    private final static Logger LOGGER = Logger.getLogger(PrinterThread.class.getName());
    private static final int SLEEP_TIME = 60;
    private static final int AWAKE_COEF = 5;
    private static final String EXCHANGE_CODE = "USD";

    public PrinterThread(DefaultConsolePrinter printer, AtomicBoolean shouldExit, OutputDBCallback retrieveCallback) {
        this.printer = printer;
        this.retrieveCallback = retrieveCallback;
        this.shouldExit = shouldExit;
    }

    public void run() {
        try {
            while (!shouldExit.get()) {

                //Printing the content of DB
                String message = retrieveCallback.retrieveContent(EXCHANGE_CODE);
                if ("".equals(message)) {
                    message = "-- Empty Database --";
                }
                printer.print(message);

                //Awaking from time to time to be able to react quickly if 'quit' command came
                for (int i = 0; i < AWAKE_COEF * SLEEP_TIME; i++) {
                    if (shouldExit.get()) {
                        break;
                    } else {
                        sleep(1000 / AWAKE_COEF);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }


}
