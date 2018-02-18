package trnenmic.input.currencyamount.validator;

public interface DefaultLineValidator {

    /**
     * Checks if string can be parsed to long value
     *
     * @param string to be tested
     * @return true if it is long, false otherwise
     */
    boolean isValidAmount(String string);

    /**
     * Checks if string is formatted as Currency Code
     *
     * @param string to be tested
     * @return true if it meets requirements for Currency Code, false otherwise
     */
    boolean isValidCurrencyCode(String string);

    /**
     * Checks if string equals to quit command
     *
     * @param string to be tested
     * @return true if it is long, false otherwise
     */
    boolean isQuitCommand(String string);

}
