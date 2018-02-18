package trnenmic.input.exchangerate.parser;

import trnenmic.exceptions.WrongFormatException;

public abstract class BaseRateParser {

    /**
     * Parser requires line to be parsed in class constructor.
     * Class methods then allow to gather data parsed from the line
     * and/or retrieve error info.
     */

    protected static final String EXCEPTION_INFO = "Attempt to access wrongly formatted input.";
    protected static final String WRONG_FORMATTING = "-- Wrong input formatting --";
    protected String line;

    protected BaseRateParser(String line) {
        this.line = line;
    }


    /**
     * Validates line inserted as parameter in parser class constructor
     *
     * @return true if acceptable by formatting rules, false otherwise
     */
    abstract public boolean isValid();

    /**
     * Provides user-friendly info about an error
     * made by violating input formatting rules
     *
     * @return String describing the input error
     */
    abstract public String getFailureMessage();

    /**
     * Retrieves rate between codes
     *
     * @return double value describing the exchange rate between the 'fromCode' and the 'toCode'
     */
    abstract public double getRate() throws WrongFormatException;

    /**
     * Retrieves parsed 3-letters currency code
     *
     * @return currency code as String
     */
    abstract public String getFromCurrencyCode() throws WrongFormatException;

    /**
     * Retrieves parsed 3-letters currency code
     *
     * @return currency code as String
     */
    abstract public String getToCurrencyCode() throws WrongFormatException;


}
