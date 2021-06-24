/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.pelion.kafka.connect;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class PayloadData extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -5669443243273934200L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PayloadData\",\"namespace\":\"com.pelion.kafka.connect\",\"fields\":[{\"name\":\"s\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"l\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"d\",\"type\":[\"null\",\"double\"],\"default\":null},{\"name\":\"b\",\"type\":[\"null\",\"boolean\"],\"default\":null}],\"connect.version\":1,\"connect.name\":\"com.pelion.kafka.connect.PayloadData\"}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<PayloadData> ENCODER =
      new BinaryMessageEncoder<PayloadData>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<PayloadData> DECODER =
      new BinaryMessageDecoder<PayloadData>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<PayloadData> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<PayloadData> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<PayloadData> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<PayloadData>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this PayloadData to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a PayloadData from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a PayloadData instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static PayloadData fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.String s;
   private java.lang.Long l;
   private java.lang.Double d;
   private java.lang.Boolean b;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PayloadData() {}

  /**
   * All-args constructor.
   * @param s The new value for s
   * @param l The new value for l
   * @param d The new value for d
   * @param b The new value for b
   */
  public PayloadData(java.lang.String s, java.lang.Long l, java.lang.Double d, java.lang.Boolean b) {
    this.s = s;
    this.l = l;
    this.d = d;
    this.b = b;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return s;
    case 1: return l;
    case 2: return d;
    case 3: return b;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: s = value$ != null ? value$.toString() : null; break;
    case 1: l = (java.lang.Long)value$; break;
    case 2: d = (java.lang.Double)value$; break;
    case 3: b = (java.lang.Boolean)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 's' field.
   * @return The value of the 's' field.
   */
  public java.lang.String getS() {
    return s;
  }


  /**
   * Sets the value of the 's' field.
   * @param value the value to set.
   */
  public void setS(java.lang.String value) {
    this.s = value;
  }

  /**
   * Gets the value of the 'l' field.
   * @return The value of the 'l' field.
   */
  public java.lang.Long getL() {
    return l;
  }


  /**
   * Sets the value of the 'l' field.
   * @param value the value to set.
   */
  public void setL(java.lang.Long value) {
    this.l = value;
  }

  /**
   * Gets the value of the 'd' field.
   * @return The value of the 'd' field.
   */
  public java.lang.Double getD() {
    return d;
  }


  /**
   * Sets the value of the 'd' field.
   * @param value the value to set.
   */
  public void setD(java.lang.Double value) {
    this.d = value;
  }

  /**
   * Gets the value of the 'b' field.
   * @return The value of the 'b' field.
   */
  public java.lang.Boolean getB() {
    return b;
  }


  /**
   * Sets the value of the 'b' field.
   * @param value the value to set.
   */
  public void setB(java.lang.Boolean value) {
    this.b = value;
  }

  /**
   * Creates a new PayloadData RecordBuilder.
   * @return A new PayloadData RecordBuilder
   */
  public static com.pelion.kafka.connect.PayloadData.Builder newBuilder() {
    return new com.pelion.kafka.connect.PayloadData.Builder();
  }

  /**
   * Creates a new PayloadData RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PayloadData RecordBuilder
   */
  public static com.pelion.kafka.connect.PayloadData.Builder newBuilder(com.pelion.kafka.connect.PayloadData.Builder other) {
    if (other == null) {
      return new com.pelion.kafka.connect.PayloadData.Builder();
    } else {
      return new com.pelion.kafka.connect.PayloadData.Builder(other);
    }
  }

  /**
   * Creates a new PayloadData RecordBuilder by copying an existing PayloadData instance.
   * @param other The existing instance to copy.
   * @return A new PayloadData RecordBuilder
   */
  public static com.pelion.kafka.connect.PayloadData.Builder newBuilder(com.pelion.kafka.connect.PayloadData other) {
    if (other == null) {
      return new com.pelion.kafka.connect.PayloadData.Builder();
    } else {
      return new com.pelion.kafka.connect.PayloadData.Builder(other);
    }
  }

  /**
   * RecordBuilder for PayloadData instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PayloadData>
    implements org.apache.avro.data.RecordBuilder<PayloadData> {

    private java.lang.String s;
    private java.lang.Long l;
    private java.lang.Double d;
    private java.lang.Boolean b;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.pelion.kafka.connect.PayloadData.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.s)) {
        this.s = data().deepCopy(fields()[0].schema(), other.s);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.l)) {
        this.l = data().deepCopy(fields()[1].schema(), other.l);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.d)) {
        this.d = data().deepCopy(fields()[2].schema(), other.d);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.b)) {
        this.b = data().deepCopy(fields()[3].schema(), other.b);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing PayloadData instance
     * @param other The existing instance to copy.
     */
    private Builder(com.pelion.kafka.connect.PayloadData other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.s)) {
        this.s = data().deepCopy(fields()[0].schema(), other.s);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.l)) {
        this.l = data().deepCopy(fields()[1].schema(), other.l);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.d)) {
        this.d = data().deepCopy(fields()[2].schema(), other.d);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.b)) {
        this.b = data().deepCopy(fields()[3].schema(), other.b);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 's' field.
      * @return The value.
      */
    public java.lang.String getS() {
      return s;
    }


    /**
      * Sets the value of the 's' field.
      * @param value The value of 's'.
      * @return This builder.
      */
    public com.pelion.kafka.connect.PayloadData.Builder setS(java.lang.String value) {
      validate(fields()[0], value);
      this.s = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 's' field has been set.
      * @return True if the 's' field has been set, false otherwise.
      */
    public boolean hasS() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 's' field.
      * @return This builder.
      */
    public com.pelion.kafka.connect.PayloadData.Builder clearS() {
      s = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'l' field.
      * @return The value.
      */
    public java.lang.Long getL() {
      return l;
    }


    /**
      * Sets the value of the 'l' field.
      * @param value The value of 'l'.
      * @return This builder.
      */
    public com.pelion.kafka.connect.PayloadData.Builder setL(java.lang.Long value) {
      validate(fields()[1], value);
      this.l = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'l' field has been set.
      * @return True if the 'l' field has been set, false otherwise.
      */
    public boolean hasL() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'l' field.
      * @return This builder.
      */
    public com.pelion.kafka.connect.PayloadData.Builder clearL() {
      l = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'd' field.
      * @return The value.
      */
    public java.lang.Double getD() {
      return d;
    }


    /**
      * Sets the value of the 'd' field.
      * @param value The value of 'd'.
      * @return This builder.
      */
    public com.pelion.kafka.connect.PayloadData.Builder setD(java.lang.Double value) {
      validate(fields()[2], value);
      this.d = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'd' field has been set.
      * @return True if the 'd' field has been set, false otherwise.
      */
    public boolean hasD() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'd' field.
      * @return This builder.
      */
    public com.pelion.kafka.connect.PayloadData.Builder clearD() {
      d = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'b' field.
      * @return The value.
      */
    public java.lang.Boolean getB() {
      return b;
    }


    /**
      * Sets the value of the 'b' field.
      * @param value The value of 'b'.
      * @return This builder.
      */
    public com.pelion.kafka.connect.PayloadData.Builder setB(java.lang.Boolean value) {
      validate(fields()[3], value);
      this.b = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'b' field has been set.
      * @return True if the 'b' field has been set, false otherwise.
      */
    public boolean hasB() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'b' field.
      * @return This builder.
      */
    public com.pelion.kafka.connect.PayloadData.Builder clearB() {
      b = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PayloadData build() {
      try {
        PayloadData record = new PayloadData();
        record.s = fieldSetFlags()[0] ? this.s : (java.lang.String) defaultValue(fields()[0]);
        record.l = fieldSetFlags()[1] ? this.l : (java.lang.Long) defaultValue(fields()[1]);
        record.d = fieldSetFlags()[2] ? this.d : (java.lang.Double) defaultValue(fields()[2]);
        record.b = fieldSetFlags()[3] ? this.b : (java.lang.Boolean) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<PayloadData>
    WRITER$ = (org.apache.avro.io.DatumWriter<PayloadData>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PayloadData>
    READER$ = (org.apache.avro.io.DatumReader<PayloadData>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    if (this.s == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.s);
    }

    if (this.l == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeLong(this.l);
    }

    if (this.d == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeDouble(this.d);
    }

    if (this.b == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeBoolean(this.b);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      if (in.readIndex() != 1) {
        in.readNull();
        this.s = null;
      } else {
        this.s = in.readString();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.l = null;
      } else {
        this.l = in.readLong();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.d = null;
      } else {
        this.d = in.readDouble();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.b = null;
      } else {
        this.b = in.readBoolean();
      }

    } else {
      for (int i = 0; i < 4; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          if (in.readIndex() != 1) {
            in.readNull();
            this.s = null;
          } else {
            this.s = in.readString();
          }
          break;

        case 1:
          if (in.readIndex() != 1) {
            in.readNull();
            this.l = null;
          } else {
            this.l = in.readLong();
          }
          break;

        case 2:
          if (in.readIndex() != 1) {
            in.readNull();
            this.d = null;
          } else {
            this.d = in.readDouble();
          }
          break;

        case 3:
          if (in.readIndex() != 1) {
            in.readNull();
            this.b = null;
          } else {
            this.b = in.readBoolean();
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










