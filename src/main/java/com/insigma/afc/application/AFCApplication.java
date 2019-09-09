/* 
 * 日期：2010-11-8
 *  
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
package com.insigma.afc.application;

import java.util.HashMap;
import java.util.Map;

import com.insigma.commons.application.Application;

/**
 * Ticket: <br/>
 * 
 * @author 邱昌进(qiuchangjin@zdwxjd.com)
 */
public class AFCApplication extends Application {

	//	private static final Log logger = LogFactory.getLog(AFCApplication.class);
	//from wh
	private static Integer fileIndex = 1;
	//	private static MetroNode afcNode;
	//	private static INodeIdStrategy _nodeIdStrategy;
	//	private static Map<String, TsyConfig> tsyConfigs = new HashMap<String, TsyConfig>();
	//	private static IUserService userService;

	//	private static Map<Long, MetroNode> scMetroInfo = new HashMap<Long, MetroNode>();

	private static Map<Long, Boolean> lcPingScMap = new HashMap<Long, Boolean>();

	private static boolean mess4001Relay = false;

	//	private static Map<Long, MetroStation> stationMap = new HashMap<Long, MetroStation>();

	//	public static void init(INodeIdStrategy nodeIdStrategy) {
	//		_nodeIdStrategy = nodeIdStrategy;
	//
	//		AFCConfiguration afconfig = Application.getConfig(AFCConfiguration.class);
	//		if (afconfig == null) {
	//			logger.error("AFCConfig读取不到，可能是没有加载配置，系统将退出。");
	//			System.exit(-1);
	//		}
	//		final NetworkConfig networkInfo = Application.getConfig(NetworkConfig.class);
	//		if (networkInfo == null) {
	//			throw new ApplicationException("请先加载配置文件。");
	//		}
	//
	//		Integer lineNo = networkInfo.getLineNo();
	//		Integer stationNo = networkInfo.getStationNo();
	//		Long deviceId = networkInfo.getDeviceId();
	//		try {
	//			IMetroNodeService service = Application.getService(IMetroNodeService.class);
	//
	//			if (lineNo == null || lineNo == 0) {
	//				MetroACC metroACC = service.getMetroACC();
	//				afcNode = metroACC;
	//				List<MetroLine> lines = service.getAllMetroLine();
	//				metroACC.setLines(lines);
	//				for (MetroLine metroLine : lines) {
	//					List<MetroStation> stations = service.getMetroStation(new Short[] { metroLine.getLineID() }, null);
	//					metroLine.setStations(stations);
	//
	//					for (MetroStation metroStation : stations) {
	//						List<MetroDevice> sles = service
	//								.getMetroDevice(new Integer[] { metroStation.getId().getStationId() }, null);
	//						metroStation.setDevices(sles);
	//						stationMap.put(metroStation.getNodeId(), metroStation);
	//					}
	//				}
	//
	//			} else if (stationNo == null || stationNo == 0) {
	//				List<MetroLine> lines = service.getMetroLine(new Short[] { lineNo.shortValue() });
	//				if (lines == null || lines.isEmpty()) {
	//					logger.error("线路号：" + lineNo + "在数据库中无法初始化，请检查配置文件.");
	//				} else {
	//					MetroLine metroLine = lines.get(0);
	//					afcNode = metroLine;
	//					List<MetroStation> stations = service.getMetroStation(new Short[] { metroLine.getLineID() }, null);
	//					metroLine.setStations(stations);
	//
	//					for (MetroStation metroStation : stations) {
	//						List<MetroDevice> sles = service
	//								.getMetroDevice(new Integer[] { metroStation.getId().getStationId() }, null);
	//						metroStation.setDevices(sles);
	//						stationMap.put(metroStation.getNodeId(), metroStation);
	//					}
	//				}
	//			} else if (deviceId == null || deviceId == 0) {
	//				List<MetroStation> stations = service.getMetroStation(new Short[] { lineNo.shortValue() },
	//						new Integer[] { stationNo.intValue() });
	//				if (stations == null || stations.isEmpty()) {
	//					logger.error("车站号：" + stationNo + "在数据库中无法初始化，请检查配置文件.");
	//				} else {
	//					MetroStation metroStation = stations.get(0);
	//					afcNode = metroStation;
	//					List<MetroDevice> sles = service.getMetroDevice(new Integer[] { stationNo.intValue() }, null);
	//					metroStation.setDevices(sles);
	//					stationMap.put(metroStation.getNodeId(), metroStation);
	//				}
	//
	//				//SC初始化全部节点信息
	//				{
	//
	//					List<MetroLine> lines = service.getMetroLine(new Short[] { lineNo.shortValue() });
	//					if (lines == null || lines.isEmpty()) {
	//						logger.error("线路号：" + lineNo + "在数据库中无法初始化，请检查配置文件.");
	//					} else {
	//						MetroLine metroLine = lines.get(0);
	//
	//						//Lc
	//						scMetroInfo.put(metroLine.getNodeId(), metroLine);
	//
	//						List<MetroStation> staList = service.getMetroStation(new Short[] { metroLine.getLineID() },
	//								null);
	//
	//						for (MetroStation metroStation : staList) {
	//							//Sc
	//							scMetroInfo.put(metroStation.getNodeId(), metroStation);
	//
	//							List<MetroDevice> sles = service
	//									.getMetroDevice(new Integer[] { metroStation.getId().getStationId() }, null);
	//
	//							stationMap.put(metroStation.getNodeId(), metroStation);
	//							for (MetroDevice metroDevice : sles) {
	//								//sle
	//								scMetroInfo.put(metroDevice.getNodeId(), metroDevice);
	//							}
	//						}
	//					}
	//
	//				}
	//
	//			} else {
	//				List<MetroDevice> sles = service.getMetroDevice(new Integer[] { stationNo.intValue() },
	//						new Long[] { deviceId.longValue() });
	//				if (sles == null || sles.isEmpty()) {
	//					logger.error(
	//							"设备：" + String.format(Application.getDeviceIdFormat(), deviceId) + "在数据库中无法初始化，请检查配置文件.");
	//				} else {
	//					afcNode = sles.get(0);
	//				}
	//			}
	//
	//			Application.setData(ApplicationKey.STATION_LIST_KEY, stationMap);
	//		} catch (ServiceException e) {
	//			logger.error("IMetroNodeService无法初始化，可能是Spring还没有初始化.", e);
	//		} catch (Exception e) {
	//			logger.error("获取网络拓扑信息异常。", e);
	//		}
	//
	//		try {
	//			ICommonDAO commonDAO = Application.getService(ICommonDAO.class);
	//			List<TsyConfig> configs = commonDAO.getEntityListHQL("from TsyConfig");
	//			for (TsyConfig tsyConfig : configs) {
	//				final String key = tsyConfig.getConfigKey();
	//				tsyConfigs.put(key, tsyConfig);
	//				System.setProperty(key, tsyConfig.getConfigValue());
	//			}
	//		} catch (Exception e) {
	//			throw new ApplicationException("获取配置信息列表异常。", e);
	//		}
	//
	//	}

