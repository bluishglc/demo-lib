
/*
 * 
 */

package com.threeti.umax.sample.salesmockapp.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.4.0
 * 2011-09-23T17:37:19.922+08:00
 * Generated source version: 2.4.0
 * 
 */


@WebServiceClient(name = "ContractService", 
                  wsdlLocation = "http://localhost:9090/services/ContractService?wsdl",
                  targetNamespace = "http://service.salesmockapp.sample.umax.threeti.com/") 
public class ContractService_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://service.salesmockapp.sample.umax.threeti.com/", "ContractService");
    public final static QName ContractManagerImplPort = new QName("http://service.salesmockapp.sample.umax.threeti.com/", "ContractManagerImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:9090/services/ContractService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ContractService_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:9090/services/ContractService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ContractService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ContractService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ContractService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     * 
     * @return
     *     returns ContractService
     */
    @WebEndpoint(name = "ContractManagerImplPort")
    public ContractService getContractManagerImplPort() {
        return super.getPort(ContractManagerImplPort, ContractService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ContractService
     */
    @WebEndpoint(name = "ContractManagerImplPort")
    public ContractService getContractManagerImplPort(WebServiceFeature... features) {
        return super.getPort(ContractManagerImplPort, ContractService.class, features);
    }

}
