package com.insigma.afc.msutil.dao;

import java.util.List;

public interface CommonDaoRepository {
    List execSqlQuery(String sql);
    List execSqlCount(String sql);
    int execSqlUpdate(String sql, Object... srgs);
}