	//	public static void initLocal() {
	//		AFCConfiguration afconfig = Application.getConfig(AFCConfiguration.class);
	//		if (afconfig == null) {
	//			logger.error("AFCConfig读取不到，可能是没有加载配置，系统将退出。");
	//			System.exit(-1);
	//		}
	//		final NetworkConfig networkInfo = Application.getConfig(NetworkConfig.class);
	//		if (networkInfo == null) {
	//			throw new ApplicationException("请先加载配置文件。");
	//		}
	//
	//		Integer lineNo = networkInfo.getLineNo();
	//		Integer stationNo = networkInfo.getStationNo();
	//		Long deviceId = networkInfo.getDeviceId();
	//		try {
	//			IMetroNodeService service = Application.getService(IMetroNodeService.class);
	//			if (lineNo == null || lineNo == 0) {
	//				MetroACC metroACC = service.getMetroACC();
	//				afcNode = metroACC;
	//
	//			} else if (stationNo == null || stationNo == 0) {
	//				List<MetroLine> lines = service.getMetroLine(new Short[] { lineNo.shortValue() });
	//				if (lines == null || lines.isEmpty()) {
	//					logger.error("线路号：" + lineNo + "在数据库中无法初始化，请检查配置文件.");
	//				} else {
	//					MetroLine metroLine = lines.get(0);
	//					afcNode = metroLine;
	//				}
	//			} else if (deviceId == null || deviceId == 0) {
	//				List<MetroStation> stations = service.getMetroStation(new Short[] { lineNo.shortValue() },
	//						new Integer[] { stationNo.intValue() });
	//				if (stations == null || stations.isEmpty()) {
	//					logger.error("车站号：" + stationNo + "在数据库中无法初始化，请检查配置文件.");
	//				} else {
	//					MetroStation metroStation = stations.get(0);
	//					afcNode = metroStation;
	//				}
	//			} else {
	//				List<MetroDevice> sles = service.getMetroDevice(new Integer[] { stationNo.intValue() },
	//						new Long[] { deviceId.longValue() });
	//				if (sles == null || sles.isEmpty()) {
	//					logger.error(
	//							"设备：" + String.format(Application.getDeviceIdFormat(), deviceId) + "在数据库中无法初始化，请检查配置文件.");
	//				} else {
	//					afcNode = sles.get(0);
	//				}
	//			}
	//		} catch (ServiceException e) {
	//			logger.error("IMetroNodeService无法初始化，可能是Spring还没有初始化.", e);
	//		} catch (Exception e) {
	//			logger.error("获取网络拓扑信息异常。", e);
	//		}
	//
	//		try {
	//			ICommonDAO commonDAO = Application.getService(ICommonDAO.class);
	//			List<TsyConfig> configs = commonDAO.getEntityListHQL("from TsyConfig");
	//			for (TsyConfig tsyConfig : configs) {
	//				final String key = tsyConfig.getConfigKey();
	//				tsyConfigs.put(key, tsyConfig);
	//				System.setProperty(key, tsyConfig.getConfigValue());
	//			}
	//		} catch (Exception e) {
	//			throw new ApplicationException("获取配置信息列表异常。", e);
	//		}
	//
	//	}

