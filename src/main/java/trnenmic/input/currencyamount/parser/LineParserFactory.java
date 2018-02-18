package trnenmic.input.currencyamount.parser;

public class LineParserFactory {

    public static BaseLineParser createDefault(String line) {
        return createSimpleParser(line);
    }

    private static BaseLineParser createSimpleParser(String line) {
        return new SimpleLineParser(line);
    }

}
