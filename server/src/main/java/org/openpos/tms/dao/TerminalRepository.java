package org.openpos.tms.dao;

import java.util.UUID;

import org.openpos.tms.dao.dataobject.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalRepository extends JpaRepository<Terminal, UUID>{

}
