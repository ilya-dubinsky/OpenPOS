package org.openpos.tms.dao;

import org.openpos.tms.dao.dataobject.AddressDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressDO, Long> {

}
