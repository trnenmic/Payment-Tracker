package trnenmic.input.exchangerate.validator;

public class RateValidatorFactory {

    public static DefaultRateValidator createDefault() {
        return createSimple();
    }

    private static DefaultRateValidator createSimple() {
        return new SimpleRateValidator();
    }
}
