/**
 * iFrameWork 框架
 * 
 * @component     应用程序框架
 * @author        Jiangxufeng
 * @email         jiangxufeng@zdwxjd.com
 * @version       1.0
 * @description   
 * @date          2009-4-2
 * @copyright     浙江浙大网新众合轨道交通工程有限公司 
 * @history       
 */
package com.insigma.commons.application;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.Configuration;

@SuppressWarnings("unchecked")
public class Application {

	//	private static Log logger = LogFactory.getLog(Application.class);

	private static HashMap<Object, Object> values = new HashMap<Object, Object>();

	//	private static List<IUserChangedListener> userChangedListeners;

	//	private static IConfigurationProvider configProvider;

	private static Configuration config;

	//	private static IUser user;

	//	private static Map<String, IService> services = new HashMap<String, IService>();

	private static Map<String, Object> classBeans = new HashMap<String, Object>();

	//	public static int systemState = SystemState.UN_INIT;

	private static Map<Long, String> imagePaths = new HashMap<Long, String>();

	//shenchao 节点编号String.format
	private static String deviceIdFormat = "%08x";

	private static String stationlineIdFormat = "%04x";

	private static String lineIdFormat = "%02x";

	private static String stationIdFormat = "%02x";

	private static String deviceTypeFormat = "%02x";

	private static String deviceIndexFormat = "%03x";

	public static void registerClassBean(String name, Object classBean) {
		classBeans.put(name, classBean);
	}

	public static void registerClassBean(Class<?> cls, Object classBean) {
		classBeans.put(cls.getName(), classBean);
	}

	//	public static void registerService(String name, IService service) {
	//		services.put(name, service);
	//	}

	//	public static void registerService(Class<?> cls, IService service) {
	//		services.put(cls.getName(), service);
	//	}

	//	public static void addUserChangedListener(IUserChangedListener userChangedListener) {
	//		if (userChangedListeners == null) {
	//			userChangedListeners = new ArrayList<IUserChangedListener>();
	//		}
	//		userChangedListeners.add(userChangedListener);
	//	}

	public static Object getData(int key) {
		String keys = String.valueOf(key);
		return values.get(keys);
	}

	//	public static void setData(int key, Object value) {
	//		String keys = String.valueOf(key);
	//		if (values.containsKey(keys)) {
	//			logger.warn("已经包含KEY为[ " + keys + " ]的数据, 重新加载");
	//			values.remove(keys);
	//			// values.put(keys, value);
	//		}
	//		values.put(keys, value);
	//	}

	public static Object getData(String key) {
		return values.get(key);
	}

	public static void setData(String key, Object value) {
		values.put(key, value);
	}

	//	public static IUser getUser() {
	//		if (user == null) {
	//			user = new AnonymousUser();
	//		}
	//		return user;
	//	}

	//	public static void setUser(IUser user) {
	//		Application.user = user;
	//	}

	//	public static Object getClassByName(String beanName) {
	//		if (classBeans.containsKey(beanName)) {
	//			return classBeans.get(beanName);
	//		}
	//		return BeanFactoryUtil.getBean(beanName);
	//	}

	//	public static <T> T getClassByName(String beanName) {
	//		if (classBeans.containsKey(beanName)) {
	//			return (T) classBeans.get(beanName);
	//		}
	//		return (T) BeanFactoryUtil.getBean(beanName);
	//	}

	//	public static <T> T getClassBean(Class<T> cls, T defaultValue) throws ServiceException {
	//		if (classBeans.containsKey(cls.getName())) {
	//			return (T) classBeans.get(cls.getName());
	//		}
	//		String[] beans = BeanFactoryUtil.getApplicationContext().getBeanNamesForType(cls);
	//
	//		if (beans == null || beans.length <= 0) {
	//			return defaultValue;
	//		}
	//		if (beans.length > 1) {
	//			throw new ServiceException(
	//					"There are " + beans.length + " " + cls.getName() + " implements in application context");
	//		}
	//		return (T) BeanFactoryUtil.getBean(beans[0]);
	//	}

	//	public static <T> T getClassBean(Class<T> cls) throws ServiceException {
	//		if (classBeans.containsKey(cls.getName())) {
	//			return (T) classBeans.get(cls.getName());
	//		}
	//		String[] beans = BeanFactoryUtil.getApplicationContext().getBeanNamesForType(cls);
	//
	//		if (beans == null || beans.length <= 0) {
	//			throw new ServiceException("Service Class " + cls.getName() + " is not found");
	//		}
	//		if (beans.length > 1) {
	//			throw new ServiceException(
	//					"There are " + beans.length + " " + cls.getName() + " implements in application context。");
	//		}
	//		return (T) BeanFactoryUtil.getBean(beans[0]);
	//	}

