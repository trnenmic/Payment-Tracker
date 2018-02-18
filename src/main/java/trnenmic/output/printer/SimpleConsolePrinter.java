package trnenmic.output.printer;

public class SimpleConsolePrinter implements DefaultConsolePrinter {

    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
