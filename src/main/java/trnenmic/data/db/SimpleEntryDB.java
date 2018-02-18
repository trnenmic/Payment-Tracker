package trnenmic.data.db;

import trnenmic.data.CurrencyEntryFactory;
import trnenmic.data.DataUtils;
import trnenmic.data.DefaultCurrencyEntry;
import trnenmic.data.validator.DataValidatorFactory;
import trnenmic.data.validator.DefaultDataValidator;
import trnenmic.exceptions.WrongFormatException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SimpleEntryDB implements DefaultEntryDB {

    private HashMap<String, DefaultCurrencyEntry> map;
    private DefaultDataValidator validator;

    protected SimpleEntryDB() {
        map = new HashMap<>();
        validator = DataValidatorFactory.createDefault();
    }

    /**
     * Increases amount of money of specified currency and saves that to the DB.
     * Creates new entry if there was no amount of specified currency before.
     *
     * @param code  currency code to add amount to
     * @param value value by which the amount will be increased (or decreased if value is negative)
     * @throws WrongFormatException thrown if currency code is not formatted properly
     */
    @Override
    public void increaseAmount(String code, long value) throws WrongFormatException {
        if (!validator.isValidCurrencyCode(code)) {
            throw new WrongFormatException("Wrong format of currency code.");
        }

        DefaultCurrencyEntry currencyEntry = map.get(code);
        if (currencyEntry == null) {
            currencyEntry = CurrencyEntryFactory.createDefault(value);
            map.put(code, currencyEntry);
        } else {
            currencyEntry.add(value);
        }
    }

    /**
     * Creates one string describing all entries in DB, sorted alphabetically. Each entry contain evaluated amount
     * of money that one could exchange in other currency described by parameter.
     *
     * @param exchangePrintCode exchange currency that should be included in the string
     * @return String describing all entries in DB, separated by EOLs.
     * @throws WrongFormatException thrown if currency code is not formatted properly
     */
    @Override
    public String retrieveContent(String exchangePrintCode) throws WrongFormatException {
        if (!validator.isValidCurrencyCode(exchangePrintCode)) {
            throw new WrongFormatException("Wrong format of currency code.");
        }
        return generateContent(exchangePrintCode);
    }

    /**
     * Creates one string describing all entries in DB, sorted alphabetically.
     *
     * @return String describing all entries in DB, separated by EOLs.
     */
    @Override
    public String retrieveContent() {
        return generateContent("");
    }

    private String generateContent(String exchangePrintCode) {
        ArrayList<String> ordered = new ArrayList<>();

        //Getting all entries from DB while converting them into formatted string
        for (Map.Entry<String, DefaultCurrencyEntry> entry : map.entrySet()) {
            String key = entry.getKey();
            DefaultCurrencyEntry currencyEntry = entry.getValue();
            if (currencyEntry.isReadable()) {
                String exchange = "";
                if (exchangePrintCode != null && !"".equals(exchangePrintCode)) {
                    Double rate = currencyEntry.getExchangeRate(exchangePrintCode);
                    if (rate != null) {
                        rate *= currencyEntry.getCurrentAmount();
                        exchange = " (" + exchangePrintCode + " " + DataUtils.round(rate, 2) + ")";
                    }
                }
                ordered.add(key + " " + currencyEntry.getCurrentAmount() + exchange + "\n");
            }
        }

        //Alphabetical sort of entries
        Collections.sort(ordered);
        StringBuilder sb = new StringBuilder("");
        for (String s : ordered) {
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * Puts an exchange rate value both between both currencies, ie. rate itself when used for codeFrom -> codeTo and
     * 1/rate when used in other direction (codeTo -> codeFrom).
     *
     * @param codeFrom starting currency code
     * @param codeTo   destination currency code
     * @param rate     double value describing exchange rate
     * @throws WrongFormatException thrown if any of these currency code params are not properly formatted
     */
    @Override
    public void putRate(String codeFrom, String codeTo, double rate) throws WrongFormatException {
        if (!validator.isValidCurrencyCode(codeFrom) || !validator.isValidCurrencyCode(codeTo)) {
            throw new WrongFormatException("Wrong format of currency code.");
        }

        DefaultCurrencyEntry currencyEntryFrom = map.get(codeFrom);
        DefaultCurrencyEntry currencyEntryTo = map.get(codeTo);

        //Creating 'empty' entries if they did not exist before
        if (currencyEntryFrom == null) {
            currencyEntryFrom = CurrencyEntryFactory.createDefault(0);
            map.put(codeFrom, currencyEntryFrom);
        }
        if (currencyEntryTo == null) {
            currencyEntryTo = CurrencyEntryFactory.createDefault(0);
            map.put(codeTo, currencyEntryTo);
        }

        currencyEntryFrom.putExchangeRate(codeTo, rate);
        currencyEntryTo.putExchangeRate(codeFrom, 1 / rate);
    }
}
