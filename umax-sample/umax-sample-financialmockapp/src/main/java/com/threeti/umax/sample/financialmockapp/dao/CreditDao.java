package com.threeti.umax.sample.financialmockapp.dao;

import com.threeti.umax.sample.financialmockapp.model.Credit;

public interface CreditDao  extends GenericDao<Credit, Long>{

	Credit getCustomerCredit(Long customerId);

}
