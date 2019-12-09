/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package kafka.thrift_serde.generated;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2019-12-07")
public class PrimitiveMessage implements org.apache.thrift.TBase<PrimitiveMessage, PrimitiveMessage._Fields>, java.io.Serializable, Cloneable, Comparable<PrimitiveMessage> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("PrimitiveMessage");

  private static final org.apache.thrift.protocol.TField TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("timestamp", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField QUERY_FIELD_DESC = new org.apache.thrift.protocol.TField("query", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField PAGE_NUMBER_FIELD_DESC = new org.apache.thrift.protocol.TField("pageNumber", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField RESULT_PER_PAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("resultPerPage", org.apache.thrift.protocol.TType.I32, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new PrimitiveMessageStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new PrimitiveMessageTupleSchemeFactory();

  public long timestamp; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String query; // required
  public int pageNumber; // required
  public int resultPerPage; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TIMESTAMP((short)1, "timestamp"),
    QUERY((short)2, "query"),
    PAGE_NUMBER((short)3, "pageNumber"),
    RESULT_PER_PAGE((short)4, "resultPerPage");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // TIMESTAMP
          return TIMESTAMP;
        case 2: // QUERY
          return QUERY;
        case 3: // PAGE_NUMBER
          return PAGE_NUMBER;
        case 4: // RESULT_PER_PAGE
          return RESULT_PER_PAGE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __TIMESTAMP_ISSET_ID = 0;
  private static final int __PAGENUMBER_ISSET_ID = 1;
  private static final int __RESULTPERPAGE_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("timestamp", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.QUERY, new org.apache.thrift.meta_data.FieldMetaData("query", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PAGE_NUMBER, new org.apache.thrift.meta_data.FieldMetaData("pageNumber", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.RESULT_PER_PAGE, new org.apache.thrift.meta_data.FieldMetaData("resultPerPage", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(PrimitiveMessage.class, metaDataMap);
  }

  public PrimitiveMessage() {
  }

  public PrimitiveMessage(
    long timestamp,
    java.lang.String query,
    int pageNumber,
    int resultPerPage)
  {
    this();
    this.timestamp = timestamp;
    setTimestampIsSet(true);
    this.query = query;
    this.pageNumber = pageNumber;
    setPageNumberIsSet(true);
    this.resultPerPage = resultPerPage;
    setResultPerPageIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public PrimitiveMessage(PrimitiveMessage other) {
    __isset_bitfield = other.__isset_bitfield;
    this.timestamp = other.timestamp;
    if (other.isSetQuery()) {
      this.query = other.query;
    }
    this.pageNumber = other.pageNumber;
    this.resultPerPage = other.resultPerPage;
  }

  public PrimitiveMessage deepCopy() {
    return new PrimitiveMessage(this);
  }

  @Override
  public void clear() {
    setTimestampIsSet(false);
    this.timestamp = 0;
    this.query = null;
    setPageNumberIsSet(false);
    this.pageNumber = 0;
    setResultPerPageIsSet(false);
    this.resultPerPage = 0;
  }

  public long getTimestamp() {
    return this.timestamp;
  }

  public PrimitiveMessage setTimestamp(long timestamp) {
    this.timestamp = timestamp;
    setTimestampIsSet(true);
    return this;
  }

  public void unsetTimestamp() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TIMESTAMP_ISSET_ID);
  }

  /** Returns true if field timestamp is set (has been assigned a value) and false otherwise */
  public boolean isSetTimestamp() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TIMESTAMP_ISSET_ID);
  }

  public void setTimestampIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TIMESTAMP_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getQuery() {
    return this.query;
  }

  public PrimitiveMessage setQuery(@org.apache.thrift.annotation.Nullable java.lang.String query) {
    this.query = query;
    return this;
  }

  public void unsetQuery() {
    this.query = null;
  }

  /** Returns true if field query is set (has been assigned a value) and false otherwise */
  public boolean isSetQuery() {
    return this.query != null;
  }

  public void setQueryIsSet(boolean value) {
    if (!value) {
      this.query = null;
    }
  }

  public int getPageNumber() {
    return this.pageNumber;
  }

  public PrimitiveMessage setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
    setPageNumberIsSet(true);
    return this;
  }

  public void unsetPageNumber() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __PAGENUMBER_ISSET_ID);
  }

  /** Returns true if field pageNumber is set (has been assigned a value) and false otherwise */
  public boolean isSetPageNumber() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __PAGENUMBER_ISSET_ID);
  }

  public void setPageNumberIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __PAGENUMBER_ISSET_ID, value);
  }

  public int getResultPerPage() {
    return this.resultPerPage;
  }

  public PrimitiveMessage setResultPerPage(int resultPerPage) {
    this.resultPerPage = resultPerPage;
    setResultPerPageIsSet(true);
    return this;
  }

  public void unsetResultPerPage() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __RESULTPERPAGE_ISSET_ID);
  }

  /** Returns true if field resultPerPage is set (has been assigned a value) and false otherwise */
  public boolean isSetResultPerPage() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __RESULTPERPAGE_ISSET_ID);
  }

  public void setResultPerPageIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __RESULTPERPAGE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case TIMESTAMP:
      if (value == null) {
        unsetTimestamp();
      } else {
        setTimestamp((java.lang.Long)value);
      }
      break;

    case QUERY:
      if (value == null) {
        unsetQuery();
      } else {
        setQuery((java.lang.String)value);
      }
      break;

    case PAGE_NUMBER:
      if (value == null) {
        unsetPageNumber();
      } else {
        setPageNumber((java.lang.Integer)value);
      }
      break;

    case RESULT_PER_PAGE:
      if (value == null) {
        unsetResultPerPage();
      } else {
        setResultPerPage((java.lang.Integer)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case TIMESTAMP:
      return getTimestamp();

    case QUERY:
      return getQuery();

    case PAGE_NUMBER:
      return getPageNumber();

    case RESULT_PER_PAGE:
      return getResultPerPage();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case TIMESTAMP:
      return isSetTimestamp();
    case QUERY:
      return isSetQuery();
    case PAGE_NUMBER:
      return isSetPageNumber();
    case RESULT_PER_PAGE:
      return isSetResultPerPage();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof PrimitiveMessage)
      return this.equals((PrimitiveMessage)that);
    return false;
  }

  public boolean equals(PrimitiveMessage that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_timestamp = true;
    boolean that_present_timestamp = true;
    if (this_present_timestamp || that_present_timestamp) {
      if (!(this_present_timestamp && that_present_timestamp))
        return false;
      if (this.timestamp != that.timestamp)
        return false;
    }

    boolean this_present_query = true && this.isSetQuery();
    boolean that_present_query = true && that.isSetQuery();
    if (this_present_query || that_present_query) {
      if (!(this_present_query && that_present_query))
        return false;
      if (!this.query.equals(that.query))
        return false;
    }

    boolean this_present_pageNumber = true;
    boolean that_present_pageNumber = true;
    if (this_present_pageNumber || that_present_pageNumber) {
      if (!(this_present_pageNumber && that_present_pageNumber))
        return false;
      if (this.pageNumber != that.pageNumber)
        return false;
    }

    boolean this_present_resultPerPage = true;
    boolean that_present_resultPerPage = true;
    if (this_present_resultPerPage || that_present_resultPerPage) {
      if (!(this_present_resultPerPage && that_present_resultPerPage))
        return false;
      if (this.resultPerPage != that.resultPerPage)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(timestamp);

    hashCode = hashCode * 8191 + ((isSetQuery()) ? 131071 : 524287);
    if (isSetQuery())
      hashCode = hashCode * 8191 + query.hashCode();

    hashCode = hashCode * 8191 + pageNumber;

    hashCode = hashCode * 8191 + resultPerPage;

    return hashCode;
  }

  @Override
  public int compareTo(PrimitiveMessage other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetTimestamp()).compareTo(other.isSetTimestamp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTimestamp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.timestamp, other.timestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetQuery()).compareTo(other.isSetQuery());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetQuery()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.query, other.query);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPageNumber()).compareTo(other.isSetPageNumber());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPageNumber()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pageNumber, other.pageNumber);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetResultPerPage()).compareTo(other.isSetResultPerPage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResultPerPage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.resultPerPage, other.resultPerPage);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("PrimitiveMessage(");
    boolean first = true;

    sb.append("timestamp:");
    sb.append(this.timestamp);
    first = false;
    if (!first) sb.append(", ");
    sb.append("query:");
    if (this.query == null) {
      sb.append("null");
    } else {
      sb.append(this.query);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("pageNumber:");
    sb.append(this.pageNumber);
    first = false;
    if (!first) sb.append(", ");
    sb.append("resultPerPage:");
    sb.append(this.resultPerPage);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class PrimitiveMessageStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public PrimitiveMessageStandardScheme getScheme() {
      return new PrimitiveMessageStandardScheme();
    }
  }

  private static class PrimitiveMessageStandardScheme extends org.apache.thrift.scheme.StandardScheme<PrimitiveMessage> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, PrimitiveMessage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.timestamp = iprot.readI64();
              struct.setTimestampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // QUERY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.query = iprot.readString();
              struct.setQueryIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // PAGE_NUMBER
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.pageNumber = iprot.readI32();
              struct.setPageNumberIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // RESULT_PER_PAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.resultPerPage = iprot.readI32();
              struct.setResultPerPageIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, PrimitiveMessage struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
      oprot.writeI64(struct.timestamp);
      oprot.writeFieldEnd();
      if (struct.query != null) {
        oprot.writeFieldBegin(QUERY_FIELD_DESC);
        oprot.writeString(struct.query);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(PAGE_NUMBER_FIELD_DESC);
      oprot.writeI32(struct.pageNumber);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(RESULT_PER_PAGE_FIELD_DESC);
      oprot.writeI32(struct.resultPerPage);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class PrimitiveMessageTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public PrimitiveMessageTupleScheme getScheme() {
      return new PrimitiveMessageTupleScheme();
    }
  }

  private static class PrimitiveMessageTupleScheme extends org.apache.thrift.scheme.TupleScheme<PrimitiveMessage> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, PrimitiveMessage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetTimestamp()) {
        optionals.set(0);
      }
      if (struct.isSetQuery()) {
        optionals.set(1);
      }
      if (struct.isSetPageNumber()) {
        optionals.set(2);
      }
      if (struct.isSetResultPerPage()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetTimestamp()) {
        oprot.writeI64(struct.timestamp);
      }
      if (struct.isSetQuery()) {
        oprot.writeString(struct.query);
      }
      if (struct.isSetPageNumber()) {
        oprot.writeI32(struct.pageNumber);
      }
      if (struct.isSetResultPerPage()) {
        oprot.writeI32(struct.resultPerPage);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, PrimitiveMessage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.timestamp = iprot.readI64();
        struct.setTimestampIsSet(true);
      }
      if (incoming.get(1)) {
        struct.query = iprot.readString();
        struct.setQueryIsSet(true);
      }
      if (incoming.get(2)) {
        struct.pageNumber = iprot.readI32();
        struct.setPageNumberIsSet(true);
      }
      if (incoming.get(3)) {
        struct.resultPerPage = iprot.readI32();
        struct.setResultPerPageIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
