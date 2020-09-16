package org.openpos.tms.dao;

import org.openpos.tms.dao.dataobject.CurrencyDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyDO, String>{

}
