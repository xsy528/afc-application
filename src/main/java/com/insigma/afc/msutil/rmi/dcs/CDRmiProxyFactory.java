/*
 * 日期：2019-8-20
 *
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
package com.insigma.afc.msutil.rmi.dcs;

import com.insigma.afc.ftp.properties.RmiProperties;
import com.insigma.afc.msutil.dao.util.JdbcTemplateDao;
import com.insigma.afc.msutil.enums.AFCCmdResultType;
import com.insigma.afc.msutil.enums.AFCDeviceType;
import com.insigma.afc.topology.MetroDevice;
import com.insigma.afc.topology.MetroLine;
import com.insigma.afc.topology.MetroNode;
import com.insigma.afc.topology.MetroStation;
import com.insigma.afc.workbench.rmi.CmdHandlerResult;
import com.insigma.afc.msutil.model.CommandResult;
import com.insigma.afc.msutil.rmi.IMessageCheckService;
import com.insigma.afc.msutil.rmi.MessageCheckService;
import com.insigma.afc.workbench.rmi.ICommandService;
import com.insigma.commons.util.DateTimeUtil;
import com.insigma.commons.util.NodeIdUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

import static com.insigma.afc.msutil.enums.AFCDeviceType.LC;
import static com.insigma.afc.msutil.enums.AFCDeviceType.SC;
import static com.insigma.afc.msutil.rmi.dcs.NodeStrategyUtil.getNodeId;


/**
 *
 * Ticket:  获取分布式RMI服务实例
 * @author  xingshaoya
 * 使用说明:
 * 1. 在构造时传入rmi接口泛型,rmi端口号，rmi服务名及rmi接口类型 以确定rmi服务
 * 2. 调用InitIpToProxyService方法传入车站列表,初始化rmi服务 (根据车站号获取ip,根据ip获取rmi对象)
 * 3. getProxyBeanByStationId 根据车站号获取rmi代理服务
 * 4. getAllIpToNodesMap 获取ip对应车站列表映射关系
 * 5. getAllIpToProxyServiceMap 获取ip对应代理服务映射关系
 * 6. getNodesByIp 通过Ip获取车站列表
 * 7. getProxyServiceByIp 通过ip获取rmi代理服务
 * 8. getDefaultService 无目标车站时获取默认的rmi服务,调用该方法前无需调用InitIpToProxyService
 */
public class CDRmiProxyFactory<T> {

	private IMessageCheckService messageCheckService;
	private static Log logger = LogFactory.getLog(CDRmiProxyFactory.class);
	private Map<String, T> ipToProxyServiceMap = null;
	private Map<Long, String> nodeIdToIpMap = null;
	private Map<String, List<Long>> ipToNodesMap = null;
	private String port;
	private String serviceName;
	private Class serviceInterface;

