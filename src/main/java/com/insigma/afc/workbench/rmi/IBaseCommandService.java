/* 
 * 日期：2010-9-17
 *  
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
package com.insigma.afc.workbench.rmi;

import com.insigma.commons.application.IService;

/**
 * 工作台与服务器之间的命令接口
 * 
 * @author 廖红自
 */
public interface IBaseCommandService extends IService {

	/**
	 * 工作台注册
	 * 
	 * @param destLogicAddress
	 * @param srcLogicAddress
	 * @param destDeviceID
	 * @param registerTime
	 * @return 注册结果信息
	 */
	RegisterResult register();

	void isAlive();
}
