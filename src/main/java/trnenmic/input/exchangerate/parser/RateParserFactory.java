package trnenmic.input.exchangerate.parser;

public class RateParserFactory {

    public static BaseRateParser createDefault(String line) {
        return createSimple(line);
    }

    private static BaseRateParser createSimple(String line) {
        return new SimpleRateParser(line);
    }
}
