package org.openpos.tms.dao;

import org.openpos.tms.dao.dataobject.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, String>{

}
