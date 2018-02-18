package trnenmic.input.exchangerate.validator;

public interface DefaultRateValidator {

    /**
     * Checks if string can be parsed to double value
     *
     * @param string to be tested
     * @return true if it is double, false otherwise
     */
    boolean isValidRate(String string);

    /**
     * Checks if string is formatted as Currency Code
     *
     * @param string to be tested
     * @return true if it meets requirements for Currency Code, false otherwise
     */
    boolean isValidCurrencyCode(String string);
}