	public CDRmiProxyFactory(String port, String serviceName, Class serviceInterface, JdbcTemplate commonDao) {
		try {
			this.port = port;
			this.serviceName = serviceName;
			this.serviceInterface = serviceInterface;
			messageCheckService = new MessageCheckService(commonDao);
			ipToProxyServiceMap = new HashMap<>();
			nodeIdToIpMap = new HashMap<>();
			ipToNodesMap = new HashMap<>();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public T getProxyBean(String ip) {
		T service = null;
		try {
			//获取IP
			String serviceUrl = "rmi://" + ip + ":" + port + "/" + serviceName;
			logger.info("rmi地址为: " + serviceUrl);
			//设置rmi代理对象属性
			RmiProxyFactoryBean rmiBean = new RmiProxyFactoryBean();
			rmiBean.setServiceUrl(serviceUrl);
			rmiBean.setServiceInterface(serviceInterface);
			rmiBean.setLookupStubOnStartup(false);
			rmiBean.setRefreshStubOnConnectFailure(true);
			//获取远程对象
			rmiBean.afterPropertiesSet();
			service = (T) rmiBean.getObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("rmi代理对象生成失败");
		}
		return service;
	}

	//通过目的车站号获取对应的消息服务器RMI代理对象
	public T getProxyBeanByNodeId(long nodeId) {
		T service = null;
		if (ipToProxyServiceMap.size() == 0 || nodeIdToIpMap.size() == 0) {
			logger.error("请先调用InitIpToProxyService方法，初始化rmi对象");
			return null;
		}

		//如果车站节点不在线且该车站的线路节点在线,则使用线路节点
		if (messageCheckService.isLcOnLine(nodeId)) {
			nodeId = NodeStrategyUtil.getLineId(nodeId);
		}
		//根据车站号获取对应ms服务的ip
		nodeId = NodeStrategyUtil.getNodeId(nodeId);
		String descIp = nodeIdToIpMap.get(nodeId);
		//根据ip获取对应的rmi代理服务对象
		service = ipToProxyServiceMap.get(descIp);
		return service;
	}

	//为车站列表中的车站生成对应的rmi代理对象
	public void InitIpToProxyService(List<Long> nodes) {
		List<Long> nodeList = new ArrayList<>();
		for (Long node : nodes) {
			long nodeId = getNodeId(node);
			if (!nodeList.contains(nodeId)) {
				nodeList.add(nodeId);
			}
		}
		List<Object[]> result = messageCheckService.checkMessageServer(nodeList);
		if (result != null && result.size() > 0) {
			for (Object[] obj : result) {
				if (obj != null && obj[0] != null && obj[1] != null) {
					Long nodeId = ((BigDecimal) obj[0]).longValue();
					String destIp =  obj[1]+"";
					//相同ip的rmi服务,只获取一次
					if (ipToProxyServiceMap.get(destIp) == null) {
						T service = getProxyBean(destIp);
						ipToProxyServiceMap.put(destIp, service);
					}
					nodeIdToIpMap.put(nodeId, destIp);
					//存储ip对应车站列表
					if (ipToNodesMap.get(destIp) == null) {
						List<Long> stationList = new ArrayList<>();
						ipToNodesMap.put(destIp, stationList);
					}
					ipToNodesMap.get(destIp).add(nodeId);
				} else {
					logger.error("数据库中rmi服务ip映射数据错误");
				}
			}
		} else {
			logger.error("数据库中rmi服务ip映射数据错误");
		}
	}

	/**
	 * 调用CommonService接口获取CommonResult集合
	 * @param userId
	 * @param node
	 * @param arg
	 * @param id
	 * @param cmdType
	 * @param name
	 * @param isSaveCmd
	 * @return
	 */
	//@Transactional
	public static List<CommandResult> getCommandResult(JdbcTemplate jdbcTemplate, final String userId,
                                                       final MetroNode node, final Object arg, final int id, final short cmdType, final String name, boolean isSaveCmd){

		List<CommandResult> results = new Vector<>();
		RmiProperties properties = new RmiProperties();

		int result = AFCCmdResultType.SEND_FAILED;
		String resultDesc = null;
		try {
			//根据车站号获取对应的commandService
		//	if (getNodeLevel(node.id()).equals(AFCNodeLevel.ACC)) {
				CDRmiProxyFactory<ICommandService> rmiProxyFactory = new CDRmiProxyFactory<>(properties.getCommandServiceRmiPort()+"", "CommandService", ICommandService.class,jdbcTemplate);
				List<Long> nodes = new ArrayList<>();
				ICommandService commandService = null;
				if(node instanceof MetroStation){
					MetroStation station = (MetroStation) node;
					nodes.add(NodeStrategyUtil.getNodeId(station.getLineId()));
					nodes.add(NodeStrategyUtil.getNodeId(station.getStationId()));
					//nodes.add(station.getNodeId());
					rmiProxyFactory.InitIpToProxyService(nodes);
					commandService = rmiProxyFactory.getProxyBeanByNodeId(NodeStrategyUtil.getNodeId(node.getStationId()));
				}else if(node instanceof MetroDevice){
					MetroDevice device = (MetroDevice)node;
					nodes.add(NodeStrategyUtil.getNodeId(device.getLineId()));
					nodes.add(NodeStrategyUtil.getNodeId(device.getStationId()));
					nodes.add(device.getNodeId());
					rmiProxyFactory.InitIpToProxyService(nodes);
					commandService = rmiProxyFactory.getProxyBeanByNodeId(node.getNodeId());
				}else if(node instanceof MetroLine){
					MetroLine line = (MetroLine)node; 
					nodes.add(NodeStrategyUtil.getNodeId(line.getLineId()));
					nodes.add(NodeStrategyUtil.getNodeId(0));
					//nodes.add(line.getNodeId());
					rmiProxyFactory.InitIpToProxyService(nodes);
					commandService = rmiProxyFactory.getProxyBeanByNodeId(NodeStrategyUtil.getNodeId(node.getLineId()));
				}
		//	}
			try{
				commandService.alive();
			}catch (Exception e){
				logger.debug("访问远程RMI接口失败！");
				return  null;
			}
//			MetroStation target = new MetroStation();
//			switch (node.getNodeType()) {
//				case LC: {
//					MetroLine metroLine = (MetroLine) node;
//					target.setLineID(metroLine.getLineID());
//					target.setNodeId(NodeIdUtils.nodeIdStrategy.getNodeNo(metroLine.getLineID().longValue()));
//					break;
//				}
//				case SC: {
//					MetroStation metroStation = (MetroStation) node;
//					target.setLineID(metroStation.getLineId());
//					target.setNodeId(NodeIdUtils.nodeIdStrategy.getNodeNo(metroStation.getLineId().longValue()));
//					break;
//				}
//				case SLE: {
//					MetroDevice metroDevice = (MetroDevice) node;
//					target.setLineID(metroDevice.getLineId());
//					target.setNodeId(NodeIdUtils.nodeIdStrategy.getNodeNo(metroDevice.getLineId().longValue()));
//					break;
//				}
//				default:
//			}
				//将目标节点转化为发送命令节点
//				com.insigma.afc.topology.MetroLine target = new com.insigma.afc.topology.MetroLine();
			CmdHandlerResult command = commandService.command(id, userId,
					node.getNodeId(), arg, node);
			Serializable returnValue = command.returnValue;
			if (returnValue != null && returnValue instanceof Integer) {
				result = (Integer) returnValue;
			} else if (command.isOK) {
				result = AFCCmdResultType.SEND_SUCCESSFUL;
			}
			resultDesc = command.getResultMessage();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("发送" + name + "错误", e);
		}
//		logger.info("向节点" + node.name() + "发送" + name + "  返回：" + result);
		results.add(save(node, name, arg, result, cmdType, resultDesc,userId,isSaveCmd,jdbcTemplate));

		return results;
	}

	public static CommandResult save(final MetroNode node, String command, final Object arg, final int result,
							  final short cmdType, final String resultDesc,String userId,boolean isSaveCmd,JdbcTemplate jdbcTemplate) {

//		String resultMessageShow = "发送结果：\n\n";

		if (result == 0) {
//			resultMessageShow += "向节点" + node.name() + "发送 " + command + " 命令发送成功。\n";
//			if (this.logService != null) {
//				logService.doBizLog(resultMessageShow);
//			}
		} else {
//			resultMessageShow += "向节点" + node.name() + "发送 " + command + " 命令失败。错误码：" + result + "。";
//			if (this.logService != null) {
//				try {
//					logService.doBizErrorLog(resultMessageShow);
//				} catch (Exception e) {
//					logger.error("发送命令保存日志失败", e);
//				}
//			}
		}

		if(isSaveCmd) {
			StringBuilder sql = new StringBuilder("insert into TMO_CMD_RESULT values(");
//			TmoCmdResult tmoCmdResult = new TmoCmdResult();
//			tmoCmdResult.setOccurTime(DateTimeUtil.getNow());
			if (arg != null) {
				//            tmoCmdResult.setRemark("命令参数：" + BeanUtil.toString(arg));
				//						tmoCmdResult.setTagValue(tagValue)
//			if (arg instanceof Form) {
//				Form form = (Form) arg;
//				String string = form.getEntity().toString();
//				command = string; }
			}

//			tmoCmdResult.setCmdName(command);
				Short lineID = null;
				Integer stationId = null;
				Long nodeId = null;
				Short nodeType = null;
			if (node instanceof MetroLine) {
				MetroLine line = (MetroLine) node;
				lineID = line.getLineID();
				stationId = 0;
				nodeId = getNodeId(lineID);
				nodeType = LC;
//				tmoCmdResult.setLineId(lineID);
//				tmoCmdResult.setStationId(0);
//				tmoCmdResult.setNodeId(getNodeId(lineID));
//				tmoCmdResult.setNodeType(AFCDeviceType.LC);
				}
//
			if (node instanceof MetroStation) {
				MetroStation station = (MetroStation) node;
				lineID = station.getLineId();
//				tmoCmdResult.setLineId();
				stationId = station.getStationId();
				nodeId = getNodeId(stationId);
				nodeType = SC;
//				tmoCmdResult.setStationId(stationId);
//				tmoCmdResult.setNodeId(getNodeId(stationId));
//				tmoCmdResult.setNodeType(AFCDeviceType.SC);
			}
//
			if (node instanceof MetroDevice) {
				MetroDevice device = (MetroDevice) node;
				lineID = device.getLineId();
				stationId = device.getStationId();
				nodeId = device.getNodeId();
				nodeType = device.getDeviceType();

//				tmoCmdResult.setLineId(device.getLineId());
//				tmoCmdResult.setStationId(device.getStationId());
//				tmoCmdResult.setNodeId(device.getDeviceId());
//				tmoCmdResult.setNodeType(device.getDeviceType());
			}
			String logIdSql = "select S_TMO_CMD_RESULT.nextval from dual";
			long logId = jdbcTemplate.queryForObject(logIdSql,long.class);

			sql.append(logId+","+lineID+","+stationId+","+nodeId+","+nodeType+",'"+userId+"',"+cmdType+",'"+command+
					"','','',"+(short) result+",TO_DATE('"+DateTimeUtil.formatCurrentDateToString("yyyy-MM-dd HH:mm:ss")+
					"', 'YYYY-MM-DD HH24:MI:SS'),'"+resultDesc+"',"+(short) 0);
			//"+DateTimeUtil.getNow()+"
			sql.append(")");
//			tmoCmdResult.setUploadStatus((short) 0);
//			tmoCmdResult.setOperatorId(userId);
//			tmoCmdResult.setCmdResult((short) result);
//			tmoCmdResult.setRemark(resultDesc);
//			tmoCmdResult.setCmdType(cmdType);
			try {
				//commonDao.execSqlUpdate(,null);
				JdbcTemplateDao jdbcTemplateDao = new JdbcTemplateDao(jdbcTemplate);
				jdbcTemplateDao.execSqlUpdate(sql.toString(),null);
				logger.info("日志命令保存成功！！");
			} catch (Exception e) {
				logger.error("保存命令日志异常", e);
			}
		}
		CommandResult resultitem = new CommandResult();
		resultitem.setId(node.getPicName());
		resultitem.setCmdName(command);
		resultitem.setResult((short) result);
		resultitem.setCmdResult(resultDesc);
		resultitem.setOccurTime(DateTimeUtil.formatCurrentDateToString("yyyy-MM-dd HH:mm:ss"));
		if (arg != null) {
			resultitem.setArg(arg.toString());
		} else {
			resultitem.setArg("无");
		}

		return resultitem;
	}


	//返回ip对应车站号列表映射关系
	public Map<String, List<Long>> getAllIpToNodesMap() {
		return ipToNodesMap;
	}

	//返回ip对应rmi代理服务映射关系
	public Map<String, T> getAllIpToProxyServiceMap() {
		return ipToProxyServiceMap;
	}

	//通过ip获取对应车站号列表
	public List<Long> getNodesByIp(String ip) {
		return ipToNodesMap.get(ip);
	}

	//返回ip对应rmi代理服务
	public T getProxyServiceByIp(String ip) {
		return ipToProxyServiceMap.get(ip);
	}

	public T getDefaultService() {
		T service = null;
		List<String> result = messageCheckService.getAllLinkedIP();
		if (result != null && result.size() > 0) {
			String destIp = result.get(0);
			service = getProxyBean(destIp);
		} else {
			logger.error("数据库中rmi服务ip映射数据错误");
		}
		return service;
	}

}
