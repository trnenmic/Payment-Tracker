package trnenmic.data.validator;

public interface DefaultDataValidator {

    /**
     * Checks if string is formatted as Currency Code
     *
     * @param string to be tested
     * @return true if it meets requirements for Currency Code, false otherwise
     */
    boolean isValidCurrencyCode(String string);
}
