package org.openpos.tms.dao;

import org.openpos.tms.dao.dataobject.CountryDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryDO, String>{

}
