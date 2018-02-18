package trnenmic.input.currencyamount.parser;

import trnenmic.exceptions.WrongFormatException;
import trnenmic.input.LineType;

public abstract class BaseLineParser {

    /**
     * Parser requires line to be parsed in class constructor.
     * Class methods then allow to gather data parsed from the line
     * and/or retrieve error info.
     */

    protected static final String EXCEPTION_INFO = "Attempt to access wrongly formatted input.";
    protected static final String WRONG_FORMATTING = "-- Wrong input formatting. Correct example: 'CZK 1000' --";
    protected String line;

    public BaseLineParser(String line) {
        this.line = line;
    }

    /**
     * Validates line inserted as parameter in parser class constructor
     *
     * @return true if acceptable by formatting rules, false otherwise
     */
    abstract public boolean isValid();

    /**
     * Provides info about type of line
     *
     * @return LineType
     * - STANDARD in case of adding or subtracting money,
     * - FORMAT_ERROR in case of parse error,
     * - QUIT in case user entered "quit" in command line
     */
    abstract public LineType getLineType();

    /**
     * Provides user-friendly info about an error
     * made by violating input formatting rules
     *
     * @return String describing the input error
     */
    abstract public String getFailureMessage() throws WrongFormatException;

    /**
     * Retrieves parsed 3-letters currency code
     *
     * @return currency code as String
     */
    abstract public String getCurrencyCode() throws WrongFormatException;

    /**
     * Retrieves amount of inserted or withdrawn money
     *
     * @return negative long value if withdrawn, positive if inserted
     */
    abstract public long getAmount() throws WrongFormatException;


}
