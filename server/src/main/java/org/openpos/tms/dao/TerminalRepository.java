package org.openpos.tms.dao;

import org.openpos.tms.dao.dataobject.Terminal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalRepository extends PagingAndSortingRepository<Terminal, Long>{

	@Query("select t from Terminal t where t.account.id=:accountId")
	public Page<Terminal> findByAccountId(Pageable pageable, @Param("accountId") long accountId);
}
