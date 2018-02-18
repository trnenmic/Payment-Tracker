package trnenmic.input.currencyamount.parser;

import org.junit.Test;
import trnenmic.exceptions.WrongFormatException;
import trnenmic.input.LineType;

import static org.junit.Assert.*;

public class BaseLineParserTest {


    /**
     * Tests validity of parsed currency entry input (correct)
     */
    @Test
    public void isValidTest1() {
        BaseLineParser parser = LineParserFactory.createDefault("CZK 100");
        assertTrue(parser.isValid());
    }

    /**
     * Tests validity of parsed quit command (correct)
     */
    @Test
    public void isValidTest2() {
        BaseLineParser parser = LineParserFactory.createDefault("quit");
        assertTrue(parser.isValid());
    }

    /**
     * Tests validity of wrongly formatted input (false - missing amount)
     */
    @Test
    public void isValidTest3() {
        BaseLineParser parser = LineParserFactory.createDefault("ABC");
        assertFalse(parser.isValid());
    }

    /**
     * Tests validity of wrongly formatted input (false - wrong length of currency code)
     */
    @Test
    public void isValidTest4() {
        BaseLineParser parser = LineParserFactory.createDefault("ABCD 1235");
        assertFalse(parser.isValid());
    }

    /**
     * Tests validity of wrongly formatted input (false - too many tokens)
     */
    @Test
    public void isValidTest5() {
        BaseLineParser parser = LineParserFactory.createDefault("ABC 1235 9101");
        assertFalse(parser.isValid());
    }

    /**
     * Tests validity of wrongly formatted input (false - amount is not long)
     */
    @Test
    public void isValidTest6() {
        BaseLineParser parser = LineParserFactory.createDefault("ABC ABC");
        assertFalse(parser.isValid());
    }

    /**
     * Tests validity of wrongly formatted input (false - currency code is not capitalized)
     */
    @Test
    public void isValidTest7() {
        BaseLineParser parser = LineParserFactory.createDefault("abc 123");
        assertFalse(parser.isValid());
    }

    /**
     * Tests getting amount that should be long value (is correct)
     *
     * @throws WrongFormatException
     */
    @Test
    public void getAmountTest1() throws WrongFormatException {
        BaseLineParser parser = LineParserFactory.createDefault("CZK 123");
        assertEquals(parser.getAmount(), 123);
    }


    /**
     * Tests getting amount that should be long value (is double so exception should be thrown)
     *
     * @throws WrongFormatException
     */

    @Test(expected = WrongFormatException.class)
    public void getAmountTest2() throws WrongFormatException {
        BaseLineParser parser = LineParserFactory.createDefault("CZK 123.5");
        parser.getAmount();
    }

    /**
     * Tests getting amount that should be long value (is just a string so exception should be thrown)
     *
     * @throws WrongFormatException
     */

    @Test(expected = WrongFormatException.class)
    public void getAmountTest3() throws WrongFormatException {
        BaseLineParser parser = LineParserFactory.createDefault("CZK ACD");
        parser.getAmount();
    }

    /**
     * Tests retrieving LineType from parsed line (input is wrongly formatted)
     */
    @Test
    public void getLineTypeTest1() {
        BaseLineParser parser = LineParserFactory.createDefault("CZK ACD");
        LineType lineType = parser.getLineType();
        assertEquals(lineType, LineType.FORMAT_ERROR);
    }


    /**
     * Tests retrieving LineType from parsed line (input is correctly formatted)
     */
    @Test
    public void getLineTypeTest2() {
        BaseLineParser parser = LineParserFactory.createDefault("CZK 123");
        LineType lineType = parser.getLineType();
        assertEquals(lineType, LineType.STANDARD);
    }

    /**
     * Tests retrieving LineType from parsed line (input is a quit command)
     */
    @Test
    public void getLineTypeTest3() {
        BaseLineParser parser = LineParserFactory.createDefault("quit");
        LineType lineType = parser.getLineType();
        assertEquals(lineType, LineType.QUIT);
    }

    /**
     * Tests retrieving currency code from parsed line (correct - line is correctly formatted)
     *
     * @throws WrongFormatException
     */
    @Test
    public void getCurrencyCodeTest1() throws WrongFormatException {
        BaseLineParser parser = LineParserFactory.createDefault("CZK 123");
        assertEquals("CZK", parser.getCurrencyCode());
    }

    /**
     * Tests retrieving currencyCode from parsed line (line is wrongly formatted, exception should be thrown)
     *
     * @throws WrongFormatException
     */
    @Test(expected = WrongFormatException.class)
    public void getCurrencyCodeTest2() throws WrongFormatException {
        BaseLineParser parser = LineParserFactory.createDefault("CZKx 123");
        parser.getCurrencyCode();
    }
}
