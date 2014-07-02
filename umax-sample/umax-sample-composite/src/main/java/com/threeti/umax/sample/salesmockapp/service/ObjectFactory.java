
package com.threeti.umax.sample.salesmockapp.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.threeti.umax.sample.salesmockapp.service package. 
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

    private final static QName _GetContractsResponse_QNAME = new QName("http://service.salesmockapp.sample.umax.threeti.com/", "getContractsResponse");
    private final static QName _Customer_QNAME = new QName("http://service.salesmockapp.sample.umax.threeti.com/", "customer");
    private final static QName _GetContractResponse_QNAME = new QName("http://service.salesmockapp.sample.umax.threeti.com/", "getContractResponse");
    private final static QName _GetContracts_QNAME = new QName("http://service.salesmockapp.sample.umax.threeti.com/", "getContracts");
    private final static QName _SaveContract_QNAME = new QName("http://service.salesmockapp.sample.umax.threeti.com/", "saveContract");
    private final static QName _SetContractState_QNAME = new QName("http://service.salesmockapp.sample.umax.threeti.com/", "setContractState");
    private final static QName _Contract_QNAME = new QName("http://service.salesmockapp.sample.umax.threeti.com/", "contract");
    private final static QName _SetContractStateResponse_QNAME = new QName("http://service.salesmockapp.sample.umax.threeti.com/", "setContractStateResponse");
    private final static QName _GetContract_QNAME = new QName("http://service.salesmockapp.sample.umax.threeti.com/", "getContract");
    private final static QName _SaveContractResponse_QNAME = new QName("http://service.salesmockapp.sample.umax.threeti.com/", "saveContractResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.threeti.umax.sample.salesmockapp.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SaveContract }
     * 
     */
    public SaveContract createSaveContract() {
        return new SaveContract();
    }

    /**
     * Create an instance of {@link SetContractState }
     * 
     */
    public SetContractState createSetContractState() {
        return new SetContractState();
    }

    /**
     * Create an instance of {@link SetContractStateResponse }
     * 
     */
    public SetContractStateResponse createSetContractStateResponse() {
        return new SetContractStateResponse();
    }

    /**
     * Create an instance of {@link Contract }
     * 
     */
    public Contract createContract() {
        return new Contract();
    }

    /**
     * Create an instance of {@link SaveContractResponse }
     * 
     */
    public SaveContractResponse createSaveContractResponse() {
        return new SaveContractResponse();
    }

    /**
     * Create an instance of {@link GetContract }
     * 
     */
    public GetContract createGetContract() {
        return new GetContract();
    }

    /**
     * Create an instance of {@link GetContractsResponse }
     * 
     */
    public GetContractsResponse createGetContractsResponse() {
        return new GetContractsResponse();
    }

    /**
     * Create an instance of {@link GetContractResponse }
     * 
     */
    public GetContractResponse createGetContractResponse() {
        return new GetContractResponse();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link GetContracts }
     * 
     */
    public GetContracts createGetContracts() {
        return new GetContracts();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetContractsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.salesmockapp.sample.umax.threeti.com/", name = "getContractsResponse")
    public JAXBElement<GetContractsResponse> createGetContractsResponse(GetContractsResponse value) {
        return new JAXBElement<GetContractsResponse>(_GetContractsResponse_QNAME, GetContractsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Customer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.salesmockapp.sample.umax.threeti.com/", name = "customer")
    public JAXBElement<Customer> createCustomer(Customer value) {
        return new JAXBElement<Customer>(_Customer_QNAME, Customer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetContractResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.salesmockapp.sample.umax.threeti.com/", name = "getContractResponse")
    public JAXBElement<GetContractResponse> createGetContractResponse(GetContractResponse value) {
        return new JAXBElement<GetContractResponse>(_GetContractResponse_QNAME, GetContractResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetContracts }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.salesmockapp.sample.umax.threeti.com/", name = "getContracts")
    public JAXBElement<GetContracts> createGetContracts(GetContracts value) {
        return new JAXBElement<GetContracts>(_GetContracts_QNAME, GetContracts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveContract }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.salesmockapp.sample.umax.threeti.com/", name = "saveContract")
    public JAXBElement<SaveContract> createSaveContract(SaveContract value) {
        return new JAXBElement<SaveContract>(_SaveContract_QNAME, SaveContract.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetContractState }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.salesmockapp.sample.umax.threeti.com/", name = "setContractState")
    public JAXBElement<SetContractState> createSetContractState(SetContractState value) {
        return new JAXBElement<SetContractState>(_SetContractState_QNAME, SetContractState.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Contract }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.salesmockapp.sample.umax.threeti.com/", name = "contract")
    public JAXBElement<Contract> createContract(Contract value) {
        return new JAXBElement<Contract>(_Contract_QNAME, Contract.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetContractStateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.salesmockapp.sample.umax.threeti.com/", name = "setContractStateResponse")
    public JAXBElement<SetContractStateResponse> createSetContractStateResponse(SetContractStateResponse value) {
        return new JAXBElement<SetContractStateResponse>(_SetContractStateResponse_QNAME, SetContractStateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetContract }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.salesmockapp.sample.umax.threeti.com/", name = "getContract")
    public JAXBElement<GetContract> createGetContract(GetContract value) {
        return new JAXBElement<GetContract>(_GetContract_QNAME, GetContract.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveContractResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.salesmockapp.sample.umax.threeti.com/", name = "saveContractResponse")
    public JAXBElement<SaveContractResponse> createSaveContractResponse(SaveContractResponse value) {
        return new JAXBElement<SaveContractResponse>(_SaveContractResponse_QNAME, SaveContractResponse.class, null, value);
    }

}
