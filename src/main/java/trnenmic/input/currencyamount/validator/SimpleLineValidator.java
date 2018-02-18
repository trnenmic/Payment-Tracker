package trnenmic.input.currencyamount.validator;

import trnenmic.input.ValidatorUtils;

public class SimpleLineValidator implements DefaultLineValidator {


    /**
     * Checks if string can be parsed to long value
     *
     * @param string to be tested
     * @return true if it is long, false otherwise
     */
    @Override
    public boolean isValidAmount(String string) {
        boolean isValid = true;
        if (string != null) {
            try {
                Long.parseLong(string);
            } catch (NumberFormatException e) {
                isValid = false;
            }
        } else {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Checks if string is formatted as Currency Code
     *
     * @param string to be tested
     * @return true if it meets requirements for Currency Code, false otherwise
     */
    @Override
    public boolean isValidCurrencyCode(String string) {
        return ValidatorUtils.isValidCurrencyCode(string);
    }

    /**
     * Checks if string equals to quit command
     *
     * @param string to be tested
     * @return true if it is long, false otherwise
     */
    @Override
    public boolean isQuitCommand(String string) {
        boolean isQuit = false;
        if ("quit".equals(string)) {
            isQuit = true;
        }
        return isQuit;
    }
}
