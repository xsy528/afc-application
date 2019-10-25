/*
 * 日期：2008-9-1
 *
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
package com.insigma.commons.communication.ftp;

import java.io.Serializable;

/**
 * FTP登陆信息参数实体
 * <p>
 * Ticket:
 *
 * @author Dingluofeng
 */
public class FtpInfo implements Serializable {

    private static final long serialVersionUID = -7409894292295522612L;

    private String user;

    private String pass;

    private String host;

    private Integer port = 21;

    private String workDir = null;

    private int fileType;

    private String ftpKey;

    private boolean isPassiveMode = false;

    public String getFtpKey() {
        return ftpKey;
    }

    public void setFtpKey(String ftpKey) {
        this.ftpKey = ftpKey;
    }

    public String getWorkDir() {
        return workDir;
    }

    public void setWorkDir(String workDir) {
        this.workDir = workDir;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    public boolean isPassiveMode() {
        return isPassiveMode;
    }

    public void setPassiveMode(boolean isPassiveMode) {
        this.isPassiveMode = isPassiveMode;
    }

    /**
     * @param host
     * @param pass
     * @param port
     * @param user
     */
    public FtpInfo(String host, int port, String user, String pass, boolean isPassiveMode) {
        super();
        this.host = host;
        this.pass = pass;
        this.port = port;
        this.user = user;
        this.isPassiveMode = isPassiveMode;
    }

    public FtpInfo(String host, int port, String user, String pass, int fileType, boolean isPassiveMode) {
        this(host, port, user, pass, isPassiveMode);
        this.fileType = fileType;
    }

    /**
     *
     */
    public FtpInfo() {
        super();
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass
     *            the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host
     *            the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * @param port
     *            the port to set
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * 判断FTP登陆参数是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        if ((host == null) || (user == null) || (pass == null) || (port == null)) {
            return true;
        }
        return false;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return String.format("主机：%s,目录：%s", host, workDir);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

}
