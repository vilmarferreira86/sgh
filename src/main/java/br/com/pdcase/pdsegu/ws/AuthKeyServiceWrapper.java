/**
 * AuthKeyServiceWrapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.pdcase.pdsegu.ws;

public class AuthKeyServiceWrapper  extends br.com.pdcase.pdsegu.ws.ServiceWrapper  implements java.io.Serializable {
    private java.lang.String authKey;

    public AuthKeyServiceWrapper() {
    }

    public AuthKeyServiceWrapper(
           java.lang.String mensagem,
           java.lang.String resultado,
           java.lang.String authKey) {
        super(
            mensagem,
            resultado);
        this.authKey = authKey;
    }


    /**
     * Gets the authKey value for this AuthKeyServiceWrapper.
     * 
     * @return authKey
     */
    public java.lang.String getAuthKey() {
        return authKey;
    }


    /**
     * Sets the authKey value for this AuthKeyServiceWrapper.
     * 
     * @param authKey
     */
    public void setAuthKey(java.lang.String authKey) {
        this.authKey = authKey;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AuthKeyServiceWrapper)) return false;
        AuthKeyServiceWrapper other = (AuthKeyServiceWrapper) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.authKey==null && other.getAuthKey()==null) || 
             (this.authKey!=null &&
              this.authKey.equals(other.getAuthKey())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getAuthKey() != null) {
            _hashCode += getAuthKey().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AuthKeyServiceWrapper.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.pdsegu.pdcase.com.br", "authKeyServiceWrapper"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authKey");
        elemField.setXmlName(new javax.xml.namespace.QName("", "authKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
