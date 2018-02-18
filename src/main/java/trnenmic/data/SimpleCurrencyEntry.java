package trnenmic.data;

import java.util.HashMap;

public class SimpleCurrencyEntry implements DefaultCurrencyEntry {

    private long currentAmount;
    private HashMap<String, Double> exchangeRates;


    protected SimpleCurrencyEntry(long currentAmount) {
        this.currentAmount = currentAmount;
        this.exchangeRates = new HashMap<>();
    }

    /**
     * Adds value to the amount of the currency entry
     *
     * @param addition value to be added
     */
    @Override
    public void add(long addition) {
        this.currentAmount += addition;
    }

    /**
     * Returns amount of currency entry.
     *
     * @return amount of currency entry
     */
    @Override
    public long getCurrentAmount() {
        return currentAmount;
    }

    /**
     * Checks whether amount is readable and it is readable only if saved amount is NOT equal to 0.
     *
     * @return false if amount is zero, true otherwise
     */
    @Override
    public boolean isReadable() {
        boolean isReadable = false;
        if (currentAmount != 0) {
            isReadable = true;
        }
        return isReadable;
    }

    /**
     * Saves exchange rate from this currency to other currency.
     *
     * @param codeTo String describing currency code
     * @param rate   double value describing exchange rate
     */
    @Override
    public void putExchangeRate(String codeTo, double rate) {
        exchangeRates.put(codeTo, rate);
    }


    /**
     * Provides exchange rate from this entry to the currency code specified as parameter.
     *
     * @param codeTo currency code
     * @return Double value if rate that belongs to such currency code exists, null otherwise
     */
    @Override
    public Double getExchangeRate(String codeTo) {
        return exchangeRates.get(codeTo);
    }
}
