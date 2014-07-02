package com.threeti.umax.sample.financialmockapp.service;

import com.threeti.umax.sample.financialmockapp.model.Credit;

import javax.jws.WebParam;
import javax.jws.WebResult;
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
@Path("/")
@Produces({"application/json", "application/xml"})
@WebService
public interface CreditService {
	
    @GET
    @Path("/credit/{customerId}")
    @WebResult(name="credit")
	Credit getCustomerCredit(@PathParam("customerId") @WebParam(name="customerId")String customerId);
    /**
     * Retrieves a credit by creditId.  An exception is thrown if credit not found
     *
     * @param creditId the identifier for the credit
     * @return Credit
     */
    @GET
    @Path("/credit/{id}")
    @WebResult(name="credit")
    Credit getCredit(@PathParam("creditId") @WebParam(name="creditId") String creditId);

    /**
     * Retrieves a list of all credits.
     *
     * @return List
     */
    @GET
    @Path("/credits")
    List<Credit> getCredits();

    /**
     * Saves a credit's information
     *
     * @param credit the credit's information
     * @return updated credit
     * @throws ContractExistsException thrown when credit already exists
     */
    @POST
    @Path("/credit")
    Credit saveCredit(Credit credit);

}
