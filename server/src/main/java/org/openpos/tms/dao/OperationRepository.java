package org.openpos.tms.dao;

import java.util.Optional;

import org.openpos.tms.dao.dataobject.OperationDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<OperationDO, Long> {
	
	@Query("select o from OperationDO o where o.type=?1")
	public Optional<OperationDO> findByType(String type);
}
