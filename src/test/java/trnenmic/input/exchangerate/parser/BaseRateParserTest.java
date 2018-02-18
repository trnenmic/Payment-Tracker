package trnenmic.input.exchangerate.parser;

import org.junit.Test;
import trnenmic.exceptions.WrongFormatException;

import static org.junit.Assert.*;

public class BaseRateParserTest {

    /**
     * Tests validity of parsed currency entry input
     */
    @Test
    public void isValidTest1() {
        BaseRateParser parser = RateParserFactory.createDefault("CZK USD 1.25");
        assertTrue(parser.isValid());
    }


    /**
     * Tests validity of random string (command quit that should not work in rate parser)
     */
    @Test
    public void isValidTest2() {
        BaseRateParser parser = RateParserFactory.createDefault("quit");
        assertFalse(parser.isValid());
    }

    /**
     * Tests validity of wrongly formatted input (missing rate)
     */
    @Test
    public void isValidTest3() {
        BaseRateParser parser = RateParserFactory.createDefault("ABC CDEF");
        assertFalse(parser.isValid());
    }

    /**
     * Tests validity of wrongly formatted input (wrong length of currency code)
     */
    @Test
    public void isValidTest4() {
        BaseRateParser parser = RateParserFactory.createDefault("ABCD AXE 1235");
        assertFalse(parser.isValid());
    }

    /**
     * Tests validity of wrongly formatted input (too many tokens)
     */
    @Test
    public void isValidTest5() {
        BaseRateParser parser = RateParserFactory.createDefault("ABC CDE 9101 XYZ");
        assertFalse(parser.isValid());
    }

    /**
     * Tests validity of wrongly formatted input (amount is not double)
     */
    @Test
    public void isValidTest6() {
        BaseRateParser parser = RateParserFactory.createDefault("ABC 1E2");
        assertFalse(parser.isValid());
    }

    /**
     * Tests validity of wrongly formatted input (currency code is not capitalized)
     */
    @Test
    public void isValidTest7() {
        BaseRateParser parser = RateParserFactory.createDefault("czk usd 123");
        assertFalse(parser.isValid());
    }

    /**
     * Tests getting of fromCurrencyCode (is correct)
     *
     * @throws WrongFormatException
     */
    @Test
    public void getFromCodeTest1() throws WrongFormatException {
        BaseRateParser parser = RateParserFactory.createDefault("CZK USD 123");
        assertEquals(parser.getFromCurrencyCode(), "CZK");
    }

    /**
     * Tests getting of fromCurrencyCode (should throw exception)
     *
     * @throws WrongFormatException
     */
    @Test(expected = WrongFormatException.class)
    public void getFromCodeTest2() throws WrongFormatException {
        BaseRateParser parser = RateParserFactory.createDefault("CZKA USD 123");
        parser.getFromCurrencyCode();
    }

    /**
     * Tests getting of toCurrencyCode (is correct)
     *
     * @throws WrongFormatException
     */
    @Test
    public void getToCodeTest1() throws WrongFormatException {
        BaseRateParser parser = RateParserFactory.createDefault("CZK USD 123");
        assertEquals(parser.getToCurrencyCode(), "USD");
    }

    /**
     * Tests getting of toCurrencyCode should throw exception)
     *
     * @throws WrongFormatException
     */
    @Test(expected = WrongFormatException.class)
    public void getToCodeTest2() throws WrongFormatException {
        BaseRateParser parser = RateParserFactory.createDefault("CZK USDA 123");
        parser.getToCurrencyCode();
    }

    /**
     * Tests getting of rate (correct)
     *
     * @throws WrongFormatException
     */
    @Test
    public void getRateTest1() throws WrongFormatException {
        BaseRateParser parser = RateParserFactory.createDefault("CZK USD 123.1");
        Double val = parser.getRate();
        assertEquals(val, new Double(123.1));
    }

    /**
     * Tests getting of rate (should throw exception)
     *
     * @throws WrongFormatException
     */
    @Test(expected = WrongFormatException.class)
    public void getRateTest2() throws WrongFormatException {
        BaseRateParser parser = RateParserFactory.createDefault("CZK USD 123.X");
        parser.getRate();
    }
}
