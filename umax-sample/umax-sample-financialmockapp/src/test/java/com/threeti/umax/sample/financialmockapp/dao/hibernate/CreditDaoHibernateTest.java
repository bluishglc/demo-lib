package com.threeti.umax.sample.financialmockapp.dao.hibernate;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.threeti.umax.sample.financialmockapp.dao.CreditDao;
import com.threeti.umax.sample.financialmockapp.model.Credit;

public class CreditDaoHibernateTest extends BaseDaoTestCase{
    @Autowired
    private CreditDao dao;

    @Test
    public void testGet() throws Exception {
        Credit credit = dao.get(-1L);
        assertNotNull(credit);
        assertEquals(Long.valueOf(-1L), credit.getId());
    }
}

