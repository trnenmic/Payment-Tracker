package trnenmic.data.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DefaultDataValidatorTest {


    private DefaultDataValidator validator;

    @Before
    public void prepare() {
        validator = DataValidatorFactory.createDefault();
    }

    /**
     * Tests currency code validity (correct)
     */
    @Test
    public void isValidCurrencyCodeTest1() {
        assertTrue(validator.isValidCurrencyCode("CZK"));
    }

    /**
     * Tests currency code validity (false - not capitalized)
     */
    @Test
    public void isValidCurrencyCodeTest2() {
        assertFalse(validator.isValidCurrencyCode("abc"));
    }

    /**
     * Tests currency code validity (false - not 3 letters)
     */
    @Test
    public void isValidCurrencyCodeTest3() {
        assertFalse(validator.isValidCurrencyCode("ABCD"));
    }

    /**
     * Tests currency code validity (false - not 3 letters but 3 numbers)
     */
    @Test
    public void isValidCurrencyCodeTest5() {
        assertFalse(validator.isValidCurrencyCode("123"));
    }

    /**
     * Tests currency code validity (false - empty string)
     */
    @Test
    public void isValidCurrencyCodeTest6() {
        assertFalse(validator.isValidCurrencyCode(""));
    }

    /**
     * Tests currency code validity (false - null)
     */
    @Test
    public void isValidCurrencyCodeTest7() {
        assertFalse(validator.isValidCurrencyCode(null));
    }

    /**
     * Tests currency code validity (false - white spaces)
     */
    @Test
    public void isValidCurrencyCodeTest8() {
        assertFalse(validator.isValidCurrencyCode("   "));
    }
}
