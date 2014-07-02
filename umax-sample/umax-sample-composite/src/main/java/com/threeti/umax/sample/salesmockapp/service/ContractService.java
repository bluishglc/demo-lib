package com.threeti.umax.sample.salesmockapp.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.0
 * 2011-09-23T17:37:19.875+08:00
 * Generated source version: 2.4.0
 * 
 */
 
@WebService(targetNamespace = "http://service.salesmockapp.sample.umax.threeti.com/", name = "ContractService")
@XmlSeeAlso({ObjectFactory.class})
public interface ContractService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "saveContract", targetNamespace = "http://service.salesmockapp.sample.umax.threeti.com/", className = "com.threeti.umax.sample.salesmockapp.service.SaveContract")
    @WebMethod
    @ResponseWrapper(localName = "saveContractResponse", targetNamespace = "http://service.salesmockapp.sample.umax.threeti.com/", className = "com.threeti.umax.sample.salesmockapp.service.SaveContractResponse")
    public com.threeti.umax.sample.salesmockapp.service.Contract saveContract(
        @WebParam(name = "arg0", targetNamespace = "")
        com.threeti.umax.sample.salesmockapp.service.Contract arg0
    );

    @RequestWrapper(localName = "setContractState", targetNamespace = "http://service.salesmockapp.sample.umax.threeti.com/", className = "com.threeti.umax.sample.salesmockapp.service.SetContractState")
    @WebMethod
    @ResponseWrapper(localName = "setContractStateResponse", targetNamespace = "http://service.salesmockapp.sample.umax.threeti.com/", className = "com.threeti.umax.sample.salesmockapp.service.SetContractStateResponse")
    public void setContractState(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getContracts", targetNamespace = "http://service.salesmockapp.sample.umax.threeti.com/", className = "com.threeti.umax.sample.salesmockapp.service.GetContracts")
    @WebMethod
    @ResponseWrapper(localName = "getContractsResponse", targetNamespace = "http://service.salesmockapp.sample.umax.threeti.com/", className = "com.threeti.umax.sample.salesmockapp.service.GetContractsResponse")
    public java.util.List<com.threeti.umax.sample.salesmockapp.service.Contract> getContracts();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getContract", targetNamespace = "http://service.salesmockapp.sample.umax.threeti.com/", className = "com.threeti.umax.sample.salesmockapp.service.GetContract")
    @WebMethod
    @ResponseWrapper(localName = "getContractResponse", targetNamespace = "http://service.salesmockapp.sample.umax.threeti.com/", className = "com.threeti.umax.sample.salesmockapp.service.GetContractResponse")
    public com.threeti.umax.sample.salesmockapp.service.Contract getContract(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}