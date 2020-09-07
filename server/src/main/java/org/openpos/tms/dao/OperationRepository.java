package org.openpos.tms.dao;

import java.util.Optional;

import org.openpos.tms.dao.dataobject.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
	
	@Query("select o from Operation o where o.type=?1")
	public Optional<Operation> findByType(String type);
}
