package project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class RunProject4 {

    public static void main(String[] args) throws FileNotFoundException {
        RunProject4 runProject4 = new RunProject4();
        for(int exec = 1; exec <= 5; exec++) {
            runProject4.execute(exec);
        }
    }

    public void execute(int exec) throws FileNotFoundException {

        try (PrintWriter writer = new PrintWriter(String.format("./output/storeLog%d.txt", exec))) {
            StringBuilder sb = new StringBuilder();
            Store store = new Store(String.format("./input/store/store%d.txt", exec), sb);
            store.processEvents(String.format("./input/events/events%d.txt", exec));
            //System.out.println(sb.toString());
            writeToFile(writer, sb.toString(), exec, exec);
        }
    }

    private static void writeToFile(PrintWriter writer, String log, int store, int events) {
        //writer.println(">> Log produced from store" + store + " and events" + events);
        writer.println(log);
    }
}
