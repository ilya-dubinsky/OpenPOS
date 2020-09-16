package org.openpos.tms.dao;

import org.openpos.tms.dao.dataobject.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String>{

}
