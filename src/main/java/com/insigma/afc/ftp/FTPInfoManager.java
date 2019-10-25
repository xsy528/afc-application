/* 
 * 日期：2010-9-17
 *  
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
package com.insigma.afc.ftp;

import com.insigma.afc.consts.dic.AFCFTPFileType;
import com.insigma.commons.communication.ftp.FtpInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FTP信息 Ticket:
 * 
 * @author fenghong
 */
public class FTPInfoManager implements IFTPInfoManager {
	public enum FtpType {
		EXPORT_FTP, IMPORT_FTP
	}

	private Map<FtpType, List<FtpInfo>> listMap = new HashMap<FtpType, List<FtpInfo>>();

	public FTPInfoManager(List<FtpInfo> importList, List<FtpInfo> exportList) {
		this.listMap.put(FtpType.IMPORT_FTP, importList);
		this.listMap.put(FtpType.EXPORT_FTP, exportList);
	}

	/**
	 * 获取FTP信息,工作台导出使用
	 * 
	 * @param lineId
	 * @param stationId
	 * @param fileType
	 * @return
	 */
	@Override
	public FtpInfo getExportFTPInfo(short lineId, int stationId, int fileType) {

		//printMap(listMap);
		return getFTPInfo(lineId, stationId, fileType, listMap.get(FtpType.EXPORT_FTP));
	}
		private void printMap(Map<FtpType, List<FtpInfo>> line) {
			for (Map.Entry<FtpType, List<FtpInfo>> entry : line.entrySet()) {
				List<FtpInfo> value = entry.getValue();
				for(FtpInfo info:value){
					System.out.println(info.getFileType() + "=========>>>" + info.getFtpKey());
				}

			}
		}
	/**
	 * 获取FTP信息,工作台导入使用
	 * 
	 * @param lineId
	 * @param stationId
	 * @param fileType
	 * @return
	 */
	@Override
	public FtpInfo getImportFTPInfo(short lineId, int stationId, int fileType) {

		return getFTPInfo(lineId, stationId, fileType, listMap.get(FtpType.IMPORT_FTP));
	}

	/**
	 * 获取导入的ftp目录
	 * @param parameterType
	 * @return
	 */
	@Override
	public FtpInfo getParameterImportFTPInfo(short lineId, int stationId, int parameterType) {
		FtpInfo ftpInfo = getImportFTPInfo(lineId,stationId, AFCFTPFileType.EOD);
		if (ftpInfo == null) {
			return null;
		}
		ftpInfo.setWorkDir(ftpInfo.getWorkDir() + "/" + parameterType);
		return ftpInfo;
	}

	/**
	 * 获取FPT信息列表
	 * 
	 * @param lineId
	 * @param stationId
	 * @param fileType
	 * @param ftpList
	 * @return
	 */
	private FtpInfo getFTPInfo(short lineId, int stationId, int fileType, List<FtpInfo> ftpList) {
		if (ftpList == null) {
			return null;
		}
		String key = getFtpKey(lineId, stationId, fileType);
		for (FtpInfo f : ftpList) {
			if (fileType == f.getFileType()) {
				String ftpKey = f.getFtpKey();
				if (ftpKey == null || ftpKey.equals("")) {
					// 默认为0的值
					ftpKey = getFtpKey((short) 0, 0, fileType);
				}
				if (key.equalsIgnoreCase(ftpKey)) {
					FtpInfo copyFtp = new FtpInfo();
					copyFtp.setFileType(f.getFileType());
					copyFtp.setFtpKey(f.getFtpKey());
					copyFtp.setHost(f.getHost());
					copyFtp.setPass(f.getPass());
					copyFtp.setPort(f.getPort());
					copyFtp.setUser(f.getUser());
					copyFtp.setWorkDir(f.getWorkDir());
					return copyFtp;
				}
			}
		}
		return null;
	}

	/**
	 * 拼装KEY: <br>
	 * 规则为10为字符：线路代码(2位HEX)+车站代码(4位HEX)+文件类型(4位HEX)<br>
	 * 比如：10号线10号车站10号文件类型：0A0A0A000A
	 * 
	 * @param lineId
	 * @param stationId
	 * @param fileType
	 * @return
	 */
	public static String getFtpKey(short lineId, int stationId, int fileType) {
		String key = "";
		key += String.format("%02x", lineId);
		key += String.format("%04x", stationId);
		key += String.format("%04d", fileType);
		System.out.println(">>>>>>>>>>>>>>>>>>>>"+key);
		return key;
	}

}
