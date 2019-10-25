/* 
 * 日期：2010-9-17
 *  
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
package com.insigma.afc.workbench.rmi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.insigma.commons.communication.ftp.FtpInfo;

/**
 * 服务器给工作台返回的注册结果信息
 * 
 * @author 廖红自
 */
public class RegisterResult implements Serializable {

	private static final long serialVersionUID = 2368441398065978888L;

	private short result;

    private List<FtpInfo> importFTPList = new ArrayList<FtpInfo>();

    private List<FtpInfo> exportFTPList = new ArrayList<FtpInfo>();

    public RegisterResult() {

    }

    public short getResult() {
        return result;
    }

    public void setResult(short result) {
        this.result = result;
    }

    public List<FtpInfo> getImportFTPList() {
        return importFTPList;
    }

    public void setImportFTPList(List<FtpInfo> importFTPList) {
        this.importFTPList = importFTPList;
    }

    public List<FtpInfo> getExportFTPList() {
        return exportFTPList;
    }

    public void setExportFTPList(List<FtpInfo> exportFTPList) {
        this.exportFTPList = exportFTPList;
    }
}
