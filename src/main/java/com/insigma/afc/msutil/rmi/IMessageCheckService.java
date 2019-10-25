/* 
 * 日期：2019年8月20日
 *  
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
/**
 * 
 */
package com.insigma.afc.msutil.rmi;

import java.util.List;


/**
 * Ticket: 检查下级节点对应的MS节点号用service
 * @author  chenhangwen
 *
 */
public interface IMessageCheckService {

	public List<Object[]> checkMessageServer(List<Long> nodeList);

	public List<String> getAllMSIP();

	public List<Long> getAllLinkedNodeId();

	public List<String> getAllLinkedIP();

	public void updateLcOnLineRecord(long mlcNodeId, long lcNodeId, String mlcIp, String lcIp, short connectStatus);

	public int countNodeIdRecord(long nodeId);

	public boolean isLcOnLine(long nodeId);

}
