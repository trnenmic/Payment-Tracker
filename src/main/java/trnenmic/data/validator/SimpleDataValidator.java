package trnenmic.data.validator;

import trnenmic.input.ValidatorUtils;

public class SimpleDataValidator implements DefaultDataValidator {


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
