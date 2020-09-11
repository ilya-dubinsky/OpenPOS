package org.openpos.tms.dao;

import org.openpos.tms.dao.dataobject.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long>{

}