	//	public static void refresh() {
	//		if (_nodeIdStrategy == null) {
	//			logger.error("节点策略未加载，Application刷新失败");
	//			return;
	//		}
	//		init(_nodeIdStrategy);
	//	}

	//	public static String getApplicationName() {
	//		AFCConfiguration afconfig = Application.getConfig(AFCConfiguration.class);
	//		return afconfig.getApplicationName();
	//	}

	//	public static String getVersion(Class<? extends Object> clazz) {
	//		if (clazz == null) {
	//			return "";
	//		}
	//		String path = clazz.getProtectionDomain().getCodeSource().getLocation().getFile();
	//		try {
	//			path = java.net.URLDecoder.decode(path, "UTF-8");
	//			logger.debug("1:" + path);
	//		} catch (java.io.UnsupportedEncodingException ex) {
	//			logger.error("版本获取失败", ex);
	//			return "";
	//		}
	//		java.io.File jarFile = new java.io.File(path);
	//		String jarName = jarFile.getName();
	//		int startIndex = jarName.indexOf("-") + 1;
	//		int endIndex = jarName.indexOf(".jar");
	//		if (startIndex != -1 && endIndex != -1) {
	//			return jarName.substring(startIndex, endIndex);
	//		} else {
	//			return "";
	//		}
	//
	//	}

	//	public static AFCNodeLevel getAFCNodeType() {
	//		if (afcNode == null) {
	//			try {
	//				INodeIdStrategy nodeIdStrategy = Application.getClassBean(INodeIdStrategy.class);
	//				init(nodeIdStrategy);
	//			} catch (ServiceException e) {
	//				logger.error("INodeIdStrategy加载失败", e);
	//			}
	//		}
	//		if (afcNode != null) {
	//			return afcNode.level();
	//		}
	//		return null;
	//	}

