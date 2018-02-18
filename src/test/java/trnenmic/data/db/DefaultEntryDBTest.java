package trnenmic.data.db;

import org.junit.Before;
import org.junit.Test;
import trnenmic.exceptions.WrongFormatException;

import static org.junit.Assert.assertEquals;

public class DefaultEntryDBTest {

    private DefaultEntryDB db;

    @Before
    public void prepare() {
        db = EntryDBFactory.createDefault();
    }

    /**
     * Tests subtraction of entries
     */
    @Test
    public void increaseAndRetrieveTest1() throws WrongFormatException {
        String expected = "CZK -100\n";
        db.increaseAmount("CZK", 100);
        db.increaseAmount("CZK", -200);
        String content = db.retrieveContent();
        assertEquals(content, expected);
    }

    /**
     * Tests alphabetical ordering of entries
     */
    @Test
    public void increaseAndRetrieveTest2() throws WrongFormatException {
        String expected = "CZK 100000\nHKD 1\nRMB 1\nUSD 100000\n";
        db.increaseAmount("USD", 100000);
        db.increaseAmount("CZK", 100000);
        db.increaseAmount("HKD", 1);
        db.increaseAmount("RMB", 1);
        String content = db.retrieveContent();
        assertEquals(content, expected);
    }

    /**
     * Tests DB content if values are subtracted back to zero
     */
    @Test
    public void increaseAndRetrieveTest3() throws WrongFormatException {
        String expected = "";
        db.increaseAmount("USD", 100000);
        db.increaseAmount("HKD", -1);
        db.increaseAmount("HKD", 1);
        db.increaseAmount("USD", -100000);
        String content = db.retrieveContent();
        assertEquals(content, expected);
    }

    /**
     * Tests if exception will be thrown
     * when wrongly formatted currency code is inserted as parameter
     */
    @Test(expected = WrongFormatException.class)
    public void increaseAmountTest1() throws WrongFormatException {
        db.increaseAmount("ABCD", 100000);
    }

    /**
     * Tests if exception will be thrown
     * when wrongly formatted currency code is inserted as parameter
     */
    @Test(expected = WrongFormatException.class)
    public void increaseAmountTest2() throws WrongFormatException {
        db.increaseAmount("123", 100000);
    }

    /**
     * Tests if exception will be thrown
     * when wrongly formatted currency code is inserted as parameter
     */
    @Test(expected = WrongFormatException.class)
    public void increaseAmountTest3() throws WrongFormatException {
        db.increaseAmount("ABC 234", 100000);
    }

    /**
     * Tests content of DB after rate insertion. Rate insertion should not affect content retrieval,
     * therefore empty content is expected.
     */
    @Test
    public void insertRateAndRetrieveTest1() throws WrongFormatException {
        String expected = "";
        db.putRate("USD", "CZK", 20.43);
        String content = db.retrieveContent("USD");
        assertEquals(content, expected);
    }

    /**
     * Tests content of DB after rate insertion. Rate insertion should not affect content retrieval,
     * therefore empty content is expected. Differs from previous test in using retrieveContent() without parameter.
     */
    @Test
    public void insertRateAndRetrieveTest2() throws WrongFormatException {
        String expected = "";
        db.putRate("USD", "CZK", 20.43);
        String content = db.retrieveContent();
        assertEquals(content, expected);
    }

    /**
     * Tests content of DB after rate insertion & entry insertion.
     */
    @Test
    public void insertRateAndRetrieveTest3() throws WrongFormatException {
        String expected = "CZK 100 (USD 4.89)\n";
        db.putRate("USD", "CZK", 20.43);
        db.increaseAmount("CZK", 100);
        String content = db.retrieveContent("USD");
        assertEquals(content, expected);
    }

    /**
     * Tests content of DB after rate insertion & entry insertion - subtraction, which should lead to entry removal.
     */
    @Test
    public void insertRateAndRetrieveTest4() throws WrongFormatException {
        String expected = "";
        db.putRate("USD", "CZK", 20.43);
        db.increaseAmount("CZK", 100);
        db.increaseAmount("CZK", -100);
        String content = db.retrieveContent("USD");
        assertEquals(content, expected);
    }

    /**
     * Tests content of DB after entry insertion and then after two rate insertions.
     */
    @Test
    public void insertRateAndRetrieveTest5() throws WrongFormatException {
        String expected = "CZK 100 (USD 4.89)\n";
        db.increaseAmount("CZK", 100);
        db.putRate("USD", "CZK", 20.43);
        db.putRate("USD", "RMB", 1);
        String content = db.retrieveContent("USD");
        assertEquals(content, expected);
    }

    /**
     * Tests updated exchange rate.
     */
    @Test
    public void insertRateAndRetrieveTest6() throws WrongFormatException {
        String expected = "CZK 100 (USD 4.89)\n";
        db.increaseAmount("CZK", 100);
        db.putRate("USD", "CZK", 1);
        db.putRate("USD", "CZK", 20.43);
        String content = db.retrieveContent("USD");
        assertEquals(content, expected);
    }

    /**
     * Tests if exception will be thrown
     * when wrongly formatted currency code is inserted as parameter
     */
    @Test(expected = WrongFormatException.class)
    public void insertRateTest2() throws WrongFormatException {
        db.putRate("abcd", "CZK", 20.43);
    }

    /**
     * Tests if exception will be thrown
     * when wrongly formatted currency code is inserted as parameter
     */
    @Test(expected = WrongFormatException.class)
    public void insertRateTest3() throws WrongFormatException {
        db.putRate("USD", "12345", 20.43);
    }

}
