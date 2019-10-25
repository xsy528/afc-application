/* 
 * 日期：2010-8-25
 *  
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
package com.insigma.afc.ftp;

import com.insigma.commons.application.IService;
import com.insigma.commons.communication.ftp.FtpInfo;

/**
 * FTP管理 Workbench专用 
 * 
 * @author fenghong
 */
public interface IFTPInfoManager extends IService {

	/**
	 * 获取工作台文件导入的FTP信息
	 * 
	 * @param fileType
	 * @return
	 */
	FtpInfo getImportFTPInfo(short lineId, int stationId, int fileType);

	/**
	 * 获取导入的ftp目录
	 * @param parameterType
	 * @return
	 */
	FtpInfo getParameterImportFTPInfo(short lineId, int stationId, int parameterType);

	/**
	 * 获取工作台文件导出的FTP信息,如果是分线路导出的话需要修改
	 * 
	 * @param lineId
	 * @param stationId
	 * @param fileType
	 * @return
	 */
	FtpInfo getExportFTPInfo(short lineId, int stationId, int fileType);

}
