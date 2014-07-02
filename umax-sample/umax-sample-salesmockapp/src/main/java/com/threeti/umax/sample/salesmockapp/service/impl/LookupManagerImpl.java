package com.threeti.umax.sample.salesmockapp.service.impl;

import com.threeti.umax.sample.salesmockapp.dao.LookupDao;
import com.threeti.umax.sample.salesmockapp.model.LabelValue;
import com.threeti.umax.sample.salesmockapp.model.Role;
import com.threeti.umax.sample.salesmockapp.service.LookupManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of LookupManager interface to talk to the persistence layer.
 *
 * @author Laurence Geng
 */
@Service("lookupManager")
public class LookupManagerImpl implements LookupManager {
    @Autowired
    LookupDao dao;

    /**
     * {@inheritDoc}
     */
    public List<LabelValue> getAllRoles() {
        List<Role> roles = dao.getRoles();
        List<LabelValue> list = new ArrayList<LabelValue>();

        for (Role role1 : roles) {
            list.add(new LabelValue(role1.getName(), role1.getName()));
        }

        return list;
    }
}
