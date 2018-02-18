package trnenmic.data.validator;

public class DataValidatorFactory {

    public static DefaultDataValidator createDefault() {
        return createSimple();
    }

    private static DefaultDataValidator createSimple() {
        return new SimpleDataValidator();
    }
}
