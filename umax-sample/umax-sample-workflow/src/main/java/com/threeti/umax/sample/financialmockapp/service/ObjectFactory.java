
package com.threeti.umax.sample.financialmockapp.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.threeti.umax.sample.financialmockapp.service package. 
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

    private final static QName _Credit_QNAME = new QName("http://service.financialmockapp.sample.umax.threeti.com/", "credit");
    private final static QName _Customer_QNAME = new QName("http://service.financialmockapp.sample.umax.threeti.com/", "customer");
    private final static QName _SaveCreditResponse_QNAME = new QName("http://service.financialmockapp.sample.umax.threeti.com/", "saveCreditResponse");
    private final static QName _GetCustomerCredit_QNAME = new QName("http://service.financialmockapp.sample.umax.threeti.com/", "getCustomerCredit");
    private final static QName _GetCreditsResponse_QNAME = new QName("http://service.financialmockapp.sample.umax.threeti.com/", "getCreditsResponse");
    private final static QName _GetCustomerCreditResponse_QNAME = new QName("http://service.financialmockapp.sample.umax.threeti.com/", "getCustomerCreditResponse");
    private final static QName _GetCreditResponse_QNAME = new QName("http://service.financialmockapp.sample.umax.threeti.com/", "getCreditResponse");
    private final static QName _GetCredits_QNAME = new QName("http://service.financialmockapp.sample.umax.threeti.com/", "getCredits");
    private final static QName _SaveCredit_QNAME = new QName("http://service.financialmockapp.sample.umax.threeti.com/", "saveCredit");
    private final static QName _GetCredit_QNAME = new QName("http://service.financialmockapp.sample.umax.threeti.com/", "getCredit");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.threeti.umax.sample.financialmockapp.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCredit }
     * 
     */
    public GetCredit createGetCredit() {
        return new GetCredit();
    }

    /**
     * Create an instance of {@link GetCreditResponse }
     * 
     */
    public GetCreditResponse createGetCreditResponse() {
        return new GetCreditResponse();
    }

    /**
     * Create an instance of {@link SaveCredit }
     * 
     */
    public SaveCredit createSaveCredit() {
        return new SaveCredit();
    }

    /**
     * Create an instance of {@link GetCredits }
     * 
     */
    public GetCredits createGetCredits() {
        return new GetCredits();
    }

    /**
     * Create an instance of {@link GetCustomerCreditResponse }
     * 
     */
    public GetCustomerCreditResponse createGetCustomerCreditResponse() {
        return new GetCustomerCreditResponse();
    }

    /**
     * Create an instance of {@link GetCreditsResponse }
     * 
     */
    public GetCreditsResponse createGetCreditsResponse() {
        return new GetCreditsResponse();
    }

    /**
     * Create an instance of {@link GetCustomerCredit }
     * 
     */
    public GetCustomerCredit createGetCustomerCredit() {
        return new GetCustomerCredit();
    }

    /**
     * Create an instance of {@link SaveCreditResponse }
     * 
     */
    public SaveCreditResponse createSaveCreditResponse() {
        return new SaveCreditResponse();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link Credit }
     * 
     */
    public Credit createCredit() {
        return new Credit();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Credit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.financialmockapp.sample.umax.threeti.com/", name = "credit")
    public JAXBElement<Credit> createCredit(Credit value) {
        return new JAXBElement<Credit>(_Credit_QNAME, Credit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Customer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.financialmockapp.sample.umax.threeti.com/", name = "customer")
    public JAXBElement<Customer> createCustomer(Customer value) {
        return new JAXBElement<Customer>(_Customer_QNAME, Customer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveCreditResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.financialmockapp.sample.umax.threeti.com/", name = "saveCreditResponse")
    public JAXBElement<SaveCreditResponse> createSaveCreditResponse(SaveCreditResponse value) {
        return new JAXBElement<SaveCreditResponse>(_SaveCreditResponse_QNAME, SaveCreditResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCustomerCredit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.financialmockapp.sample.umax.threeti.com/", name = "getCustomerCredit")
    public JAXBElement<GetCustomerCredit> createGetCustomerCredit(GetCustomerCredit value) {
        return new JAXBElement<GetCustomerCredit>(_GetCustomerCredit_QNAME, GetCustomerCredit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCreditsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.financialmockapp.sample.umax.threeti.com/", name = "getCreditsResponse")
    public JAXBElement<GetCreditsResponse> createGetCreditsResponse(GetCreditsResponse value) {
        return new JAXBElement<GetCreditsResponse>(_GetCreditsResponse_QNAME, GetCreditsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCustomerCreditResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.financialmockapp.sample.umax.threeti.com/", name = "getCustomerCreditResponse")
    public JAXBElement<GetCustomerCreditResponse> createGetCustomerCreditResponse(GetCustomerCreditResponse value) {
        return new JAXBElement<GetCustomerCreditResponse>(_GetCustomerCreditResponse_QNAME, GetCustomerCreditResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCreditResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.financialmockapp.sample.umax.threeti.com/", name = "getCreditResponse")
    public JAXBElement<GetCreditResponse> createGetCreditResponse(GetCreditResponse value) {
        return new JAXBElement<GetCreditResponse>(_GetCreditResponse_QNAME, GetCreditResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCredits }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.financialmockapp.sample.umax.threeti.com/", name = "getCredits")
    public JAXBElement<GetCredits> createGetCredits(GetCredits value) {
        return new JAXBElement<GetCredits>(_GetCredits_QNAME, GetCredits.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveCredit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.financialmockapp.sample.umax.threeti.com/", name = "saveCredit")
    public JAXBElement<SaveCredit> createSaveCredit(SaveCredit value) {
        return new JAXBElement<SaveCredit>(_SaveCredit_QNAME, SaveCredit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCredit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.financialmockapp.sample.umax.threeti.com/", name = "getCredit")
    public JAXBElement<GetCredit> createGetCredit(GetCredit value) {
        return new JAXBElement<GetCredit>(_GetCredit_QNAME, GetCredit.class, null, value);
    }

}
