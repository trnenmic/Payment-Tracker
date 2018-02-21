package trnenmic.manager.callback;

import trnenmic.manager.IOManager;

public class InputDBCallback {

    private IOManager manager;

    public InputDBCallback(IOManager manager) {
        this.manager = manager;
    }

    public void increaseAmount(String code, long value) {
        manager.increaseAmount(code, value);
    }

    public void putRate(String codeFrom, String codeTo, double rate) {
        manager.putRate(codeFrom, codeTo, rate);
    }

    public void interruptPrinting() {
        manager.shutDown();
    }
}
