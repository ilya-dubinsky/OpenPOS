package org.openpos.tms.dao;

import org.openpos.tms.dao.dataobject.PublicKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicKeyRepository extends JpaRepository<PublicKey, Long> {

}
