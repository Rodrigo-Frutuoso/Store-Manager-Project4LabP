package tests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

import project.*;

public class StoreTests {

    // TEST ATTRIBUTES

    @Test
    public void testTotalSalesAmmount1() throws FileNotFoundException {

        int exec = 1;
        StringBuilder sb = new StringBuilder();
        Store store = new Store(String.format("./input/store/store%d.txt", exec), sb);
        store.processEvents(String.format("./input/events/events%d.txt", exec));
        double expected = 1.0;
        double actual = store.getTotalSalesAmount();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void testTotalSalesAmmount2() throws FileNotFoundException {

        int exec = 2;
        StringBuilder sb = new StringBuilder();
        Store store = new Store(String.format("./input/store/store%d.txt", exec), sb);
        store.processEvents(String.format("./input/events/events%d.txt", exec));
        double expected = 8.0;
        double actual = store.getTotalSalesAmount();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void testTotalSalesAmmount3() throws FileNotFoundException {

        int exec = 3;
        StringBuilder sb = new StringBuilder();
        Store store = new Store(String.format("./input/store/store%d.txt", exec), sb);
        store.processEvents(String.format("./input/events/events%d.txt", exec));
        double expected = 155.0;
        double actual = store.getTotalSalesAmount();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void testTotalSalesAmmount4() throws FileNotFoundException {

        int exec = 4;
        StringBuilder sb = new StringBuilder();
        Store store = new Store(String.format("./input/store/store%d.txt", exec), sb);
        store.processEvents(String.format("./input/events/events%d.txt", exec));
        double expected = 410.0;
        double actual = store.getTotalSalesAmount();
        assertEquals(expected, actual, 0);
    }

    @Test
    public void testTotalSalesAmmount5() throws FileNotFoundException {

        int exec = 5;
        StringBuilder sb = new StringBuilder();
        Store store = new Store(String.format("./input/store/store%d.txt", exec), sb);
        store.processEvents(String.format("./input/events/events%d.txt", exec));
        double expected = 1890.0;
        double actual = store.getTotalSalesAmount();
        assertEquals(expected, actual, 0);
    }

    // TEST METHODS

    @Test
    public void testProcessClient1() throws FileNotFoundException {
    }

    @Test
    public void testFirstCounterToFinish1() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Store store = new Store("./src/tests/storeTest1.txt", sb);
        Product[] shoppingCart = { new Product("TEST", 1, 1) };
        Client client = new Client(1, shoppingCart, 0, 1);
        store.processClient(client);
        int expected = 0;
        int actual = store.firstCounterToFinish();
        assertEquals(expected, actual);
    }

    @Test
    public void testFirstCounterToFinish2() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Store store = new Store("./src/tests/storeTest2.txt", sb);
        Product[] shoppingCart = { new Product("TEST", 1, 1) };
        Client client = new Client(1, shoppingCart, 0, 1);
        store.processClient(client);
        int expected = 1;
        int actual = store.firstCounterToFinish();
        assertEquals(expected, actual);
    }

    @Test
    public void testFirstCounterToFinish3() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Store store = new Store("./src/tests/storeTest2.txt", sb);
        Product[] shoppingCart1 = { new Product("TEST5", 5, 5) };
        Client client1 = new Client(1, shoppingCart1, 0, 5);
        store.processClient(client1);
        Product[] shoppingCart2 = { new Product("TEST1", 1, 1) };
        Client client2 = new Client(2, shoppingCart2, 0, 1);
        store.processClient(client2);
        int expected = 1;
        int actual = store.firstCounterToFinish();
        assertEquals(expected, actual);
    }

