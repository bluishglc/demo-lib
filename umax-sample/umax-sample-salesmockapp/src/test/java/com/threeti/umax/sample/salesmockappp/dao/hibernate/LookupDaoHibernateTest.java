package com.threeti.umax.sample.salesmockappp.dao.hibernate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.threeti.umax.sample.salesmockapp.dao.LookupDao;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * This class tests the current LookupDao implementation class
 * @author Laurence Geng
 */
public class LookupDaoHibernateTest extends BaseDaoTestCase {
    @Autowired
    LookupDao lookupDao;

    @Test
    public void testGetRoles() {
        List roles = lookupDao.getRoles();
        log.debug(roles);
        assertTrue(roles.size() > 0);
    }
}
