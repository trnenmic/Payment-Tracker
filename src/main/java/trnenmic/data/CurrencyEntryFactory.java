package trnenmic.data;

public class CurrencyEntryFactory {

    public static DefaultCurrencyEntry createDefault(long amount) {
        return createSimple(amount);
    }

    private static DefaultCurrencyEntry createSimple(long amount) {
        return new SimpleCurrencyEntry(amount);
    }
}
