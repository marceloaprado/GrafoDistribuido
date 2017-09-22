/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package GrafoThrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-09-21")
public class Aresta implements org.apache.thrift.TBase<Aresta, Aresta._Fields>, java.io.Serializable, Cloneable, Comparable<Aresta> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Aresta");

  private static final org.apache.thrift.protocol.TField V1_FIELD_DESC = new org.apache.thrift.protocol.TField("v1", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField V2_FIELD_DESC = new org.apache.thrift.protocol.TField("v2", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField PESO_FIELD_DESC = new org.apache.thrift.protocol.TField("peso", org.apache.thrift.protocol.TType.DOUBLE, (short)3);
  private static final org.apache.thrift.protocol.TField IS_DIRECIONADA_FIELD_DESC = new org.apache.thrift.protocol.TField("isDirecionada", org.apache.thrift.protocol.TType.BOOL, (short)4);
  private static final org.apache.thrift.protocol.TField DESCRICAO_FIELD_DESC = new org.apache.thrift.protocol.TField("descricao", org.apache.thrift.protocol.TType.STRING, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ArestaStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ArestaTupleSchemeFactory();

  public Vertice v1; // required
  public Vertice v2; // required
  public double peso; // required
  public boolean isDirecionada; // required
  public java.lang.String descricao; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    V1((short)1, "v1"),
    V2((short)2, "v2"),
    PESO((short)3, "peso"),
    IS_DIRECIONADA((short)4, "isDirecionada"),
    DESCRICAO((short)5, "descricao");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // V1
          return V1;
        case 2: // V2
          return V2;
        case 3: // PESO
          return PESO;
        case 4: // IS_DIRECIONADA
          return IS_DIRECIONADA;
        case 5: // DESCRICAO
          return DESCRICAO;
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
  private static final int __PESO_ISSET_ID = 0;
  private static final int __ISDIRECIONADA_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.V1, new org.apache.thrift.meta_data.FieldMetaData("v1", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Vertice.class)));
    tmpMap.put(_Fields.V2, new org.apache.thrift.meta_data.FieldMetaData("v2", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Vertice.class)));
    tmpMap.put(_Fields.PESO, new org.apache.thrift.meta_data.FieldMetaData("peso", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.IS_DIRECIONADA, new org.apache.thrift.meta_data.FieldMetaData("isDirecionada", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.DESCRICAO, new org.apache.thrift.meta_data.FieldMetaData("descricao", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Aresta.class, metaDataMap);
  }

  public Aresta() {
  }

  public Aresta(
    Vertice v1,
    Vertice v2,
    double peso,
    boolean isDirecionada,
    java.lang.String descricao)
  {
    this();
    this.v1 = v1;
    this.v2 = v2;
    this.peso = peso;
    setPesoIsSet(true);
    this.isDirecionada = isDirecionada;
    setIsDirecionadaIsSet(true);
    this.descricao = descricao;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Aresta(Aresta other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetV1()) {
      this.v1 = new Vertice(other.v1);
    }
    if (other.isSetV2()) {
      this.v2 = new Vertice(other.v2);
    }
    this.peso = other.peso;
    this.isDirecionada = other.isDirecionada;
    if (other.isSetDescricao()) {
      this.descricao = other.descricao;
    }
  }

  public Aresta deepCopy() {
    return new Aresta(this);
  }

  @Override
  public void clear() {
    this.v1 = null;
    this.v2 = null;
    setPesoIsSet(false);
    this.peso = 0.0;
    setIsDirecionadaIsSet(false);
    this.isDirecionada = false;
    this.descricao = null;
  }

  public Vertice getV1() {
    return this.v1;
  }

  public Aresta setV1(Vertice v1) {
    this.v1 = v1;
    return this;
  }

  public void unsetV1() {
    this.v1 = null;
  }

  /** Returns true if field v1 is set (has been assigned a value) and false otherwise */
  public boolean isSetV1() {
    return this.v1 != null;
  }

  public void setV1IsSet(boolean value) {
    if (!value) {
      this.v1 = null;
    }
  }

  public Vertice getV2() {
    return this.v2;
  }

  public Aresta setV2(Vertice v2) {
    this.v2 = v2;
    return this;
  }

  public void unsetV2() {
    this.v2 = null;
  }

  /** Returns true if field v2 is set (has been assigned a value) and false otherwise */
  public boolean isSetV2() {
    return this.v2 != null;
  }

  public void setV2IsSet(boolean value) {
    if (!value) {
      this.v2 = null;
    }
  }

  public double getPeso() {
    return this.peso;
  }

  public Aresta setPeso(double peso) {
    this.peso = peso;
    setPesoIsSet(true);
    return this;
  }

  public void unsetPeso() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __PESO_ISSET_ID);
  }

  /** Returns true if field peso is set (has been assigned a value) and false otherwise */
  public boolean isSetPeso() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __PESO_ISSET_ID);
  }

  public void setPesoIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __PESO_ISSET_ID, value);
  }

  public boolean isIsDirecionada() {
    return this.isDirecionada;
  }

  public Aresta setIsDirecionada(boolean isDirecionada) {
    this.isDirecionada = isDirecionada;
    setIsDirecionadaIsSet(true);
    return this;
  }

  public void unsetIsDirecionada() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ISDIRECIONADA_ISSET_ID);
  }

  /** Returns true if field isDirecionada is set (has been assigned a value) and false otherwise */
  public boolean isSetIsDirecionada() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ISDIRECIONADA_ISSET_ID);
  }

  public void setIsDirecionadaIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ISDIRECIONADA_ISSET_ID, value);
  }

  public java.lang.String getDescricao() {
    return this.descricao;
  }

  public Aresta setDescricao(java.lang.String descricao) {
    this.descricao = descricao;
    return this;
  }

  public void unsetDescricao() {
    this.descricao = null;
  }

  /** Returns true if field descricao is set (has been assigned a value) and false otherwise */
  public boolean isSetDescricao() {
    return this.descricao != null;
  }

  public void setDescricaoIsSet(boolean value) {
    if (!value) {
      this.descricao = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case V1:
      if (value == null) {
        unsetV1();
      } else {
        setV1((Vertice)value);
      }
      break;

    case V2:
      if (value == null) {
        unsetV2();
      } else {
        setV2((Vertice)value);
      }
      break;

    case PESO:
      if (value == null) {
        unsetPeso();
      } else {
        setPeso((java.lang.Double)value);
      }
      break;

    case IS_DIRECIONADA:
      if (value == null) {
        unsetIsDirecionada();
      } else {
        setIsDirecionada((java.lang.Boolean)value);
      }
      break;

    case DESCRICAO:
      if (value == null) {
        unsetDescricao();
      } else {
        setDescricao((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case V1:
      return getV1();

    case V2:
      return getV2();

    case PESO:
      return getPeso();

    case IS_DIRECIONADA:
      return isIsDirecionada();

    case DESCRICAO:
      return getDescricao();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case V1:
      return isSetV1();
    case V2:
      return isSetV2();
    case PESO:
      return isSetPeso();
    case IS_DIRECIONADA:
      return isSetIsDirecionada();
    case DESCRICAO:
      return isSetDescricao();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Aresta)
      return this.equals((Aresta)that);
    return false;
  }

  public boolean equals(Aresta that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_v1 = true && this.isSetV1();
    boolean that_present_v1 = true && that.isSetV1();
    if (this_present_v1 || that_present_v1) {
      if (!(this_present_v1 && that_present_v1))
        return false;
      if (!this.v1.equals(that.v1))
        return false;
    }

    boolean this_present_v2 = true && this.isSetV2();
    boolean that_present_v2 = true && that.isSetV2();
    if (this_present_v2 || that_present_v2) {
      if (!(this_present_v2 && that_present_v2))
        return false;
      if (!this.v2.equals(that.v2))
        return false;
    }

    boolean this_present_peso = true;
    boolean that_present_peso = true;
    if (this_present_peso || that_present_peso) {
      if (!(this_present_peso && that_present_peso))
        return false;
      if (this.peso != that.peso)
        return false;
    }

    boolean this_present_isDirecionada = true;
    boolean that_present_isDirecionada = true;
    if (this_present_isDirecionada || that_present_isDirecionada) {
      if (!(this_present_isDirecionada && that_present_isDirecionada))
        return false;
      if (this.isDirecionada != that.isDirecionada)
        return false;
    }

    boolean this_present_descricao = true && this.isSetDescricao();
    boolean that_present_descricao = true && that.isSetDescricao();
    if (this_present_descricao || that_present_descricao) {
      if (!(this_present_descricao && that_present_descricao))
        return false;
      if (!this.descricao.equals(that.descricao))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetV1()) ? 131071 : 524287);
    if (isSetV1())
      hashCode = hashCode * 8191 + v1.hashCode();

    hashCode = hashCode * 8191 + ((isSetV2()) ? 131071 : 524287);
    if (isSetV2())
      hashCode = hashCode * 8191 + v2.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(peso);

    hashCode = hashCode * 8191 + ((isDirecionada) ? 131071 : 524287);

    hashCode = hashCode * 8191 + ((isSetDescricao()) ? 131071 : 524287);
    if (isSetDescricao())
      hashCode = hashCode * 8191 + descricao.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(Aresta other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetV1()).compareTo(other.isSetV1());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetV1()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.v1, other.v1);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetV2()).compareTo(other.isSetV2());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetV2()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.v2, other.v2);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPeso()).compareTo(other.isSetPeso());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPeso()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.peso, other.peso);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetIsDirecionada()).compareTo(other.isSetIsDirecionada());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIsDirecionada()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.isDirecionada, other.isDirecionada);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDescricao()).compareTo(other.isSetDescricao());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDescricao()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.descricao, other.descricao);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Aresta(");
    boolean first = true;

    sb.append("v1:");
    if (this.v1 == null) {
      sb.append("null");
    } else {
      sb.append(this.v1);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("v2:");
    if (this.v2 == null) {
      sb.append("null");
    } else {
      sb.append(this.v2);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("peso:");
    sb.append(this.peso);
    first = false;
    if (!first) sb.append(", ");
    sb.append("isDirecionada:");
    sb.append(this.isDirecionada);
    first = false;
    if (!first) sb.append(", ");
    sb.append("descricao:");
    if (this.descricao == null) {
      sb.append("null");
    } else {
      sb.append(this.descricao);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (v1 != null) {
      v1.validate();
    }
    if (v2 != null) {
      v2.validate();
    }
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

  private static class ArestaStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ArestaStandardScheme getScheme() {
      return new ArestaStandardScheme();
    }
  }

  private static class ArestaStandardScheme extends org.apache.thrift.scheme.StandardScheme<Aresta> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Aresta struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // V1
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.v1 = new Vertice();
              struct.v1.read(iprot);
              struct.setV1IsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // V2
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.v2 = new Vertice();
              struct.v2.read(iprot);
              struct.setV2IsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // PESO
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.peso = iprot.readDouble();
              struct.setPesoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // IS_DIRECIONADA
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.isDirecionada = iprot.readBool();
              struct.setIsDirecionadaIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // DESCRICAO
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.descricao = iprot.readString();
              struct.setDescricaoIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Aresta struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.v1 != null) {
        oprot.writeFieldBegin(V1_FIELD_DESC);
        struct.v1.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.v2 != null) {
        oprot.writeFieldBegin(V2_FIELD_DESC);
        struct.v2.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(PESO_FIELD_DESC);
      oprot.writeDouble(struct.peso);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(IS_DIRECIONADA_FIELD_DESC);
      oprot.writeBool(struct.isDirecionada);
      oprot.writeFieldEnd();
      if (struct.descricao != null) {
        oprot.writeFieldBegin(DESCRICAO_FIELD_DESC);
        oprot.writeString(struct.descricao);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ArestaTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ArestaTupleScheme getScheme() {
      return new ArestaTupleScheme();
    }
  }

  private static class ArestaTupleScheme extends org.apache.thrift.scheme.TupleScheme<Aresta> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Aresta struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetV1()) {
        optionals.set(0);
      }
      if (struct.isSetV2()) {
        optionals.set(1);
      }
      if (struct.isSetPeso()) {
        optionals.set(2);
      }
      if (struct.isSetIsDirecionada()) {
        optionals.set(3);
      }
      if (struct.isSetDescricao()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetV1()) {
        struct.v1.write(oprot);
      }
      if (struct.isSetV2()) {
        struct.v2.write(oprot);
      }
      if (struct.isSetPeso()) {
        oprot.writeDouble(struct.peso);
      }
      if (struct.isSetIsDirecionada()) {
        oprot.writeBool(struct.isDirecionada);
      }
      if (struct.isSetDescricao()) {
        oprot.writeString(struct.descricao);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Aresta struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.v1 = new Vertice();
        struct.v1.read(iprot);
        struct.setV1IsSet(true);
      }
      if (incoming.get(1)) {
        struct.v2 = new Vertice();
        struct.v2.read(iprot);
        struct.setV2IsSet(true);
      }
      if (incoming.get(2)) {
        struct.peso = iprot.readDouble();
        struct.setPesoIsSet(true);
      }
      if (incoming.get(3)) {
        struct.isDirecionada = iprot.readBool();
        struct.setIsDirecionadaIsSet(true);
      }
      if (incoming.get(4)) {
        struct.descricao = iprot.readString();
        struct.setDescricaoIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

