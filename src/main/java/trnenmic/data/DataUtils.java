package trnenmic.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DataUtils {

    /**
     * Rounds a double value with precision specified in parameter 'places' and returns result as string.
     *
     * @param value  a double value to be rounded
     * @param places digits after decimal point for which result should be rounded
     * @return rounded result as string
     */
    public static String round(Double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal decimal = new BigDecimal(value);
        decimal = decimal.setScale(places, RoundingMode.HALF_UP);
        return decimal.toString();
    }
}