    @Test
    public void testFirstCounterToFinish4() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Store store = new Store("./src/tests/storeTest2.txt", sb);
        Product[] shoppingCart1 = { new Product("TEST5", 5, 5) };
        Client client1 = new Client(1, shoppingCart1, 0, 5);
        store.processClient(client1);
        Product[] shoppingCart2 = { new Product("TEST2", 2, 2) };
        Client client2 = new Client(2, shoppingCart2, 4, 2);
        store.processClient(client2);
        int expected = 0;
        int actual = store.firstCounterToFinish();
        assertEquals(expected, actual);
    }

    // when two counters are tied, the one with lesser counterId is chosen
    @Test
    public void testFirstCounterToFinish5() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Store store = new Store("./src/tests/storeTest2.txt", sb);
        Product[] shoppingCart1 = { new Product("TEST5", 5, 5) };
        Client client1 = new Client(1, shoppingCart1, 0, 5);
        store.processClient(client1);
        store.processClient(client1);
        int expected = 0;
        int actual = store.firstCounterToFinish();
        assertEquals(expected, actual);
    }

    // when two counters are tied, the one with lesser counterId is chosen
    @Test
    public void testFirstCounterToFinish6() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Store store = new Store("./src/tests/storeTest2.txt", sb);
        Product[] shoppingCart1 = { new Product("TEST5", 5, 5) };
        Client client1 = new Client(1, shoppingCart1, 0, 5);
        store.processClient(client1);
        store.processClient(client1);
        store.processClient(client1);
        int expected = 1;
        int actual = store.firstCounterToFinish();
        assertEquals(expected, actual);
    }

    @Test
    public void testFirstCounterToFinish7() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Store store = new Store("./src/tests/storeTest2.txt", sb);
        Product[] shoppingCart1 = { new Product("TEST1", 1, 1) };
        Client client1 = new Client(1, shoppingCart1, 0, 1);
        Product[] shoppingCart2 = { new Product("TEST5", 5, 5) };
        Client client2 = new Client(1, shoppingCart2, 0, 5);
        store.processClient(client1);
        store.processClient(client2);
        store.processClient(client2);
        int expected = 1;
        int actual = store.firstCounterToFinish();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testFirstCounterToFinish8() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Store store = new Store("./src/tests/storeTest3.txt", sb);
        Product[] shoppingCart1 = { new Product("TEST1", 1, 1) };
        Client client1 = new Client(1, shoppingCart1, 0, 1);
        Product[] shoppingCart2 = { new Product("TEST5", 5, 5) };
        Client client2 = new Client(1, shoppingCart2, 0, 5);
        store.processClient(client1);
        store.processClient(client1);
        store.processClient(client2);
        store.processClient(client2);
        store.processClient(client2);
        int expected = 2;
        int actual = store.firstCounterToFinish();
        assertEquals(expected, actual);
    }

    @Test
    public void testFirstCounterToFinishClient1() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Store store = new Store("./src/tests/storeTest1.txt", sb);
        Product[] shoppingCart = { new Product("TEST", 1, 1) };
        Client client = new Client(1, shoppingCart, 0, 1);
        store.processClient(client);
        int expected = 0;
        int actual = store.firstCounterToFinishClient();
        assertEquals(expected, actual);
    }

    @Test
    public void testFirstCounterToFinishClient2() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Store store = new Store("./src/tests/storeTest2.txt", sb);
        Product[] shoppingCart = { new Product("TEST", 1, 1) };
        Client client = new Client(1, shoppingCart, 0, 1);
        store.processClient(client);
        int expected = 0;
        int actual = store.firstCounterToFinishClient();
        assertEquals(expected, actual);
    }


    @Test
    public void testFirstCounterToFinishClient3() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Store store = new Store("./src/tests/storeTest2.txt", sb);
        Product[] shoppingCart1 = { new Product("TEST1", 1, 1) };
        Client client1 = new Client(1, shoppingCart1, 0, 1);
        Product[] shoppingCart2 = { new Product("TEST5", 5, 5) };
        Client client2 = new Client(1, shoppingCart2, 0, 5);
        store.processClient(client1);
        store.processClient(client2);
        store.processClient(client2);
        int expected = 0;
        int actual = store.firstCounterToFinishClient();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testFirstCounterToFinishClient4() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Store store = new Store("./src/tests/storeTest3.txt", sb);
        Product[] shoppingCart1 = { new Product("TEST1", 1, 1) };
        Client client1 = new Client(1, shoppingCart1, 0, 1);
        Product[] shoppingCart2 = { new Product("TEST5", 5, 5) };
        Client client2 = new Client(1, shoppingCart2, 0, 5);
        store.processClient(client1);
        store.processClient(client1);
        store.processClient(client2);
        store.processClient(client2);
        store.processClient(client2);
        int expected = 0;
        int actual = store.firstCounterToFinishClient();
        assertEquals(expected, actual);
    }

    @Test
    public void testIsProcessingFinished1() throws FileNotFoundException {

        StringBuilder sb = new StringBuilder();
        Store store = new Store(String.format("./input/store/store%d.txt", 1), sb);
        boolean expected = true;
        boolean actual = store.isProcessingFinished();
        assertEquals(expected, actual);
    }

    @Test
    public void testIsProcessingFinished2() throws FileNotFoundException {

        StringBuilder sb = new StringBuilder();
        Store store = new Store("./src/tests/storeTest1.txt", sb);
        Product[] shoppingCart = { new Product("TEST", 1, 1) };
        Client client = new Client(1, shoppingCart, 0, 1);
        store.processClient(client);
        boolean expected = false;
        boolean actual = store.isProcessingFinished();
        assertEquals(expected, actual);
    }

}