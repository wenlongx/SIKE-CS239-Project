/*
 * Created by Sahil Gandhi
 */

package kafka.avro_serde;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;

public class AvroSchemas {

    public static final Schema primitiveMessageSchema = SchemaBuilder.record("PrimitiveMessage").fields()
            .optionalString("query")
            .optionalInt("page_number")
            .optionalInt("result_per_page")
            .optionalLong("timestamp")
            .endRecord();

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
