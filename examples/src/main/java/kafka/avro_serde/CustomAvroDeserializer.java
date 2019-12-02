/*
 * Created by Sahil Gandhi
 */

package kafka.avro_serde;

import kafka.Utilities;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class CustomAvroDeserializer implements Deserializer<GenericRecord> {

    private final DatumReader<GenericRecord> datumReader;

    /**
     * Returns a new instance of {@link CustomAvroDeserializer}
     *
     * @param schema The AVRO {@link Schema}
     */
    public CustomAvroDeserializer(Schema schema) {
        datumReader = new GenericDatumReader<>(schema);
    }

    @Override
    public GenericRecord deserialize(String topic, byte[] data) {
        long startTime = System.nanoTime();
        BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(data, null);

        try {
            GenericRecord r = this.datumReader.read(null, decoder);
            long endTime = System.nanoTime();
            Utilities.appendToFile("avro_de.txt", (endTime - startTime) + " \n");
            return r;
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializationException("Could not parse AVRO Object");
        }

    }
}
