package trnenmic.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultCurrencyEntryTest {

    /**
     * Tests if amount is saved properly by creating CurrencyEntry
     */
    @Test
    public void testCreation1() {
        DefaultCurrencyEntry entry = CurrencyEntryFactory.createDefault(0);
        assertEquals(entry.getCurrentAmount(), 0);
    }

    /**
     * Tests if amount is saved properly by creating CurrencyEntry
     */
    @Test
    public void testCreation2() {
        DefaultCurrencyEntry entry = CurrencyEntryFactory.createDefault(-1000);
        assertEquals(entry.getCurrentAmount(), -1000);
    }

    /**
     * Tests amount addition to the saved entry
     */
    @Test
    public void addTest() {
        DefaultCurrencyEntry entry = CurrencyEntryFactory.createDefault(-1000);
        entry.add(1000);
        assertEquals(entry.getCurrentAmount(), 0);
    }

    /**
     * Tests retrieval of exchange rate (exchange rate value is not saved, so null expected)
     */
    @Test
    public void getExchangeRateTest1() {
        DefaultCurrencyEntry entry = CurrencyEntryFactory.createDefault(-1000);
        Double rate = entry.getExchangeRate("USD");
        assertEquals(rate, null);
    }

    /**
     * Tests retrieval of exchange rate (exchange rate value is not saved, so null expected)
     */
    @Test
    public void getExchangeRateTest2() {
        DefaultCurrencyEntry entry = CurrencyEntryFactory.createDefault(-1000);
        Double value = 100.1;
        entry.putExchangeRate("USD", value);
        Double rate = entry.getExchangeRate("USD");
        assertEquals(rate, value);
    }

    @Test
    public void isReadableTest1() {
        DefaultCurrencyEntry entry = CurrencyEntryFactory.createDefault(-1000);
        assertTrue(entry.isReadable());
    }

    @Test
    public void isReadableTest2() {
        DefaultCurrencyEntry entry = CurrencyEntryFactory.createDefault(0);
        assertFalse(entry.isReadable());
    }
}
