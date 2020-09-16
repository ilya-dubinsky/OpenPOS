package org.openpos.tms.dao;

import org.openpos.tms.dao.dataobject.AccountDO;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<AccountDO, Long>{

}
