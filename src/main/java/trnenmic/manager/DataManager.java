package trnenmic.manager;

import trnenmic.data.db.DefaultEntryDB;
import trnenmic.data.db.EntryDBFactory;
import trnenmic.exceptions.WrongFormatException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DataManager {

    private static final Logger LOGGER = Logger.getLogger(DataManager.class.getName());
    private DefaultEntryDB entryDB;

    private DataManager(MainManager manager) {
        this.entryDB = EntryDBFactory.createDefault();
    }

    public static DataManager init(MainManager manager) {
        return new DataManager(manager);
    }

    public void increaseAmount(String code, long amount) {
        try {
            entryDB.increaseAmount(code, amount);
        } catch (WrongFormatException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

    }

    public String retrieveDBContent(String exchangePrintCode) {
        String result = "";
        try {
            result = entryDB.retrieveContent(exchangePrintCode);
        } catch (WrongFormatException e) {
            result = "-- Error in database --";
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return result;
    }

    public void putRate(String codeFrom, String codeTo, double rate) {
        try {
            entryDB.putRate(codeFrom, codeTo, rate);
        } catch (WrongFormatException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
