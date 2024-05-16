package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import project.*;

public class CounterTests {

    // GENERATION FUNCTIONS FOR TESTS   

    private static Product generateProduct(int priceAndDuration) {
        return new Product("TEST", priceAndDuration, priceAndDuration);
    }

    private static Product[] generateShoppingCart(int priceAndDuration) {
        Product[] shoppingCart = { generateProduct(priceAndDuration) };
        return shoppingCart;
    }

    private static Client generateClient(int priceAndDuration, int arrivalTime) {
        return new Client(-1, generateShoppingCart(priceAndDuration), arrivalTime, priceAndDuration);
    }

    // TEST GETTERS

    @Test
    public void testGetCounterId() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(10, 0, sb);
        int expected = 10;
        int actual = counter.getCounterId();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetCurrentTime1() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 10, sb);
        int expected = 10;
        int actual = counter.getCurrentTime();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetSalesAmmount() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 0, sb);
        double expected = 0.0;
        double actual = counter.getSalesAmount();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void testGetTotalProcessingDuration() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 0, sb);
        int expected = 0;
        int actual = counter.getTotalProcessingDuration();
        assertEquals(expected, actual);
    }

    // TEST ATTRIBUTES

    @Test
    public void testCurrentTime1() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 0, sb);
        counter.addClient(generateClient(10, 0));
        counter.processQueueForDuration(0);
        int expected = 0;
        int actual = counter.getCurrentTime();
        assertEquals(expected, actual);
    }

    @Test
    public void testCurrentTime2() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 0, sb);
        counter.addClient(generateClient(10, 0));
        counter.processQueueForDuration(5);
        int expected = 5;
        int actual = counter.getCurrentTime();
        assertEquals(expected, actual);
    }

    @Test
    public void testCurrentTime3() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 0, sb);
        counter.addClient(generateClient(10, 0));
        counter.processQueueForDuration(10);
        int expected = 10;
        int actual = counter.getCurrentTime();
        assertEquals(expected, actual);
    }

    @Test
    public void testCurrentTime4() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 0, sb);
        counter.addClient(generateClient(10, 0));
        counter.processQueueForDuration(20);
        int expected = 20;
        int actual = counter.getCurrentTime();
        assertEquals(expected, actual);
    }

    @Test
    public void testCurrentTime5() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 10, sb);
        counter.addClient(generateClient(10, 10));
        counter.processQueueForDuration(5);
        int expected = 15;
        int actual = counter.getCurrentTime();
        assertEquals(expected, actual);
    }

    @Test
    public void testCurrentTime6() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 10, sb);
        counter.addClient(generateClient(10, 10));
        counter.processQueueForDuration(10);
        int expected = 20;
        int actual = counter.getCurrentTime();
        assertEquals(expected, actual);
    }

    @Test
    public void testCurrentTime7() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 10, sb);
        counter.addClient(generateClient(10, 10));
        counter.processQueueForDuration(20);
        int expected = 30;
        int actual = counter.getCurrentTime();
        assertEquals(expected, actual);
    }

    @Test
    public void testCurrentTime8() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 0, sb);
        counter.addClient(generateClient(10, 0));
        counter.processQueueForDuration(10);
        counter.addClient(generateClient(20, 10));
        counter.processQueueForDuration(20);
        int expected = 30;
        int actual = counter.getCurrentTime();
        assertEquals(expected, actual);
    }

    @Test
    public void testSalesAmount1() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 0, sb);
        double expected = 0.0;
        double actual = counter.getSalesAmount();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void testSalesAmount2() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 0, sb);
        counter.addClient(generateClient(10, 0));
        double expected = 0.0;
        double actual = counter.getSalesAmount();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void testSalesAmount3() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 0, sb);
        counter.addClient(generateClient(10, 0));
        counter.processQueueForDuration(10);
        double expected = 10.0;
        double actual = counter.getSalesAmount();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void testSalesAmount4() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 0, sb);
        counter.addClient(generateClient(10, 0));
        counter.processQueueForDuration(10);
        counter.addClient(generateClient(5, 10));
        counter.processQueueForDuration(1);
        double expected = 10.0;
        double actual = counter.getSalesAmount();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void testTotalProcessingDuration1() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 0, sb);
        int expected = 0;
        int actual = counter.getTotalProcessingDuration();
        assertEquals(expected, actual);
    }

    @Test
    public void testTotalProcessingDuration2() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 0, sb);
        int expected = 0;
        int actual = counter.getTotalProcessingDuration();
        assertEquals(expected, actual);
    }

    @Test
    public void testTotalProcessingDuration3() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 0, sb);
        counter.addClient(generateClient(10, 0));
        int expected = 10;
        int actual = counter.getTotalProcessingDuration();
        assertEquals(expected, actual);
    }

    @Test
    public void testTotalProcessingDuration4() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 0, sb);
        counter.addClient(generateClient(10, 0));
        counter.addClient(generateClient(10, 0));
        int expected = 20;
        int actual = counter.getTotalProcessingDuration();
        assertEquals(expected, actual);
    }

    @Test
    public void testTotalProcessingDuration5() { 
        StringBuilder sb = new StringBuilder();
        Counter counter = new Counter(0, 0, sb);
        counter.addClient(generateClient(10, 0));
        counter.processQueueForDuration(5);
        counter.addClient(generateClient(10, 5));
        int expected = 15;
        int actual = counter.getTotalProcessingDuration();
        assertEquals(expected, actual);
    }

}
