package org.openpos.tms.dao;

import org.openpos.tms.dao.dataobject.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
