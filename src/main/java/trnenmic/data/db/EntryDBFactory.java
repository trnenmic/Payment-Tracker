package trnenmic.data.db;

public class EntryDBFactory {

    public static DefaultEntryDB createDefault() {
        return createSimple();
    }

    private static DefaultEntryDB createSimple() {
        return new SimpleEntryDB();
    }
}
