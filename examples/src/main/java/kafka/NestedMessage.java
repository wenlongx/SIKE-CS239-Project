package kafka; /**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */

import kafka.PrimitiveMessage;
import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class NestedMessage extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -6322669853561336501L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"NestedMessage\",\"fields\":[{\"name\":\"timestamp\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"id\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"primitiveMsg\",\"type\":{\"type\":\"record\",\"name\":\"PrimitiveMessage\",\"fields\":[{\"name\":\"timestamp\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"query\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"page_number\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"result_per_page\",\"type\":[\"null\",\"int\"],\"default\":null}]}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<NestedMessage> ENCODER =
      new BinaryMessageEncoder<NestedMessage>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<NestedMessage> DECODER =
      new BinaryMessageDecoder<NestedMessage>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<NestedMessage> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<NestedMessage> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<NestedMessage> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<NestedMessage>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this NestedMessage to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a NestedMessage from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a NestedMessage instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static NestedMessage fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.Long timestamp;
   private java.lang.Integer id;
   private PrimitiveMessage primitiveMsg;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public NestedMessage() {}

  /**
   * All-args constructor.
   * @param timestamp The new value for timestamp
   * @param id The new value for id
   * @param primitiveMsg The new value for primitiveMsg
   */
  public NestedMessage(java.lang.Long timestamp, java.lang.Integer id, PrimitiveMessage primitiveMsg) {
    this.timestamp = timestamp;
    this.id = id;
    this.primitiveMsg = primitiveMsg;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return timestamp;
    case 1: return id;
    case 2: return primitiveMsg;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: timestamp = (java.lang.Long)value$; break;
    case 1: id = (java.lang.Integer)value$; break;
    case 2: primitiveMsg = (PrimitiveMessage)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'timestamp' field.
   * @return The value of the 'timestamp' field.
   */
  public java.lang.Long getTimestamp() {
    return timestamp;
  }


  /**
   * Sets the value of the 'timestamp' field.
   * @param value the value to set.
   */
  public void setTimestamp(java.lang.Long value) {
    this.timestamp = value;
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.Integer getId() {
    return id;
  }


  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.Integer value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'primitiveMsg' field.
   * @return The value of the 'primitiveMsg' field.
   */
  public PrimitiveMessage getPrimitiveMsg() {
    return primitiveMsg;
  }


  /**
   * Sets the value of the 'primitiveMsg' field.
   * @param value the value to set.
   */
  public void setPrimitiveMsg(PrimitiveMessage value) {
    this.primitiveMsg = value;
  }

  /**
   * Creates a new NestedMessage RecordBuilder.
   * @return A new NestedMessage RecordBuilder
   */
  public static NestedMessage.Builder newBuilder() {
    return new NestedMessage.Builder();
  }

  /**
   * Creates a new NestedMessage RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new NestedMessage RecordBuilder
   */
  public static NestedMessage.Builder newBuilder(NestedMessage.Builder other) {
    if (other == null) {
      return new NestedMessage.Builder();
    } else {
      return new NestedMessage.Builder(other);
    }
  }

  /**
   * Creates a new NestedMessage RecordBuilder by copying an existing NestedMessage instance.
   * @param other The existing instance to copy.
   * @return A new NestedMessage RecordBuilder
   */
  public static NestedMessage.Builder newBuilder(NestedMessage other) {
    if (other == null) {
      return new NestedMessage.Builder();
    } else {
      return new NestedMessage.Builder(other);
    }
  }

  /**
   * RecordBuilder for NestedMessage instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<NestedMessage>
    implements org.apache.avro.data.RecordBuilder<NestedMessage> {

    private java.lang.Long timestamp;
    private java.lang.Integer id;
    private PrimitiveMessage primitiveMsg;
    private PrimitiveMessage.Builder primitiveMsgBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(NestedMessage.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[0].schema(), other.timestamp);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.id)) {
        this.id = data().deepCopy(fields()[1].schema(), other.id);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.primitiveMsg)) {
        this.primitiveMsg = data().deepCopy(fields()[2].schema(), other.primitiveMsg);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (other.hasPrimitiveMsgBuilder()) {
        this.primitiveMsgBuilder = PrimitiveMessage.newBuilder(other.getPrimitiveMsgBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing NestedMessage instance
     * @param other The existing instance to copy.
     */
    private Builder(NestedMessage other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[0].schema(), other.timestamp);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.id)) {
        this.id = data().deepCopy(fields()[1].schema(), other.id);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.primitiveMsg)) {
        this.primitiveMsg = data().deepCopy(fields()[2].schema(), other.primitiveMsg);
        fieldSetFlags()[2] = true;
      }
      this.primitiveMsgBuilder = null;
    }

    /**
      * Gets the value of the 'timestamp' field.
      * @return The value.
      */
    public java.lang.Long getTimestamp() {
      return timestamp;
    }


    /**
      * Sets the value of the 'timestamp' field.
      * @param value The value of 'timestamp'.
      * @return This builder.
      */
    public NestedMessage.Builder setTimestamp(java.lang.Long value) {
      validate(fields()[0], value);
      this.timestamp = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'timestamp' field has been set.
      * @return True if the 'timestamp' field has been set, false otherwise.
      */
    public boolean hasTimestamp() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'timestamp' field.
      * @return This builder.
      */
    public NestedMessage.Builder clearTimestamp() {
      timestamp = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.Integer getId() {
      return id;
    }


    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public NestedMessage.Builder setId(java.lang.Integer value) {
      validate(fields()[1], value);
      this.id = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public NestedMessage.Builder clearId() {
      id = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'primitiveMsg' field.
      * @return The value.
      */
    public PrimitiveMessage getPrimitiveMsg() {
      return primitiveMsg;
    }


    /**
      * Sets the value of the 'primitiveMsg' field.
      * @param value The value of 'primitiveMsg'.
      * @return This builder.
      */
    public NestedMessage.Builder setPrimitiveMsg(PrimitiveMessage value) {
      validate(fields()[2], value);
      this.primitiveMsgBuilder = null;
      this.primitiveMsg = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'primitiveMsg' field has been set.
      * @return True if the 'primitiveMsg' field has been set, false otherwise.
      */
    public boolean hasPrimitiveMsg() {
      return fieldSetFlags()[2];
    }

    /**
     * Gets the Builder instance for the 'primitiveMsg' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public PrimitiveMessage.Builder getPrimitiveMsgBuilder() {
      if (primitiveMsgBuilder == null) {
        if (hasPrimitiveMsg()) {
          setPrimitiveMsgBuilder(PrimitiveMessage.newBuilder(primitiveMsg));
        } else {
          setPrimitiveMsgBuilder(PrimitiveMessage.newBuilder());
        }
      }
      return primitiveMsgBuilder;
    }

    /**
     * Sets the Builder instance for the 'primitiveMsg' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */
    public NestedMessage.Builder setPrimitiveMsgBuilder(PrimitiveMessage.Builder value) {
      clearPrimitiveMsg();
      primitiveMsgBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'primitiveMsg' field has an active Builder instance
     * @return True if the 'primitiveMsg' field has an active Builder instance
     */
    public boolean hasPrimitiveMsgBuilder() {
      return primitiveMsgBuilder != null;
    }

    /**
      * Clears the value of the 'primitiveMsg' field.
      * @return This builder.
      */
    public NestedMessage.Builder clearPrimitiveMsg() {
      primitiveMsg = null;
      primitiveMsgBuilder = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public NestedMessage build() {
      try {
        NestedMessage record = new NestedMessage();
        record.timestamp = fieldSetFlags()[0] ? this.timestamp : (java.lang.Long) defaultValue(fields()[0]);
        record.id = fieldSetFlags()[1] ? this.id : (java.lang.Integer) defaultValue(fields()[1]);
        if (primitiveMsgBuilder != null) {
          try {
            record.primitiveMsg = this.primitiveMsgBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("primitiveMsg"));
            throw e;
          }
        } else {
          record.primitiveMsg = fieldSetFlags()[2] ? this.primitiveMsg : (PrimitiveMessage) defaultValue(fields()[2]);
        }
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<NestedMessage>
    WRITER$ = (org.apache.avro.io.DatumWriter<NestedMessage>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<NestedMessage>
    READER$ = (org.apache.avro.io.DatumReader<NestedMessage>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    if (this.timestamp == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeLong(this.timestamp);
    }

    if (this.id == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeInt(this.id);
    }

    this.primitiveMsg.customEncode(out);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      if (in.readIndex() != 1) {
        in.readNull();
        this.timestamp = null;
      } else {
        this.timestamp = in.readLong();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.id = null;
      } else {
        this.id = in.readInt();
      }

      if (this.primitiveMsg == null) {
        this.primitiveMsg = new PrimitiveMessage();
      }
      this.primitiveMsg.customDecode(in);

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          if (in.readIndex() != 1) {
            in.readNull();
            this.timestamp = null;
          } else {
            this.timestamp = in.readLong();
          }
          break;

        case 1:
          if (in.readIndex() != 1) {
            in.readNull();
            this.id = null;
          } else {
            this.id = in.readInt();
          }
          break;

        case 2:
          if (this.primitiveMsg == null) {
            this.primitiveMsg = new PrimitiveMessage();
          }
          this.primitiveMsg.customDecode(in);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










