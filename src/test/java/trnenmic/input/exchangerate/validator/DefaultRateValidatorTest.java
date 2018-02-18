package trnenmic.input.exchangerate.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DefaultRateValidatorTest {


    private DefaultRateValidator validator;

    @Before
    public void prepare() {
        validator = RateValidatorFactory.createDefault();
    }

    @Test
    public void isValidCurrencyCodeTest1() {
        assertTrue(validator.isValidCurrencyCode("CZK"));
    }

    @Test
    public void isValidCurrencyCodeTest2() {
        assertFalse(validator.isValidCurrencyCode("abc"));
    }

    @Test
    public void isValidCurrencyCodeTest3() {
        assertFalse(validator.isValidCurrencyCode("ABCD"));
    }

    @Test
    public void isValidCurrencyCodeTest5() {
        assertFalse(validator.isValidCurrencyCode("123"));
    }

    @Test
    public void isValidCurrencyCodeTest6() {
        assertFalse(validator.isValidCurrencyCode(""));
    }

    @Test
    public void isValidCurrencyCodeTest7() {
        assertFalse(validator.isValidCurrencyCode(null));
    }

    @Test
    public void isValidCurrencyCodeTest8() {
        assertFalse(validator.isValidCurrencyCode("   "));
    }

    @Test
    public void isValidRateTest1() {
        assertTrue(validator.isValidRate("1"));
    }

    @Test
    public void isValidRateTest2() {
        assertTrue(validator.isValidRate("1.1"));
    }

    @Test
    public void isValidRateTest3() {
        assertFalse(validator.isValidRate("s"));
    }

    @Test
    public void isValidRateTest4() {
        assertFalse(validator.isValidRate("11 1"));
    }
}
