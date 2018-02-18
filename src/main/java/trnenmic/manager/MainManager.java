package trnenmic.manager;

public class MainManager {


    private DataManager dataManager;
    private IOManager ioManager;

    public MainManager() {

    }

    public void init(String[] args) {
        dataManager = DataManager.init(this);
        ioManager = IOManager.init(args, this);
    }

    public void increaseAmount(String code, long amount) {
        dataManager.increaseAmount(code, amount);
    }

    public void putRate(String codeFrom, String codeTo, double rate) {
        dataManager.putRate(codeFrom, codeTo, rate);
    }

    public String retrieveDBContent(String exchangeCode) {
        return dataManager.retrieveDBContent(exchangeCode);
    }


}


