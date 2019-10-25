package com.insigma.afc.msutil.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommonDaoImpl implements CommonDaoRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CommonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List execSqlQuery(String sql) {
        List<Map<String,Object>> list = null;
        List<Object[]> objList = new ArrayList<>();
        try {
            list = jdbcTemplate.queryForList(sql);
            for(Map<String,Object> map:list){
                List<Object> collist = new ArrayList<>();
                for(Map.Entry entry:map.entrySet()){
                    collist.add(entry.getValue());
                }
                objList.add(collist.toArray());
            }
        }catch(Exception e){
            throw e;
        }
        return objList;
    }

    @Override
    public List execSqlCount(String sql) {
        List<Map<String,Object>> list = null;
        List objList = new ArrayList<>();
        try {
            list = jdbcTemplate.queryForList(sql);
            for(Map<String,Object> map:list){
                for(Map.Entry entry:map.entrySet()){
                    objList.add(entry.getValue());
                }
            }
        }catch(Exception e){
            throw e;
        }
        return objList;
    }

    @Override
    public int execSqlUpdate(String sql, Object... args) {
        int ret = 0;
        try {
            ret = jdbcTemplate.update(sql, args);
        }catch (Exception e){
            throw e;
        }
        return ret;
    }
}
