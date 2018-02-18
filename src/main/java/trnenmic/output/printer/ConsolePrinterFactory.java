package trnenmic.output.printer;

public class ConsolePrinterFactory {

    public static DefaultConsolePrinter createDefaultConsolePrinter() {
        return createSimpleConsolePrinter();
    }

    private static DefaultConsolePrinter createSimpleConsolePrinter() {
        return new SimpleConsolePrinter();
    }
}
