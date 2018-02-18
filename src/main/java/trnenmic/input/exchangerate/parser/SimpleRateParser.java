package trnenmic.input.exchangerate.parser;

import trnenmic.exceptions.WrongFormatException;
import trnenmic.input.exchangerate.validator.DefaultRateValidator;
import trnenmic.input.exchangerate.validator.RateValidatorFactory;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleRateParser extends BaseRateParser {

    private static final Logger LOGGER = Logger.getLogger(SimpleRateParser.class.getName());

    private ParsedRate parsedLine;
    private boolean parsed = false;

    public SimpleRateParser(String line) {
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
        return parsedLine.isValid();
    }

    /**
     * Provides user-friendly info about an error
     * made by violating input formatting rules
     *
     * @return String describing the input error
     */
    @Override
    public String getFailureMessage() {
        if (!parsed) {
            parse();
        }
        return parsedLine.getFailureMessage();
    }

    /**
     * Retrieves rate of inserted or withdrawn money
     *
     * @return negative long value if withdrawn, positive if inserted
     */
    @Override
    public double getRate() throws WrongFormatException {
        if (!parsed) {
            parse();
        }
        if (WRONG_FORMATTING.equals(parsedLine.getFailureMessage())) {
            throw new WrongFormatException(EXCEPTION_INFO);
        }
        return parsedLine.getRate();
    }

    /**
     * Retrieves parsed 3-letters currency code
     *
     * @return currency code as String
     */
    @Override
    public String getFromCurrencyCode() throws WrongFormatException {
        if (!parsed) {
            parse();
        }
        if (WRONG_FORMATTING.equals(parsedLine.getFailureMessage())) {
            throw new WrongFormatException(EXCEPTION_INFO);
        }
        return parsedLine.getCurrencyCodeFrom();
    }

    /**
     * Retrieves parsed 3-letters currency code
     *
     * @return currency code as String
     */
    @Override
    public String getToCurrencyCode() throws WrongFormatException {
        if (!parsed) {
            parse();
        }
        if (WRONG_FORMATTING.equals(parsedLine.getFailureMessage())) {
            throw new WrongFormatException(EXCEPTION_INFO);
        }
        return parsedLine.getCurrencyCodeTo();
    }

    private void parse() {
        parsedLine = new ParsedRate();
        DefaultRateValidator validator = RateValidatorFactory.createDefault();
        try {
            boolean success = false;
            StringTokenizer tokenizer = new StringTokenizer(line);
            String tok1 = tokenizer.nextToken();
            if (tok1 != null && validator.isValidCurrencyCode(tok1)) {
                parsedLine.setCurrencyCodeFrom(tok1);
                String tok2 = tokenizer.nextToken();
                if (tok2 != null && validator.isValidCurrencyCode(tok2)) {
                    parsedLine.setCurrencyCodeTo(tok2);
                    String tok3 = tokenizer.nextToken();
                    if (tok3 != null && validator.isValidRate(tok3)) {
                        parsedLine.setRate(Double.parseDouble(tok3));
                        if (!tokenizer.hasMoreTokens()) {
                            success = true;
                            parsedLine.setValid(true);
                        }
                    }
                }
            }
            if (!success) {
                parsedLine.setFailureMessage(WRONG_FORMATTING);
            }
        } catch (NoSuchElementException e) {
            parsedLine.setFailureMessage(WRONG_FORMATTING);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        parsed = true;
    }
}