	//	public static MetroNode getAFCNode() {
	//		if (afcNode == null) {
	//			try {
	//				//这个方法无法获取INodeIdStrategy,若afcNode为空,检查cinfig.ini是否配置错误
	//				INodeIdStrategy nodeIdStrategy = Application.getClassBean(INodeIdStrategy.class);
	//				init(nodeIdStrategy);
	//			} catch (ServiceException e) {
	//				e.printStackTrace();
	//			}
	//		}
	//		return afcNode;
	//	}

	@SuppressWarnings("unchecked")
	public static <T> T getObject(String key, Class<? extends T> t) {
		Object stringData = Application.getData(key);
		if (stringData != null) {
			return (T) stringData;
		}
		return (T) Application.getData(t.getName());
	}

	public static Object getObject(String key) {
		return Application.getData(key);
	}

	/**
	 * 获取线路号
	 * 
	 * @return
	 */
	//	public static short getLineId() {
	//		NetworkConfig nc = getNetworkConfig();
	//		return nc.getLineNo().shortValue();
	//	}

	/**
	 * 获取车站号
	 * 
	 * @return
	 */
	//	public static int getStationId() {
	//		NetworkConfig nc = getNetworkConfig();
	//		return nc.getStationNo().intValue();
	//	}

	/**
	 * 获取节点号
	 * 
	 * @return
	 */
	//	public static long getNodeId() {
	//		NetworkConfig nc = getNetworkConfig();
	//		return nc.getNodeNo().longValue();
	//	}

	//	private static NetworkConfig getNetworkConfig() {
	//		NetworkConfig nc = getConfig(NetworkConfig.class);
	//		return nc;
	//	}

	//	public static TsyConfig getSystemConfig(String key) {
	//		return tsyConfigs.get(key);
	//	}

	//	public static Integer getSystemIntConfig(String key) {
	//		TsyConfig tsyConfig = tsyConfigs.get(key);
	//		if (tsyConfig == null) {
	//			return null;
	//		}
	//		Integer decode = Integer.decode(tsyConfig.getConfigValue());
	//		return decode;
	//	}

	//	public static String getSystemStringConfig(String key) {
	//		TsyConfig tsyConfig = tsyConfigs.get(key);
	//		if (tsyConfig == null) {
	//			return null;
	//		}
	//		return tsyConfig.getConfigValue();
	//	}

	//	public static String getNodeName(long id) {
	//		MetroNode node = getNode(id);
	//		if (node == null) {
	//			return String.valueOf(id);
	//		}
	//		final AFCNodeLevel level = node.level();
	//		if (level == AFCNodeLevel.SLE) {
	//			return String.format("%s-%s/" + Application.getDeviceIdFormat(), node.getParent().name(), node.name(),
	//					getNodeId(id));
	//		}
	//		return String.format("%s/" + Application.getDeviceIdFormat(), node.name(), getNodeId(id));
	//	}

	//	public static MetroNode getNode(final long mid) {
	//		final long id = getNodeId(mid);
	//		AFCNodeLevel type = getAFCNodeType();
	//
	//		switch (type) {
	//		case ACC:
	//			MetroACC acc = (MetroACC) afcNode;
	//			if (acc.getAccID() == id) {
	//				return acc;
	//			}
	//			Map<Long, MetroLine> lineMap = acc.getLineMap();
	//			if (lineMap.containsKey(id)) {
	//				return lineMap.get(id);
	//			}
	//			for (MetroLine line : lineMap.values()) {
	//				Map<Long, MetroStation> ss = line.getStationMap();
	//				if (ss.containsKey(id)) {
	//					return ss.get(id);
	//				}
	//				for (MetroStation station : ss.values()) {
	//					Map<Long, MetroDevice> ds = station.getDeviceMap();
	//					if (ds.containsKey(id)) {
	//						return ds.get(id);
	//					}
	//				}
	//			}
	//			break;
	//		case LC:
	//			MetroLine line = (MetroLine) afcNode;
	//			if (line.getNodeId() == (id)) {
	//				return line;
	//			}
	//			Map<Long, MetroStation> ss = line.getStationMap();
	//			if (ss.containsKey(id)) {
	//				return ss.get(id);
	//			}
	//			for (MetroStation station : ss.values()) {
	//				Map<Long, MetroDevice> ds = station.getDeviceMap();
	//				if (ds.containsKey(id)) {
	//					return ds.get(id);
	//				}
	//			}
	//
	//			break;
	//		case SC:
	//			MetroStation station = (MetroStation) afcNode;
	//			if (station.getNodeId() == (id)) {
	//				return station;
	//			}
	//			Map<Long, MetroDevice> ds = station.getDeviceMap();
	//			if (ds.containsKey(id)) {
	//				return ds.get(id);
	//			}
	//
	//			//SC上会出现其他车站的节点信息，需要显示名称
	//			if (scMetroInfo.containsKey(id)) {
	//				return scMetroInfo.get(id);
	//			}
	//
	//			break;
	//		default:
	//			break;
	//		}
	//		return null;
	//	}

