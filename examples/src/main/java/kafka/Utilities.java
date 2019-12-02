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
    public static final Schema primitiveMessageSchema = SchemaBuilder.record("PrimitiveMessage").fields()
            .optionalString("query")
            .optionalInt("page_number")
            .optionalInt("result_per_page")
            .optionalLong("timestamp")
            .endRecord();
//    public static final Schema mapSchema = SchemaBuilder.map().
    public static final Schema complexMessageSchema = SchemaBuilder.record("ComplexMessage").fields()
            .name("storage").type().map().values().intType().noDefault()
            .name("arr").type().array().items().intType().noDefault()
            .optionalLong("timestamp")
            .endRecord();

    public static final Schema nestedMessageSchema = SchemaBuilder.record("NestedMessage").fields()
            .optionalInt("id")
            .name("primitiveMsg").type(primitiveMessageSchema).noDefault()
            .optionalLong("timestamp")
            .endRecord();


}
