package com.threeti.umax.sample.financialmockapp.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.threeti.umax.sample.financialmockapp.dao.CustomerDao;
import com.threeti.umax.sample.financialmockapp.model.Customer;

@Repository
public class CustomerDaoHibernate extends GenericDaoHibernate<Customer, Long> implements CustomerDao{
    public CustomerDaoHibernate() {
        super(Customer.class);
    }
}
