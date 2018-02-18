package trnenmic.input;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorUtilsTest {


    /**
     * Tests currency code validity (correct)
     */
    @Test
    public void isValidCurrencyCodeTest1() {
        assertTrue(ValidatorUtils.isValidCurrencyCode("CZK"));
    }

    /**
     * Tests currency code validity (false - not capitalized)
     */
    @Test
    public void isValidCurrencyCodeTest2() {
        assertFalse(ValidatorUtils.isValidCurrencyCode("abc"));
    }

    /**
     * Tests currency code validity (false - not 3 letters)
     */
    @Test
    public void isValidCurrencyCodeTest3() {
        assertFalse(ValidatorUtils.isValidCurrencyCode("ABCD"));
    }

    /**
     * Tests currency code validity (false - not 3 letters but 3 numbers)
     */
    @Test
    public void isValidCurrencyCodeTest5() {
        assertFalse(ValidatorUtils.isValidCurrencyCode("123"));
    }

    /**
     * Tests currency code validity (false - empty string)
     */
    @Test
    public void isValidCurrencyCodeTest6() {
        assertFalse(ValidatorUtils.isValidCurrencyCode(""));
    }

    /**
     * Tests currency code validity (false - null)
     */
    @Test
    public void isValidCurrencyCodeTest7() {
        assertFalse(ValidatorUtils.isValidCurrencyCode(null));
    }

    /**
     * Tests currency code validity (false - white spaces)
     */
    @Test
    public void isValidCurrencyCodeTest8() {
        assertFalse(ValidatorUtils.isValidCurrencyCode("   "));
    }
}
