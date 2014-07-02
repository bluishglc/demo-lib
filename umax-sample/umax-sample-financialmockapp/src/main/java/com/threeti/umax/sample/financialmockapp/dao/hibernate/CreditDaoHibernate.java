package com.threeti.umax.sample.financialmockapp.dao.hibernate;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.threeti.umax.sample.financialmockapp.dao.CreditDao;
import com.threeti.umax.sample.financialmockapp.model.Credit;

@Repository
public class CreditDaoHibernate extends GenericDaoHibernate<Credit, Long> implements CreditDao{
    
	public CreditDaoHibernate() {
        super(Credit.class);
    }

	@Override
	public Credit getCustomerCredit(final Long customerId) {
		return getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Credit>() {
			@Override
			public Credit doInHibernate(Session session) throws HibernateException, SQLException {
				String queryCustomerCreditHql = "select cd from Credit cd join cd.customer ct where ct.id = :customerId";
				Credit credit = (Credit) session.createQuery(queryCustomerCreditHql).setParameter("customerId", customerId).setCacheable(true).uniqueResult();
				return credit;
			}
		});
	}
}
