
package com.threeti.umax.sample.financialmockapp.service;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.0
 * 2011-09-14T16:58:45.063+08:00
 * Generated source version: 2.4.0
 * 
 */
public final class CreditService_CreditManagerImplPort_Client {

    private static final QName SERVICE_NAME = new QName("http://service.financialmockapp.sample.umax.threeti.com/", "CreditService");

    private CreditService_CreditManagerImplPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = CreditService_Service.WSDL_LOCATION;
        if (args.length > 0) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        CreditService_Service ss = new CreditService_Service(wsdlURL, SERVICE_NAME);
        CreditService port = ss.getCreditManagerImplPort();  
        
        {
        System.out.println("Invoking saveCredit...");
        com.threeti.umax.sample.financialmockapp.service.Credit _saveCredit_arg0 = null;
        com.threeti.umax.sample.financialmockapp.service.Credit _saveCredit__return = port.saveCredit(_saveCredit_arg0);
        System.out.println("saveCredit.result=" + _saveCredit__return);


        }
        {
        System.out.println("Invoking getCredits...");
        java.util.List<com.threeti.umax.sample.financialmockapp.service.Credit> _getCredits__return = port.getCredits();
        System.out.println("getCredits.result=" + _getCredits__return);


        }
        {
        System.out.println("Invoking getCustomerCredit...");
        java.lang.String _getCustomerCredit_customerId = "";
        com.threeti.umax.sample.financialmockapp.service.Credit _getCustomerCredit__return = port.getCustomerCredit(_getCustomerCredit_customerId);
        System.out.println("getCustomerCredit.result=" + _getCustomerCredit__return);


        }
        {
        System.out.println("Invoking getCredit...");
        java.lang.String _getCredit_creditId = "";
        com.threeti.umax.sample.financialmockapp.service.Credit _getCredit__return = port.getCredit(_getCredit_creditId);
        System.out.println("getCredit.result=" + _getCredit__return);


        }

        System.exit(0);
    }

}
