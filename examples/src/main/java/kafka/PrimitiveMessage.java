package kafka; /**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class PrimitiveMessage extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -3628449665286788554L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PrimitiveMessage\",\"fields\":[{\"name\":\"timestamp\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"query\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"page_number\",\"type\":[\"null\",\"int\"],\"default\":null},{\"name\":\"result_per_page\",\"type\":[\"null\",\"int\"],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<PrimitiveMessage> ENCODER =
      new BinaryMessageEncoder<PrimitiveMessage>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<PrimitiveMessage> DECODER =
      new BinaryMessageDecoder<PrimitiveMessage>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<PrimitiveMessage> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<PrimitiveMessage> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<PrimitiveMessage> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<PrimitiveMessage>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this PrimitiveMessage to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a PrimitiveMessage from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a PrimitiveMessage instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static PrimitiveMessage fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.Long timestamp;
   private java.lang.CharSequence query;
   private java.lang.Integer page_number;
   private java.lang.Integer result_per_page;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PrimitiveMessage() {}

  /**
   * All-args constructor.
   * @param timestamp The new value for timestamp
   * @param query The new value for query
   * @param page_number The new value for page_number
   * @param result_per_page The new value for result_per_page
   */
  public PrimitiveMessage(java.lang.Long timestamp, java.lang.CharSequence query, java.lang.Integer page_number, java.lang.Integer result_per_page) {
    this.timestamp = timestamp;
    this.query = query;
    this.page_number = page_number;
    this.result_per_page = result_per_page;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return timestamp;
    case 1: return query;
    case 2: return page_number;
    case 3: return result_per_page;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: timestamp = (java.lang.Long)value$; break;
    case 1: query = (java.lang.CharSequence)value$; break;
    case 2: page_number = (java.lang.Integer)value$; break;
    case 3: result_per_page = (java.lang.Integer)value$; break;
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
   * Gets the value of the 'query' field.
   * @return The value of the 'query' field.
   */
  public java.lang.CharSequence getQuery() {
    return query;
  }


  /**
   * Sets the value of the 'query' field.
   * @param value the value to set.
   */
  public void setQuery(java.lang.CharSequence value) {
    this.query = value;
  }

  /**
   * Gets the value of the 'page_number' field.
   * @return The value of the 'page_number' field.
   */
  public java.lang.Integer getPageNumber() {
    return page_number;
  }


  /**
   * Sets the value of the 'page_number' field.
   * @param value the value to set.
   */
  public void setPageNumber(java.lang.Integer value) {
    this.page_number = value;
  }

  /**
   * Gets the value of the 'result_per_page' field.
   * @return The value of the 'result_per_page' field.
   */
  public java.lang.Integer getResultPerPage() {
    return result_per_page;
  }


  /**
   * Sets the value of the 'result_per_page' field.
   * @param value the value to set.
   */
  public void setResultPerPage(java.lang.Integer value) {
    this.result_per_page = value;
  }

  /**
   * Creates a new PrimitiveMessage RecordBuilder.
   * @return A new PrimitiveMessage RecordBuilder
   */
  public static PrimitiveMessage.Builder newBuilder() {
    return new PrimitiveMessage.Builder();
  }

  /**
   * Creates a new PrimitiveMessage RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PrimitiveMessage RecordBuilder
   */
  public static PrimitiveMessage.Builder newBuilder(PrimitiveMessage.Builder other) {
    if (other == null) {
      return new PrimitiveMessage.Builder();
    } else {
      return new PrimitiveMessage.Builder(other);
    }
  }

  /**
   * Creates a new PrimitiveMessage RecordBuilder by copying an existing PrimitiveMessage instance.
   * @param other The existing instance to copy.
   * @return A new PrimitiveMessage RecordBuilder
   */
  public static PrimitiveMessage.Builder newBuilder(PrimitiveMessage other) {
    if (other == null) {
      return new PrimitiveMessage.Builder();
    } else {
      return new PrimitiveMessage.Builder(other);
    }
  }

  /**
   * RecordBuilder for PrimitiveMessage instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PrimitiveMessage>
    implements org.apache.avro.data.RecordBuilder<PrimitiveMessage> {

    private java.lang.Long timestamp;
    private java.lang.CharSequence query;
    private java.lang.Integer page_number;
    private java.lang.Integer result_per_page;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(PrimitiveMessage.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[0].schema(), other.timestamp);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.query)) {
        this.query = data().deepCopy(fields()[1].schema(), other.query);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.page_number)) {
        this.page_number = data().deepCopy(fields()[2].schema(), other.page_number);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.result_per_page)) {
        this.result_per_page = data().deepCopy(fields()[3].schema(), other.result_per_page);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing PrimitiveMessage instance
     * @param other The existing instance to copy.
     */
    private Builder(PrimitiveMessage other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[0].schema(), other.timestamp);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.query)) {
        this.query = data().deepCopy(fields()[1].schema(), other.query);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.page_number)) {
        this.page_number = data().deepCopy(fields()[2].schema(), other.page_number);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.result_per_page)) {
        this.result_per_page = data().deepCopy(fields()[3].schema(), other.result_per_page);
        fieldSetFlags()[3] = true;
      }
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
    public PrimitiveMessage.Builder setTimestamp(java.lang.Long value) {
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
    public PrimitiveMessage.Builder clearTimestamp() {
      timestamp = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'query' field.
      * @return The value.
      */
    public java.lang.CharSequence getQuery() {
      return query;
    }


    /**
      * Sets the value of the 'query' field.
      * @param value The value of 'query'.
      * @return This builder.
      */
    public PrimitiveMessage.Builder setQuery(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.query = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'query' field has been set.
      * @return True if the 'query' field has been set, false otherwise.
      */
    public boolean hasQuery() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'query' field.
      * @return This builder.
      */
    public PrimitiveMessage.Builder clearQuery() {
      query = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'page_number' field.
      * @return The value.
      */
    public java.lang.Integer getPageNumber() {
      return page_number;
    }


    /**
      * Sets the value of the 'page_number' field.
      * @param value The value of 'page_number'.
      * @return This builder.
      */
    public PrimitiveMessage.Builder setPageNumber(java.lang.Integer value) {
      validate(fields()[2], value);
      this.page_number = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'page_number' field has been set.
      * @return True if the 'page_number' field has been set, false otherwise.
      */
    public boolean hasPageNumber() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'page_number' field.
      * @return This builder.
      */
    public PrimitiveMessage.Builder clearPageNumber() {
      page_number = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'result_per_page' field.
      * @return The value.
      */
    public java.lang.Integer getResultPerPage() {
      return result_per_page;
    }


    /**
      * Sets the value of the 'result_per_page' field.
      * @param value The value of 'result_per_page'.
      * @return This builder.
      */
    public PrimitiveMessage.Builder setResultPerPage(java.lang.Integer value) {
      validate(fields()[3], value);
      this.result_per_page = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'result_per_page' field has been set.
      * @return True if the 'result_per_page' field has been set, false otherwise.
      */
    public boolean hasResultPerPage() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'result_per_page' field.
      * @return This builder.
      */
    public PrimitiveMessage.Builder clearResultPerPage() {
      result_per_page = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PrimitiveMessage build() {
      try {
        PrimitiveMessage record = new PrimitiveMessage();
        record.timestamp = fieldSetFlags()[0] ? this.timestamp : (java.lang.Long) defaultValue(fields()[0]);
        record.query = fieldSetFlags()[1] ? this.query : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.page_number = fieldSetFlags()[2] ? this.page_number : (java.lang.Integer) defaultValue(fields()[2]);
        record.result_per_page = fieldSetFlags()[3] ? this.result_per_page : (java.lang.Integer) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<PrimitiveMessage>
    WRITER$ = (org.apache.avro.io.DatumWriter<PrimitiveMessage>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PrimitiveMessage>
    READER$ = (org.apache.avro.io.DatumReader<PrimitiveMessage>)MODEL$.createDatumReader(SCHEMA$);

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

    if (this.query == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.query);
    }

    if (this.page_number == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeInt(this.page_number);
    }

    if (this.result_per_page == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeInt(this.result_per_page);
    }

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
        this.query = null;
      } else {
        this.query = in.readString(this.query instanceof Utf8 ? (Utf8)this.query : null);
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.page_number = null;
      } else {
        this.page_number = in.readInt();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.result_per_page = null;
      } else {
        this.result_per_page = in.readInt();
      }

    } else {
      for (int i = 0; i < 4; i++) {
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
            this.query = null;
          } else {
            this.query = in.readString(this.query instanceof Utf8 ? (Utf8)this.query : null);
          }
          break;

        case 2:
          if (in.readIndex() != 1) {
            in.readNull();
            this.page_number = null;
          } else {
            this.page_number = in.readInt();
          }
          break;

        case 3:
          if (in.readIndex() != 1) {
            in.readNull();
            this.result_per_page = null;
          } else {
            this.result_per_page = in.readInt();
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










