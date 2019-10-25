package com.insigma.afc.msutil.dao;

import com.insigma.afc.msutil.model.TmoCmdResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TmoCmdResultDao extends JpaRepository<TmoCmdResult, Long>, JpaSpecificationExecutor<TmoCmdResult> {
}
