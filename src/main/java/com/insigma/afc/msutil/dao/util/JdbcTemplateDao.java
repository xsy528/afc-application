package com.insigma.afc.msutil.dao.util;


import com.insigma.commons.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Ticket: Jdbc工具类
 *
 * @author: xingshaoya
 * @time: 2019-07-09 11:18
 */
public class JdbcTemplateDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcTemplateDao.class);

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 只查询一列数据类型对象。用于只有一行查询结果的数据
     * @param sql
     * @param params
     * @param cla Integer.class,Float.class,Double.Class,Long.class,Boolean.class,Char.class,Byte.class,Short.class
     * @return
     */
    private Object queryOneColumnForSigetonRow(String sql,Object[] params,Class cla){

        try{
            if(params!=null&&params.length>0) {

                return jdbcTemplate.queryForObject(sql, params, cla);
            }
            return jdbcTemplate.queryForObject(sql,cla);
        }catch(Exception ex){
            LOGGER.error(ex.toString());
            return null;
        }
    }


    /**
     * 更新表
     * @param sql
     * @param params
     * @return
     */
    public Integer update(String sql,Object[] params){
        try{
            if(params!=null&&params.length>0) {
                return jdbcTemplate.update(sql, params);
            }
            return jdbcTemplate.update(sql);
        }catch(Exception ex){
            LOGGER.error(ex.toString());
            return null;
        }
    }

    /**
     * 删除数据
     * @param sql
     * @param params
     * @return
     */
    public Integer delect(String sql,Object[] params){
       return update(sql,params);
    }

    /**
     * 获取存取过程的结果-时间
     * @param sql
     * @param type
     * @param params
     * @return
     */
    public Date excuteSql(String sql,int type,Object... params){
        Object execute = jdbcTemplate.execute(con -> {
            CallableStatement cs = con.prepareCall(sql);
            //cs.setObject(1,obj[0]);// 设置输入参数的值
            cs.setObject(1,params[0]);
            cs.registerOutParameter(2,type);// 注册输出参数的类型
            return cs;
        }, (CallableStatementCallback) cs -> {
            cs.execute();
            return cs.getObject(2);// 获取输出参数的值
        });
        return (Date)execute;

    }
    /**
     * 返回结果为单个String
     * @param sql
     * @param params
     * @param getName
     * @return
     */
    public String queryForString(String sql,Object[] params,String getName){
        return queryForMaps(sql, params).get(0).get(getName)+"";
    }

    public Date queryForDate(String sql, Object[] params){
        List<Map<String, Object>> maps = queryForMaps(sql, params);

        String strDate = maps.get(0) + "";
        strDate = strDate.substring(10);
        Date strtodate = DateTimeUtil.stringToDate(strDate,"yyyy-MM-dd");

        return strtodate;
    }
    /**
     * 查询返回实体对象集合
     * @param sql    sql语句
     * @param params 填充sql问号占位符数
     * @param cla    实体对象类型
     * @return
     */
    public List queryForObjectList(String sql,Object[] params,final Class cla){
        final List list=new ArrayList();
        try{
            jdbcTemplate.query(sql, params, new RowCallbackHandler(){
                @Override
                public void processRow(ResultSet rs) {
                    try{
                        List<String> columnNames=new ArrayList<String>();
                        ResultSetMetaData meta=rs.getMetaData();
                        int num=meta.getColumnCount();
                        for(int i=0;i<num;i++){
                            columnNames.add(meta.getColumnLabel(i+1).toLowerCase().trim());
                        }
                        Method[] methods=cla.getMethods();
                        List<String> fields=new ArrayList<String>();
                        for(int i=0;i<methods.length;i++){
                            if(methods[i].getName().trim().startsWith("set")){
                                String f=methods[i].getName().trim().substring(3);
                                f=(f.charAt(0)+"").toLowerCase().trim()+f.substring(1);
                                fields.add(f);
                            }
                        }
                        do{
                            Object obj=null;
                            try{
                                obj=cla.getConstructor().newInstance();
                            }catch(Exception ex){
                                ex.printStackTrace();
                            }
                            for(int i=0;i<num;i++){
                                Object objval=rs.getObject(i+1);
                                for(int n=0;n<fields.size();n++){
                                    String fieldName=fields.get(n).trim();
                                    if(columnNames.get(i).equals(fieldName.toLowerCase().trim())){
                                        BeanUtils.copyProperties(obj, objval,fieldName);
                                        break;
                                    }
                                }
                            }
                            list.add(obj);
                        }while(rs.next());
                    }catch(Exception ex){
                        LOGGER.error(ex.toString());
                    }
                }
            });
        }catch(Exception ex){ex.printStackTrace();}
        if(list.size()<=0){
            return null;
        }
        return list;
    }

    /**
     * 查询返回List<Map<String,Object>>格式数据,每一个Map代表一行数据，列名为key
     * @param sql  sql语句
     * @param params 填充问号占位符数
     * @return
     */
    public List<Map<String,Object>> queryForMaps(String sql, Object[] params){
        try{
            if(params!=null&&params.length>0){
                return jdbcTemplate.queryForList(sql, params);
            }
            return jdbcTemplate.queryForList(sql);
        }catch(Exception ex){
            LOGGER.error(ex.toString());
            return null;
        }
    }

    /**
     * 获取数据总条数
     * @param sql
     * @param params
     * @return
     */
    public Integer countColumn(String sql,Object[] params,boolean flag){
        if(flag){
            if(params!=null&&params.length!=0){
                return jdbcTemplate.queryForObject(sql, params, Integer.class);
            }
                return jdbcTemplate.queryForObject(sql,Integer.class);
        }
        String rowsql="select count(*) from ("+sql+") gmtxtabs_";

        return (Integer)queryOneColumnForSigetonRow(rowsql, params, Integer.class);
    }

    /**
     * 查询分页（ORACLE数据库）
     * @param sql     终执行查询的语句
     * @param params  填充sql语句中的问号占位符数
     * @param page    想要第几页的数据
     * @param cla     要封装成的实体元类型
     * @return        pageList对象
     */
    public PageList queryByPageForOracle(String sql, Object[] params, int page, int pageSize,Class cla) {
        //查询总行数sql
        int pages;
        //总页数
        int rows=countColumn(sql,params,false);
        LOGGER.info("查询总行数为："+rows);
        //查询总行数
        //判断页数,如果是页大小的整数倍就为rows/pageRow如果不是整数倍就为rows/pageRow+1
        if (rows % pageSize == 0) {
            pages = rows / pageSize;
        } else {
            pages = rows / pageSize + 1;
        }
        StringBuffer paginationSQL = new StringBuffer(" SELECT * FROM ( ");
        paginationSQL.append(" SELECT temp.* ,ROWNUM num FROM ( ");
        paginationSQL.append(sql);
        paginationSQL.append("　) temp where ROWNUM <= " + (page+1)*pageSize);
        paginationSQL.append(" ) WHERE　num > " + page*pageSize);
        //查询第page页的数据sql语句

        LOGGER.debug(">>>>>>sql:"+paginationSQL.toString());
        //查询第page页数据
        List list=null;
        if(cla!=null){
            list=queryForObjectList(sql, params, cla);
            LOGGER.debug("queryForObjectList:"+list.size());
        }else{
            list=queryForMaps(paginationSQL.toString(), params);
            LOGGER.debug("queryForMaps:"+list.size());
        }

        //返回分页格式数据
        PageList pl =new PageList();
        pl.setPage(page);
        //设置显示的当前页数
        pl.setPageSize(pageSize);
        //设置每页个数
        pl.setPages(pages);
        //设置总页数
        pl.setList(list);
        //设置当前页数据
        pl.setTotalRows(rows);
        //设置总记录数
        return pl;
    }

    /**
     * 获取所有数据不分页
     * @param sql
     * @param params
     * @param cla
     * @return
     */
    public PageList queryForOracle(String sql, Object[] params,Class cla) {
        //查询总行数sql
       // int pages;
        //总页数
        int rows=countColumn(sql,params,false);
        LOGGER.info("查询总行数为："+rows);
        List list;
        if(cla!=null){
            list=queryForObjectList(sql, params, cla);
            LOGGER.debug("queryForObjectList:"+list.size());
        }else{
            list=queryForMaps(sql, params);
            LOGGER.debug("queryForMaps:"+list.size());
        }

        //返回分页格式数据
        PageList pl =new PageList();
        pl.setPage(0);
        //设置显示的当前页数
        pl.setPageSize(0);
        //设置每页个数
        pl.setPages(0);
        //设置总页数
        pl.setList(list);
        //设置当前页数据
        pl.setTotalRows(rows);
        //设置总记录数
        return pl;
    }

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

    public int execSqlUpdate(String sql, Object... args) {
        int ret = 0;
        try {
            ret = jdbcTemplate.update(sql, args);
        }catch (Exception e){
            throw e;
        }
        return ret;
    }

    private void printMap(Map<String,Object> line) {
        for (Map.Entry<String, Object> entry : line.entrySet()) {
            String key = entry.getKey();
            LOGGER.debug(key + "=========" + entry.getValue());
        }
    }
}
