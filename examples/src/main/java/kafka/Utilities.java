package kafka;

import java.io.*;

public final class Utilities {

    public static final int BUFFER_SIZE = 1000;
    public static final int METRICS_INTERVAL = 1000; // how many records to skip before measuring the metrics for producer/consumer
    public static final int METRICS_BUFFER_SIZE = 1000; // how many metrics to buffer before writing to disk

    public static void appendToFile(String fileName, long [] writeBuffer) {
        try {
            Writer writer = new BufferedWriter(new FileWriter(fileName, true));
            for (long l: writeBuffer){
                writer.write(l + "\n");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendStringToFile(String fileName, String writeBuffer) {
        try {
            Writer writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(writeBuffer);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
