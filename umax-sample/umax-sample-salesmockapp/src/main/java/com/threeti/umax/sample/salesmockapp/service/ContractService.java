package com.threeti.umax.sample.salesmockapp.service;

import com.threeti.umax.sample.salesmockapp.model.Contract;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Web Service interface so hierarchy of Generic Manager isn't carried through.
 */
@WebService
@Path("/")
@Produces({"application/json", "application/xml"})
public interface ContractService {
	
    /**
     * Retrieves a contract by contractId.  An exception is thrown if contract not found
     *
     * @param contractId the identifier for the contract
     * @return Contract
     */
    @GET
    @Path("/contract/{id}")
    Contract getContract(@PathParam("id") String contractId);
    
    /**
     * Sets state for contract by given id.
     * @param contractId
     * @param state
     */
    void setContractState(String contractId, String state); 
    
    /**
     * Retrieves a list of all contracts.
     *
     * @return List
     */
    @GET
    @Path("/contracts")
    List<Contract> getContracts();

    /**
     * Saves a contract's information
     *
     * @param contract the contract's information
     * @return updated contract
     * @throws ContractExistsException thrown when contract already exists
     */
    @POST
    @Path("/contract")
    Contract saveContract(Contract contract);

}
