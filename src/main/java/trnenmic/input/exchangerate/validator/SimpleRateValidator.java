package trnenmic.input.exchangerate.validator;

import trnenmic.input.ValidatorUtils;

public class SimpleRateValidator implements DefaultRateValidator {

    /**
     * Checks if string can be parsed to double value
     *
     * @param string to be tested
     * @return true if it is double, false otherwise
     */
    @Override
    public boolean isValidRate(String string) {
        boolean isValid = true;
        if (string != null) {
            try {
                Double.parseDouble(string);
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
}
