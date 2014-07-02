package com.threeti.umax.sample.financialmockapp.service.impl;

import com.threeti.umax.sample.financialmockapp.dao.CustomerDao;
import com.threeti.umax.sample.financialmockapp.model.Customer;
import com.threeti.umax.sample.financialmockapp.model.User;
import com.threeti.umax.sample.financialmockapp.service.CustomerManager;
import com.threeti.umax.sample.financialmockapp.service.CustomerService;
import com.threeti.umax.sample.financialmockapp.service.UserExistsException;
import com.threeti.umax.sample.financialmockapp.service.UserManager;
import com.threeti.umax.sample.financialmockapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;


/**
 * Implementation of CustomerManager interface.
 *
 * @author Laurence Geng
 */
@Service("customerManager")
@WebService(serviceName = "CustomerService", 
			endpointInterface = "com.threeti.umax.sample.financialmockapp.service.CustomerService",
			targetNamespace="http://service.financialmockapp.sample.umax.threeti.com/")
public class CustomerManagerImpl extends GenericManagerImpl<Customer, Long> implements CustomerManager, CustomerService {

    private CustomerDao customerDao;


    @Autowired
    public void setCustomerDao(CustomerDao customerDao) {
        this.dao = customerDao;
        this.customerDao = customerDao;
    }

    /**
     * {@inheritDoc}
     */
    public Customer getCustomer(String customerId) {
        return customerDao.get(new Long(customerId));
    }

    /**
     * {@inheritDoc}
     */
    public List<Customer> getCustomers() {
        return customerDao.getAllDistinct();
    }

    /**
     * {@inheritDoc}
     */
	public Customer saveCustomer(Customer customer) {
		return customerDao.save(customer);
	}

 
}
