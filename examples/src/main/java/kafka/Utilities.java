package kafka;

import java.io.*;

public final class Utilities {

    public static final int BUFFER_SIZE = 10;

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
}