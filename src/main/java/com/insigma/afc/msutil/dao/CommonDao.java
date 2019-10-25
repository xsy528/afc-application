package com.insigma.afc.msutil.dao;

import com.insigma.afc.msutil.dao.entity.TapEodVersionInquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonDao extends CommonDaoRepository, JpaRepository<TapEodVersionInquiry, String>, JpaSpecificationExecutor<TapEodVersionInquiry>  {

}
