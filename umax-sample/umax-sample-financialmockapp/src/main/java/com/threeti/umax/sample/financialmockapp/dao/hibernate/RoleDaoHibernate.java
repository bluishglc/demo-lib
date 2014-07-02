package com.threeti.umax.sample.financialmockapp.dao.hibernate;

import com.threeti.umax.sample.financialmockapp.dao.RoleDao;
import com.threeti.umax.sample.financialmockapp.model.Role;

import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve Role objects.
 *
 * @author laurence.geng 
 */
@Repository
public class RoleDaoHibernate extends GenericDaoHibernate<Role, Long> implements RoleDao {

    /**
     * Constructor to create a Generics-based version using Role as the entity
     */
    public RoleDaoHibernate() {
        super(Role.class);
    }

    /**
     * {@inheritDoc}
     */
    public Role getRoleByName(String rolename) {
        List roles = getHibernateTemplate().find("from Role where name=?", rolename);
        if (roles.isEmpty()) {
            return null;
        } else {
            return (Role) roles.get(0);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeRole(String rolename) {
        Object role = getRoleByName(rolename);
        getHibernateTemplate().delete(role);
    }
}
