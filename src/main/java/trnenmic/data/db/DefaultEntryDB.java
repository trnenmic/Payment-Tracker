package trnenmic.data.db;

import trnenmic.exceptions.WrongFormatException;

public interface DefaultEntryDB {

    /**
     * Increases amount of money of specified currency and saves that to the DB.
     * Creates new entry if there was no amount of specified currency before.
     *
     * @param code  currency code to add amount to
     * @param value value by which the amount will be increased (or decreased if value is negative)
     * @throws WrongFormatException thrown if currency code is not formatted properly
     */
    void increaseAmount(String code, long value) throws WrongFormatException;

    /**
     * Puts an exchange rate value both between both currencies, ie. rate itself when used for codeFrom -> codeTo and
     * 1/rate when used in other direction (codeTo -> codeFrom).
     *
     * @param codeFrom starting currency code
     * @param codeTo   destination currency code
     * @param rate     double value describing exchange rate
     * @throws WrongFormatException thrown if any of these currency code params are not properly formatted
     */
    void putRate(String codeFrom, String codeTo, double rate) throws WrongFormatException;

    /**
     * Creates one string describing all entries in DB, sorted alphabetically. Each entry contain evaluated amount
     * of money that one could exchange in other currency described by parameter.
     *
     * @param exchangePrintCode exchange currency that should be included in the string
     * @return String describing all entries in DB, separated by EOLs.
     * @throws WrongFormatException thrown if currency code is not formatted properly
     */
    String retrieveContent(String exchangePrintCode) throws WrongFormatException;

    /**
     * Creates one string describing all entries in DB, sorted alphabetically.
     *
     * @return String describing all entries in DB, separated by EOLs.
     */
    String retrieveContent();

}
