/*
 * Created by Sahil Gandhi
 */

package kafka.thrift_serde;

import kafka.Utilities;
import kafka.examples.SerializerType;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;

import java.lang.reflect.InvocationTargetException;

import static kafka.Utilities.BUFFER_SIZE;

public class CustomThriftDeserializer implements Deserializer<Object> {
    private  Class entityClass;
    private final long[] serializedTimes;
    private int currCount;
    private final String filename;

    /**
     * Returns a new instance of {@link CustomThriftDeserializer}
     *
     * @param serializerType The serializer type being used
     * @param iterations     The number of iterations this deserializer will run for
     */
    public CustomThriftDeserializer(SerializerType serializerType, int iterations){
        this.serializedTimes = new long[BUFFER_SIZE];
        this.currCount = 0;
        this.filename = serializerType.toString() + "_" + iterations + "_des.txt";

        try {
            switch (serializerType){
                case THRIFT1:
                    entityClass = Class.forName("kafka.thrift_serde.generated.PrimitiveMessage");
                    break;
                case THRIFT2:
                    entityClass = Class.forName("kafka.thrift_serde.generated.ComplexMessage");
                    break;
                case THRIFT3:
                    entityClass = Class.forName("kafka.thrift_serde.generated.NestedMessage");
                    break;
                default:
                    throw new ClassNotFoundException("Incorrect serializerType passed in");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object deserialize(String topic, byte[] data) {
        TDeserializer deserializer = new TDeserializer(new TCompactProtocol.Factory());
        try {
            Object obj = this.entityClass.getConstructor().newInstance();

            long startTime = System.nanoTime();
            deserializer.deserialize( (TBase) obj, data);
            long endTime = System.nanoTime();

            this.serializedTimes[this.currCount] = endTime - startTime;
            this.currCount++;

            if (this.currCount == BUFFER_SIZE) {
                Utilities.appendToFile(this.filename, this.serializedTimes);
                this.currCount = 0;
            }

            return obj;
        } catch (TException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
            throw new SerializationException("Could not parse Thrift Object");
        }
    }
}
