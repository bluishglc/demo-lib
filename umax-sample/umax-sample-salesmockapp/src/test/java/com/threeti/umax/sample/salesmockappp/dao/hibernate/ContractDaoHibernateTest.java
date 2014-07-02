package com.threeti.umax.sample.salesmockappp.dao.hibernate;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.threeti.umax.sample.salesmockapp.dao.ContractDao;
import com.threeti.umax.sample.salesmockapp.model.Contract;

public class ContractDaoHibernateTest extends BaseDaoTestCase{
    @Autowired
    private ContractDao dao;

    @Test
    public void testGet() throws Exception {
        Contract contract = dao.get(-1L);
        assertNotNull(contract);
        assertEquals(new Long(-1L), contract.getFirstParty().getId());
    }
}

