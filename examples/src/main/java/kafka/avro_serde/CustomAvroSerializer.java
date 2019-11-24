/*
 * Created by Sahil Gandhi
 */

package kafka.avro_serde;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CustomAvroSerializer implements Serializer<GenericRecord> {

    private final DatumWriter<GenericRecord> datumWriter;

    /**
     * Returns a new instance of {@link CustomAvroSerializer}
     *
     * @param schema The AVRO {@link Schema}
     */
    public CustomAvroSerializer(Schema schema) {
        this.datumWriter = new GenericDatumWriter<>(schema);
    }

    @Override
    public byte[] serialize(String topic, GenericRecord data) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        BinaryEncoder encoder = null;
        encoder = EncoderFactory.get().binaryEncoder(stream, null);
        try {
            this.datumWriter.write(data, encoder);
            encoder.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stream.toByteArray();
    }
}
