package trnenmic.input.currencyamount.validator;

public class LineValidatorFactory {

    public static DefaultLineValidator createDefault() {
        return createSimple();
    }

    private static DefaultLineValidator createSimple() {
        return new SimpleLineValidator();
    }
}
