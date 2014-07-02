package com.threeti.umax.sample.financialmockapp.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.threeti.umax.sample.financialmockapp.model.Customer;

/**
 * Web Service interface so hierarchy of Generic Manager isn't carried through.
 */
@WebService
@Path("/")
@Produces({"application/json", "application/xml"})
public interface CustomerService {
    /**
     * Retrieves a customer by customerId.  An exception is thrown if customer not found
     *
     * @param customerId the identifier for the customer
     * @return Customer
     */
    @GET
    @Path("/customer/{id}")
    Customer getCustomer(@PathParam("id") String customerId);

    /**
     * Retrieves a list of all customers.
     *
     * @return List
     */
    @GET
    @Path("/customers")
    List<Customer> getCustomers();

    /**
     * Saves a customer's information
     *
     * @param customer the customer's information
     * @return updated customer
     * @throws CustomerExistsException thrown when customer already exists
     */
    @POST
    @Path("/customer")
    Customer saveCustomer(Customer customer);

}
