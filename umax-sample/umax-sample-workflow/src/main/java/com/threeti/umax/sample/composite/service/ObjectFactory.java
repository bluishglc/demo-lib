
package com.threeti.umax.sample.composite.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.threeti.umax.sample.composite.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ExistCreditRiskResponse_QNAME = new QName("http://service.composite.sample.umax.threeti.com/", "existCreditRiskResponse");
    private final static QName _ExistCreditRisk_QNAME = new QName("http://service.composite.sample.umax.threeti.com/", "existCreditRisk");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.threeti.umax.sample.composite.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExistCreditRiskResponse }
     * 
     */
    public ExistCreditRiskResponse createExistCreditRiskResponse() {
        return new ExistCreditRiskResponse();
    }

    /**
     * Create an instance of {@link ExistCreditRisk }
     * 
     */
    public ExistCreditRisk createExistCreditRisk() {
        return new ExistCreditRisk();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExistCreditRiskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.composite.sample.umax.threeti.com/", name = "existCreditRiskResponse")
    public JAXBElement<ExistCreditRiskResponse> createExistCreditRiskResponse(ExistCreditRiskResponse value) {
        return new JAXBElement<ExistCreditRiskResponse>(_ExistCreditRiskResponse_QNAME, ExistCreditRiskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExistCreditRisk }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.composite.sample.umax.threeti.com/", name = "existCreditRisk")
    public JAXBElement<ExistCreditRisk> createExistCreditRisk(ExistCreditRisk value) {
        return new JAXBElement<ExistCreditRisk>(_ExistCreditRisk_QNAME, ExistCreditRisk.class, null, value);
    }

}