	//	public static <T extends IService> T getService(Class<T> cls) throws ServiceException {
	//		if (services.containsKey(cls.getName())) {
	//			return (T) services.get(cls.getName());
	//		} else {
	//			String[] beans = BeanFactoryUtil.getApplicationContext().getBeanNamesForType(cls);
	//			if (beans == null || beans.length <= 0) {
	//				throw new ServiceException("Service Class " + cls.getName() + " is not found");
	//			}
	//			if (beans.length > 1) {
	//				throw new ServiceException(
	//						"There are " + beans.length + " " + cls.getName() + " implements in application context。");
	//			}
	//			return (T) BeanFactoryUtil.getBean(beans[0]);
	//		}
	//
	//	}

	/**
	 * 用beanName获取service
	 * 
	 * @param beanName
	 * @return
	 */
	//	public static <T extends IService> T getServiceByBeanName(String beanName) {
	//		if (services.containsKey(beanName)) {
	//			return (T) services.get(beanName);
	//		} else {
	//			IService service = null;
	//
	//			try {
	//				service = (IService) BeanFactoryUtil.getApplicationContext().getBean(beanName);
	//			} catch (Exception ex) {
	//				throw new ApplicationException("bean:" + beanName + " 在配置文件spring-config中不存在。\n", ex);
	//			}
	//			return (T) service;
	//		}
	//	}

	//	public static IConfigurationProvider getConfigProvider() {
	//		if (configProvider == null) {
	//			configProvider = new XMLConfigurationProvider();
	//		}
	//		return configProvider;
	//	}

	//	public static void setConfigProvider(IConfigurationProvider configProvider) {
	//		Application.configProvider = configProvider;
	//	}

	//	public static <T extends ConfigurationItem> T getConfigItem(T configInstance) {
	//		return configProvider.getConfigItem(configInstance);
	//	}

	//	public static <T extends ConfigurationItem> T getConfig(Class<T> cls) {
	//		return (T) configProvider.getConfig(cls);
	//	}

	public static Configuration getConfig() {
		return config;
	}

	public static void setConfig(Configuration config) {
		Application.config = config;
	}

	//	public static void loadConfiguration(Class<? extends ConfigurationItem>... externalConfigClasses) {
	//		configProvider.loadConfiguration(externalConfigClasses);
	//	}

	//	public static void loadConfiguration(String configFileName,
	//			Class<? extends ConfigurationItem>... externalConfigClasses) {
	//		configProvider.loadConfiguration(configFileName, externalConfigClasses);
	//	}

	//	public static void initialize(String... files) {
	//		BeanFactoryUtil.initBean(files);
	//	}

	public static <T> T getObject(String key, Class<? extends T> t) {
		if (values.containsKey(key)) {
			return (T) values.get(key);
		}
		Object stringData = Application.getData(key);
		if (stringData != null) {
			return (T) stringData;
		}
		return (T) Application.getData(t.getName());
	}

	public static Object getObject(String key) {
		if (values.containsKey(key)) {
			return values.get(key);
		}
		return Application.getData(key);
	}

	public static void setObject(String key, Object object) {
		values.put(key, object);
	}

	public static Map<Long, String> getImagePaths() {
		return imagePaths;
	}

	public static void setImagePaths(Map<Long, String> imagePaths) {
		Application.imagePaths = imagePaths;
	}

	public static String getImagePath(long key) {
		String imagePath = null;
		imagePath = imagePaths.get(key);
		return imagePath;
	}

	//key--nodeid Map状态  key--id tree状态
	public static void addImagePath(long key, String imagePath) {
		if (imagePaths.get(key) != null) {
			imagePaths.remove(key);
		}
		imagePaths.put(key, imagePath);
	}

	public static String getDeviceIdFormat() {
		return deviceIdFormat;
	}

	public static void setDeviceIdFormat(String deviceIdFormat) {
		Application.deviceIdFormat = deviceIdFormat;
	}

	public static String getStationlineIdFormat() {
		return stationlineIdFormat;
	}

	public static void setStationlineIdFormat(String stationlineIdFormat) {
		Application.stationlineIdFormat = stationlineIdFormat;
	}

	public static String getLineIdFormat() {
		return lineIdFormat;
	}

	public static void setLineIdFormat(String lineIdFormat) {
		Application.lineIdFormat = lineIdFormat;
	}

	public static String getStationIdFormat() {
		return stationIdFormat;
	}

	public static void setStationIdFormat(String stationIdFormat) {
		Application.stationIdFormat = stationIdFormat;
	}

	public static String getDeviceTypeFormat() {
		return deviceTypeFormat;
	}

	public static void setDeviceTypeFormat(String deviceTypeFormat) {
		Application.deviceTypeFormat = deviceTypeFormat;
	}

	/**
	 * @return the deviceIndexFormat
	 */
	public static String getDeviceIndexFormat() {
		return deviceIndexFormat;
	}

	/**
	 * @param deviceIndexFormat the deviceIndexFormat to set
	 */
	public static void setDeviceIndexFormat(String deviceIndexFormat) {
		Application.deviceIndexFormat = deviceIndexFormat;
	}
}
