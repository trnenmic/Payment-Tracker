package trnenmic.manager.callback;

import trnenmic.manager.IOManager;

public class OutputDBCallback {

    private IOManager manager;

    public OutputDBCallback(IOManager manager) {
        this.manager = manager;
    }

    public String retrieveContent(String exchangeCode) {
        return manager.retrieveDBContent(exchangeCode);
    }
}
