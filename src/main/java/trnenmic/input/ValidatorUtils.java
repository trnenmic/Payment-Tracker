package trnenmic.input;

public class ValidatorUtils {

    /**
     * Checks if string is formatted as Currency Code
     *
     * @param string to be tested
     * @return true if it meets requirements for Currency Code, false otherwise
     */
    public static boolean isValidCurrencyCode(String string) {
        boolean isValid = false;
        if (string != null && string.length() == 3 && ValidatorUtils.isUpperCase(string)) {
            isValid = true;
        }
        return isValid;
    }

    private static boolean isUpperCase(String string) {
        boolean isUpperCase = true;
        if (string != null) {
            for (int i = 0; i < string.length(); i++) {
                if (!Character.isUpperCase(string.charAt(i))) {
                    isUpperCase = false;
                    break;
                }
            }
        } else {
            isUpperCase = false;
        }
        return isUpperCase;
    }
}
