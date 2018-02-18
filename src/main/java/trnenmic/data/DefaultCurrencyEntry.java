package trnenmic.data;

public interface DefaultCurrencyEntry {

    /**
     * Adds value to the amount of the currency entry
     *
     * @param addition value to be added
     */
    void add(long addition);

    /**
     * Returns amount of currency entry.
     *
     * @return amount of currency entry
     */
    long getCurrentAmount();

    /**
     * Checks whether amount is readable and it is readable only if saved amount is NOT equal to 0.
     *
     * @return false if amount is zero, true otherwise
     */
    boolean isReadable();

    /**
     * Saves exchange rate from this currency to other currency.
     *
     * @param codeTo String describing currency code
     * @param rate   double value describing exchange rate
     */
    void putExchangeRate(String codeTo, double rate);

    /**
     * Provides exchange rate from this entry to the currency code specified as parameter.
     *
     * @param codeTo currency code
     * @return Double value if rate that belongs to such currency code exists, null otherwise
     */
    Double getExchangeRate(String codeTo);


}
