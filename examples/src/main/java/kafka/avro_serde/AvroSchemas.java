/*
 * Created by Sahil Gandhi
 */

package kafka.avro_serde;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericData;

public class AvroSchemas {

    public static final Schema primitiveMessageSchema = SchemaBuilder.record("PrimitiveMessage").fields()
            .optionalLong("timestamp")
            .optionalString("query")
            .optionalInt("page_number")
            .optionalInt("result_per_page")
            .endRecord();

    public static final Schema complexMessageSchema = SchemaBuilder.record("ComplexMessage").fields()
            .optionalLong("timestamp")
            .name("storage").type().optional().map().values().intType()
            .name("arr").type().optional().array().items().intType()
            .endRecord();

    public static final Schema nestedMessageSchema = SchemaBuilder.record("NestedMessage").fields()
            .optionalLong("timestamp")
            .optionalInt("id")
            .name("primitiveMsg").type(primitiveMessageSchema).noDefault()
            .endRecord();

    public static final Schema nestedMessageSchema2 = SchemaBuilder
            .record("NestedMessage").fields()
            .optionalLong("timestamp")
            .optionalInt("id")
            .name("primitiveMsg").type().array().items(primitiveMessageSchema)
            .noDefault()
            .endRecord();


}
