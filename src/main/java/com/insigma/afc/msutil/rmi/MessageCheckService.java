/*
 * 日期：2019年8月20日
 *
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
/**
 *
 */
package com.insigma.afc.msutil.rmi;

import com.insigma.afc.application.AFCNodeLevel;
import com.insigma.afc.msutil.dao.util.JdbcTemplateDao;
import com.insigma.afc.msutil.enums.CDMSConnectionStatus;
import com.insigma.afc.msutil.enums.CDMSConnectionType;
import com.insigma.afc.msutil.enums.CDMSNodeStatus;
import com.insigma.afc.msutil.rmi.dcs.NodeStrategyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Ticket: 检查下级节点对应的MS节点号用service
 * @author chenhangwen
 *
 */
@Service
public class MessageCheckService implements IMessageCheckService {
    private static Logger logger = LogManager.getLogger(MessageCheckService.class);

    // @Autowired
    private JdbcTemplateDao jdbcTemplateDao;

    public MessageCheckService(JdbcTemplate jdbcTemplate) {
        jdbcTemplateDao = new JdbcTemplateDao(jdbcTemplate);
    }

    @Override
    public List<Object[]> checkMessageServer(List<Long> nodeList) {
        try {

            if (jdbcTemplateDao == null) {
                logger.debug("commonDao加载失败");
                return null;
            }

            String sql = "select SC_NODE_ID,MLC_NODE_IP_ADDRESS from TMETRO_MLC_SC_CONNECTION where (SC_NODE_ID,CONNECTION_TIME) in "
                    + " ( select SC_NODE_ID,max(CONNECTION_TIME) from TMETRO_MLC_SC_CONNECTION where SC_NODE_ID in (-1";
            for (Long node : nodeList) {
                //如果节点类型为sle,目标节点为sle的上级节点sc
                if (NodeStrategyUtil.getNodeLevel(node).equals(AFCNodeLevel.SLE)) {
                    node = (long) NodeStrategyUtil.getStationId(node);
                    node = NodeStrategyUtil.getNodeId(node);
                }
                //如果目标节点为sc,判断表中是否存在在线记录,如果存在,则使用该sc节点,如果不存在,则寻找该sc对应的lc节点
                if (NodeStrategyUtil.getNodeLevel(node).equals(AFCNodeLevel.SC)) {
                    if (countNodeIdRecord(node) == 0) {
                        node = (long) NodeStrategyUtil.getLineId(node);
                        node = NodeStrategyUtil.getNodeId(node);
                    }
                }
                sql = sql + "," + node;
            }
            sql = sql + ") and CONNECTION_STATUS = " + CDMSConnectionStatus.Connect + " and CONNECTION_TYPE = "
                    + CDMSConnectionType.Master + " group by SC_NODE_ID )  and CONNECTION_STATUS = "
                    + CDMSConnectionStatus.Connect + " and CONNECTION_TYPE = " + CDMSConnectionType.Master;
            List<Object[]> objList = jdbcTemplateDao.execSqlQuery(sql);
            if (!objList.isEmpty()) {
                return objList;
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("查询节点对应MessageServer失败", e);
            return null;
        }
    }

    /* (non-Javadoc)
     * @see com.insigma.afc.chengdu.service.IMessageCheckService#getAllMSIP()
     */
    @Override
    public List<String> getAllMSIP() {
        try {

            String sql = "select NODE_IP_ADDRESS from TMETRO_MLC_NODE where NODE_STATUS = " + CDMSNodeStatus.Normal
                    + " order by NODE_ID ";
            List<Object> selectList = jdbcTemplateDao.execSqlQuery(sql);
            if (selectList != null) {
                List<String> returnList = new ArrayList<String>();
                for (Object ipAddr : selectList) {
                    if (ipAddr != null) {
                        returnList.add(ipAddr.toString());
                    }
                }
                return returnList;
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("获取所有MS节点IP失败");
            return null;
        }

    }

    /* (non-Javadoc)
     * @see com.insigma.afc.chengdu.service.IMessageCheckService#getAllLinkedNodeId()
     */
    @Override
    public List<Long> getAllLinkedNodeId() {
        try {

            String sql = "select distinct t.SC_NODE_ID from TMETRO_MLC_SC_CONNECTION t where t.CONNECTION_STATUS = "
                    + CDMSConnectionStatus.Connect + " and CONNECTION_TYPE = " + CDMSConnectionType.Master
                    + " order by t.SC_NODE_ID";
            List<Object> selectList = jdbcTemplateDao.execSqlQuery(sql);
            if (selectList != null) {
                List<Long> returnList = new ArrayList<Long>();
                for (Object nodeId : selectList) {
                    if (nodeId != null) {
                        returnList.add(Long.valueOf(nodeId.toString()));
                    }
                }
                return returnList;
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("获取所有已连接节点IP失败");
            return null;
        }
    }

    /* (non-Javadoc)
     * @see com.insigma.afc.chengdu.service.IMessageCheckService#getAllLinkedIP()
     */
    @Override
    public List<String> getAllLinkedIP() {
        try {

            String sql = "select distinct t.MLC_NODE_IP_ADDRESS from TMETRO_MLC_SC_CONNECTION t where t.CONNECTION_STATUS = "
                    + CDMSConnectionStatus.Connect + " and CONNECTION_TYPE = " + CDMSConnectionType.Master;
            List<Object> selectList = jdbcTemplateDao.execSqlQuery(sql);
            if (selectList != null) {
                List<String> returnList = new ArrayList<String>();
                for (Object nodeId : selectList) {
                    if (nodeId != null) {
                        returnList.add(nodeId.toString());
                    }
                }
                return returnList;
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("获取所有已连接节点IP失败");
            return null;
        }
    }

    /* (non-Javadoc)
     * @see com.insigma.afc.chengdu.service.IMessageCheckService#updateLcOnLineRecord(long, long, java.lang.String, java.lang.String, short)
     */
    @Override
    public void updateLcOnLineRecord(long mlcNodeId, long lcNodeId, String mlcIp, String lcIp, short connectStatus) {
        try {

            String sql = "";
            //判断是否存在lc的在线记录
            int count = countNodeIdRecord(lcNodeId);
            //存在则更新在线状态,不存在则新增记录
            if (count > 0) {
                sql = "update TMETRO_MLC_SC_CONNECTION set CONNECTION_TYPE = ? where SC_NODE_ID = ?";
                jdbcTemplateDao.execSqlUpdate(sql, connectStatus, lcNodeId);
            } else if (count == 0) {
                sql = "insert into TMETRO_MLC_SC_CONNECTION values (?,?,?,?,?,?,?)";
                jdbcTemplateDao.execSqlUpdate(sql, mlcNodeId, lcNodeId, mlcIp, lcIp, connectStatus, new Date(), 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /* (non-Javadoc)
     * @see com.insigma.afc.chengdu.service.IMessageCheckService#countNodeIdRecord(long)
     */
    @Override
    public int countNodeIdRecord(long nodeId) {
        nodeId = NodeStrategyUtil.getNodeId(nodeId);
        //判断是否存在下级节点的在线记录
        String sql = "select count(*) from TMETRO_MLC_SC_CONNECTION where SC_NODE_ID = " + nodeId;
        List<Object> result = null;
        try {
            result = jdbcTemplateDao.execSqlCount(sql);
        } catch (Exception e) {
            logger.error("统计下级节点在线记录失败");
            e.printStackTrace();
        }
        int count = 0;
        if (result != null && result.size() > 0) {
            count = ((BigDecimal) result.get(0)).intValue();
        }
        return count;
    }

    /* (non-Javadoc)
     * @see com.insigma.afc.chengdu.service.IMessageCheckService#isLcOnLine(long)
     */
    @Override
    public boolean isLcOnLine(long nodeId) {

        nodeId = NodeStrategyUtil.getNodeId(nodeId);
        long fatherNode = NodeStrategyUtil.getLineId(nodeId);
        fatherNode = NodeStrategyUtil.getNodeId(fatherNode);
        if (countNodeIdRecord(nodeId) == 0 && countNodeIdRecord(fatherNode) > 0) {
            return true;
        }
        return false;
    }
}
