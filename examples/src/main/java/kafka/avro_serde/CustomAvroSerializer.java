/*
 * Created by Sahil Gandhi
 */

package kafka.avro_serde;

import kafka.Utilities;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static kafka.Utilities.BUFFER_SIZE;

public class CustomAvroSerializer implements Serializer<GenericRecord> {

    private final DatumWriter<GenericRecord> datumWriter;
    private final long [] serializedTimes;
    private int currCount;
    /**
     * Returns a new instance of {@link CustomAvroSerializer}
     *
     * @param schema The AVRO {@link Schema}
     */
    public CustomAvroSerializer(Schema schema) {
        this.datumWriter = new GenericDatumWriter<>(schema);
        this.serializedTimes = new long[BUFFER_SIZE];
        this.currCount = 0;
    }

    @Override
    public byte[] serialize(String topic, GenericRecord data) {
        long startTime = System.nanoTime();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        BinaryEncoder encoder = null;
        encoder = EncoderFactory.get().binaryEncoder(stream, null);

        try {
            this.datumWriter.write(data, encoder);
            encoder.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] arr = stream.toByteArray();

        long endTime = System.nanoTime();

        this.serializedTimes[this.currCount] = endTime - startTime;
        this.currCount++;

        if (this.currCount == BUFFER_SIZE){
            Utilities.appendToFile("avro_ser.txt",  this.serializedTimes);
            this.currCount = 0;
        }

        return arr;
    }
}
