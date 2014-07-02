package com.threeti.umax.sample.salesmockapp.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.threeti.umax.sample.salesmockapp.dao.ContractDao;
import com.threeti.umax.sample.salesmockapp.model.Contract;

@Repository
public class ContractDaoHibernate extends GenericDaoHibernate<Contract, Long> implements ContractDao{
    public ContractDaoHibernate() {
        super(Contract.class);
    }
}
