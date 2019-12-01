package kafka;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;

import java.io.*;

public final class Utilities {
    public static void appendToFile(String fileName, String value){
        try {
            Writer writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(value);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static final Schema searchRequestSchema = SchemaBuilder.record("SearchRequest").fields()
            .optionalString("query")
            .optionalInt("page_number")
            .optionalInt("result_per_page")
            .endRecord();
}
