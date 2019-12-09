/*
 * Created by Sahil Gandhi
 */

package kafka.avro_serde;

import kafka.Utilities;
import kafka.examples.SerializerType;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

import static kafka.Utilities.BUFFER_SIZE;

public class CustomAvroDeserializer implements Deserializer<GenericRecord> {

    private final DatumReader<GenericRecord> datumReader;
    private final long[] serializedTimes;
    private int currCount;
    private final String filename;

    /**
     * Returns a new instance of {@link CustomAvroDeserializer}
     *
     * @param schema         The AVRO {@link Schema}
     * @param serializerType The serializer type being used
     * @param iterations     The number of iterations this deserializer will run for
     */
    public CustomAvroDeserializer(Schema schema, SerializerType serializerType, int iterations) {
        this.datumReader = new GenericDatumReader<>(schema);
        this.serializedTimes = new long[BUFFER_SIZE];
        this.currCount = 0;
        this.filename = serializerType.toString() + "_" + iterations + "_des.txt";
    }

    @Override
    public GenericRecord deserialize(String topic, byte[] data) {
        long startTime = System.nanoTime();
        BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(data, null);

        try {
            GenericRecord r = this.datumReader.read(null, decoder);
            long endTime = System.nanoTime();
            this.serializedTimes[this.currCount] = endTime - startTime;
            this.currCount++;

            if (this.currCount == BUFFER_SIZE) {
                Utilities.appendToFile(this.filename, this.serializedTimes);
                this.currCount = 0;
            }

            return r;
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializationException("Could not parse AVRO Object");
        }

    }
}
