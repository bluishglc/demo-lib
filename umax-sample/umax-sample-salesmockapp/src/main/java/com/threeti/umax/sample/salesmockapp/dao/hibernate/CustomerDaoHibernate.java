package com.threeti.umax.sample.salesmockapp.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.threeti.umax.sample.salesmockapp.dao.CustomerDao;
import com.threeti.umax.sample.salesmockapp.model.Customer;

@Repository
public class CustomerDaoHibernate extends GenericDaoHibernate<Customer, Long> implements CustomerDao{
    public CustomerDaoHibernate() {
        super(Customer.class);
    }
}
