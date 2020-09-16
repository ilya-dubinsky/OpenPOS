package org.openpos.tms.dao;

import org.openpos.tms.dao.dataobject.TerminalDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalRepository extends PagingAndSortingRepository<TerminalDO, Long>{

	@Query("select t from TerminalDO t where t.account.id=:accountId")
	public Page<TerminalDO> findByAccountId(Pageable pageable, @Param("accountId") long accountId);
}
