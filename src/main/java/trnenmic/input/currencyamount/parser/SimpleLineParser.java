package trnenmic.input.currencyamount.parser;

import trnenmic.exceptions.WrongFormatException;
import trnenmic.input.LineType;
import trnenmic.input.currencyamount.validator.DefaultLineValidator;
import trnenmic.input.currencyamount.validator.LineValidatorFactory;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleLineParser extends BaseLineParser {

    private static final Logger LOGGER = Logger.getLogger(SimpleLineParser.class.getName());

    private ParsedLine parsedLine;
    private boolean parsed = false;


    protected SimpleLineParser(String line) {
        super(line);

    }

    /**
     * Validates line inserted as parameter in parser class constructor
     *
     * @return true if acceptable by formatting rules, false otherwise
     */
    @Override
    public boolean isValid() {
        if (!parsed) {
            parse();
        }
        return (parsedLine.getLineType() == LineType.STANDARD || parsedLine.getLineType() == LineType.QUIT);
    }

    /**
     * Provides info about type of line
     *
     * @return LineType
     * - STANDARD in case of adding or subtracting money,
     * - FORMAT_ERROR in case of parse error,
     * - QUIT in case user entered "quit" in command line
     */
    @Override
    public LineType getLineType() {
        if (!parsed) {
            parse();
        }
        return parsedLine.getLineType();
    }

    /**
     * Provides user-friendly info about an error
     * made by violating input formatting rules
     *
     * @return String describing the input error
     */
    @Override
    public String getFailureMessage() throws WrongFormatException {
        if (!parsed) {
            parse();
        }
        if (parsedLine.getLineType() == LineType.FORMAT_ERROR) {
            return parsedLine.getFailureMessage();
        } else {
            throw new WrongFormatException(EXCEPTION_INFO);
        }
    }

    /**
     * Retrieves parsed 3-letters currency code
     *
     * @return currency code as String
     */
    @Override
    public String getCurrencyCode() throws WrongFormatException {
        if (!parsed) {
            parse();
        }
        if (isValid()) {
            return parsedLine.getCurrencyCode();
        } else {
            throw new WrongFormatException(EXCEPTION_INFO);
        }
    }

    /**
     * Retrieves amount of inserted or withdrawn money
     *
     * @return negative long value if withdrawn, positive if inserted
     */
    @Override
    public long getAmount() throws WrongFormatException {
        if (!parsed) {
            parse();
        }
        if (isValid()) {
            return parsedLine.getAmount();
        } else {
            throw new WrongFormatException(EXCEPTION_INFO);
        }
    }

    private void parse() {
        parsedLine = new ParsedLine();
        DefaultLineValidator validator = LineValidatorFactory.createDefault();
        try {
            boolean success = false;
            StringTokenizer tokenizer = new StringTokenizer(line);
            String tok1 = tokenizer.nextToken();
            if (tok1 != null) {
                if (validator.isQuitCommand(tok1)) {
                    parsedLine.setLineType(LineType.QUIT); //i.e. "quit"
                    success = true;
                } else if (validator.isValidCurrencyCode(tok1)) { //i.e. "CZK"
                    parsedLine.setCurrencyCode(tok1);
                    String tok2 = tokenizer.nextToken();
                    if (tok2 != null && validator.isValidAmount(tok2)) { //i.e. "1000"
                        parsedLine.setAmount(Long.parseLong(tok2));
                        if (!tokenizer.hasMoreTokens()) {
                            parsedLine.setLineType(LineType.STANDARD);
                            success = true;
                        }
                    }
                }
            }
            if (!success) {
                parsedLine.setLineType(LineType.FORMAT_ERROR);
                parsedLine.setFailureMessage(WRONG_FORMATTING);
            }
        } catch (NoSuchElementException e) {
            parsedLine.setLineType(LineType.FORMAT_ERROR);
            parsedLine.setFailureMessage(WRONG_FORMATTING);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        parsed = true;
    }

}
