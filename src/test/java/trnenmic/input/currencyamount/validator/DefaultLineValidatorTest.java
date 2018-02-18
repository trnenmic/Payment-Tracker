package trnenmic.input.currencyamount.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DefaultLineValidatorTest {


    private DefaultLineValidator validator;

    @Before
    public void prepare() {
        validator = LineValidatorFactory.createDefault();
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

    /**
     * Tests valid amount(correct)
     */
    @Test
    public void isValidAmountTest1() {
        assertTrue(validator.isValidAmount("1"));
    }

    /**
     * Tests valid amount(false - not long but float)
     */
    @Test
    public void isValidAmountTest2() {
        assertFalse(validator.isValidAmount("1.1"));
    }

    /**
     * Tests valid amount(false - not a number, just a string)
     */
    @Test
    public void isValidAmountTest3() {
        assertFalse(validator.isValidAmount("s"));
    }

    /**
     * Tests valid amount(false - not one number, but two, so not properly parsed)
     */
    @Test
    public void isValidAmountTest4() {
        assertFalse(validator.isValidAmount("11 1"));
    }

    /**
     * Tests valid quit command (correct)
     */
    @Test
    public void isQuitCommandTest1() {
        assertTrue(validator.isQuitCommand("quit"));
    }

    /**
     * Tests valid quit command (false - not exactly 'quit')
     */
    @Test
    public void isQuitCommandTest2() {
        assertFalse(validator.isQuitCommand("quitty"));
    }
}

