package trnenmic.data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataUtilsTest {

    /**
     * Tests round up
     */
    @Test
    public void roundTest1() {
        assertEquals(DataUtils.round(1.116, 2), "1.12");
    }

    /**
     * Test round down
     */
    @Test
    public void roundTest2() {
        assertEquals(DataUtils.round(1.111222, 2), "1.11");
    }
}
