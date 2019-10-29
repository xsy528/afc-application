package com.insigma.afc.msutil.dao.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author by Xinshao,
 * @Email xingshaoya@unittec.com,
 * @Time on 2019/9/11 18:01.
 * @Ticket :
 */
public class NodeUtil {
    private JdbcTemplateDao templateDao;


    public NodeUtil(JdbcTemplate jdbcTemplate) {

        templateDao = new JdbcTemplateDao(jdbcTemplate);

    }

    private static Map<Short,String> lines = new HashMap<>();

    private static Map<Integer,String> stations = new HashMap<>();

    private static Map<Long,String> devices = new HashMap<>();

    private final String findLine = " select LINE_NAME from TMETRO_LINE where LINE_ID=";
    private final String findStation = "select STATION_NAME from TMETRO_STATION where STATION_ID=";
    private final String findDevice = "select DEVICE_NAME from TMETRO_DEVICE where DEVICE_ID=";

    public  String getLineNameById(Short id){
        String name = lines.get(id);
        if(name == null){
            Object list = templateDao.queryForString(
                    findLine + id, null,"LINE_NAME");
            String lineName = (String) list;
            lines.put(id,lineName);
            return lineName;
        }
        return name;
    }
    public  String getStationNameById(Integer id){
        String name = stations.get(id);
        if(name == null){
            Object list = templateDao.queryForString(
                    findStation + id, null,"STATION_NAME");
            String lineName = (String) list;
            stations.put(id,lineName);
            return lineName;
        }
        return name;
    }
    public  String getDeviceNameById(Long id){
        String name = devices.get(id);
        if(name == null){
            Object list = templateDao.queryForString(
                    findDevice + id, null,"DEVICE_NAME");
            String lineName = (String) list;
            devices.put(id,lineName);
            return lineName;
        }
        return name;
    }
    public String getTicktTypeName(Integer cardType){
        if(cardType==null){

            return null;
        }
        Object list = templateDao.queryForString("select t.CARDTYPENAME from V_TICKET_TYPE t where 1=1 and t.CARDTYPE=" + cardType + " order by CARDTYPE",
                null,"CARDTYPENAME");

        return list+"";

    }
    public Map<String,Object> getTicktTypeName(){
            List list = templateDao.queryForMaps("select t.CARDTYPE, t.CARDTYPENAME from V_TICKET_TYPE t where 1=1  order by CARDTYPE",
                    null);
            return (Map) list;
        }
     public String getUserNameById(String useId,boolean flag){
        String sql;
        if (flag){
            sql = "select USER_NAME from TAP_USER where USER_ID="+useId+" and IF_VALID=0";
        }else {
            sql = "select USER_NAME from TAP_USER where USER_ID="+useId;
        }
         Object list = templateDao.queryForString(sql, null,"USER_NAME");
        if(list!=null){
            return list+"";
        }
         return "未知";
     }

     public String getTimeText(String time){
        if(time.length()!=6){
            return null;
        }
        return time.substring(0,2)+":"+time.substring(2,4)+":"+time.substring(4);

     }

    public String getLineTextById(Short id){
        return getLineNameById(id)+"/"+id;
    }
    public String getStationTextById(Integer id){
        return getStationNameById(id)+"/"+id;
    }
    public String getDeviceTextById(Long id){
        return getDeviceNameById(id)+"/"+id;
    }

}