	//	public static INodeIdStrategy getNodeIdStrategy() {
	//		return _nodeIdStrategy;
	//	}

	//	public static long getNodeId(long id) {
	//		return _nodeIdStrategy.getNodeId(id);
	//	}

	//	public static short getLineId(long nodeId) {
	//		return _nodeIdStrategy.getLineId(nodeId);
	//	}

	//	public static int getStationId(long nodeId) {
	//		return _nodeIdStrategy.getStationId(nodeId);
	//	}

	//	public static short getNodeType(long nodeId) {
	//		return _nodeIdStrategy.getNodeType(nodeId);
	//	}

	//	public static short getNodeType() {
	//		return _nodeIdStrategy.getNodeType(getNodeId());
	//	}

	//	public static short getStationIdOnly(long nodeId) {
	//		return _nodeIdStrategy.getStationOnlyId(nodeId);
	//	}

	//	public static short getDeviceId(long nodeId) {
	//		return _nodeIdStrategy.getDeviceId(nodeId);
	//	}

	//	public static AFCNodeLevel getNodeLevel(long nodeId) {
	//		return _nodeIdStrategy.getNodeLevel(nodeId);
	//	}

	//	public static long getFatherNode(long nodeId) {
	//		return _nodeIdStrategy.getFatherNode(nodeId);
	//	}

	//	public static List<Object[]> getAllMetroNode() {
	//		return _nodeIdStrategy.getAllMetroNode();
	//	}

	//	public static String getUserNameByUserId(String userId) {
	//
	//		String userName = "无";
	//
	//		if (userService == null) {
	//			try {
	//				userService = Application.getService(IUserService.class);
	//			} catch (ServiceException e) {
	//				logger.error("加载IUserService失败。", e);
	//			}
	//		}
	//		if (userId == null || ("").equals(userId)) {
	//			return userName;
	//		} else if (userId.equals(BackgroundUser.COMPUTER.getUserId())) {
	//			return BackgroundUser.COMPUTER.getUserName() + "/" + BackgroundUser.COMPUTER.getUserId();
	//		}
	//
	//		TapUser tapUser = userService.getUserByUserId(userId, false);
	//		if (tapUser != null) {
	//			userName = tapUser.getUserName();
	//		}
	//
	//		if (tapUser == null) {
	//			userName = "未知 /" + userId;
	//			return userName;
	//		}
	//
	//		return userName + "/" + userId;
	//	}

	public static Map<Long, Boolean> getLcPingScMap() {
		return lcPingScMap;
	}

	public static void setLcPingScMap(Map<Long, Boolean> lcPingScMap) {
		AFCApplication.lcPingScMap = lcPingScMap;
	}

	public static void addLcPingScMap(Long nodeId, Boolean bo) {
		lcPingScMap.put(nodeId, bo);
	}

	public static boolean isMess4001Relay() {
		return mess4001Relay;
	}

	public static void setMess4001Relay(boolean mess4001Relay) {
		AFCApplication.mess4001Relay = mess4001Relay;
	}

	//from wh
	public static Integer getFileIndex() {
		synchronized (fileIndex) {
			return fileIndex++;
		}
	}

}
