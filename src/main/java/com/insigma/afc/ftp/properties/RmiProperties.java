/*
 * 日期：2018年12月10日
 *
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
/**
 *
 */
package com.insigma.afc.ftp.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Ticket:
 *
 * @author xuzhemin
 * 2019/3/21 14:53
 */
@Component
@ConfigurationProperties(prefix = "rmi")
public class RmiProperties {
    /**
     * 与MessageServer通讯RMI配置
     */
    private final String DEFAULT_HOST = "localhost";
    private String rmiHostIpAddr = DEFAULT_HOST;
    private Integer communicationRegistRmiPort = 8527;
    private Integer commandServiceRmiPort = 8528;
    private Integer parameterManagerRmiPort = 8532;
    private Integer qrKeyParamManagerRmiPort = 8536;

    /**
     * 与TaskServer通讯RMI配置
     */
    private String rmiTaskHostIpAddr = DEFAULT_HOST;
    private Integer dailyClearRmiPort = 8533;

    /**
     * 与YKTServer通讯RMI配置
     */
    private String rmiYKTHostIpAddr = DEFAULT_HOST;
    private Integer fileOperationRmiPort = 8534;

    /**
     * 和SS通讯RMI配置
     */
    private String rmiSecertIpAddr = DEFAULT_HOST;
    private Integer QRKeyRmiPort = 8535;

    public String getRmiHostIpAddr() {
        return rmiHostIpAddr;
    }

    public void setRmiHostIpAddr(String rmiHostIpAddr) {
        this.rmiHostIpAddr = rmiHostIpAddr;
    }

    public Integer getCommunicationRegistRmiPort() {
        return communicationRegistRmiPort;
    }

    public void setCommunicationRegistRmiPort(Integer communicationRegistRmiPort) {
        this.communicationRegistRmiPort = communicationRegistRmiPort;
    }

    public Integer getCommandServiceRmiPort() {
        return commandServiceRmiPort;
    }

    public void setCommandServiceRmiPort(Integer commandServiceRmiPort) {
        this.commandServiceRmiPort = commandServiceRmiPort;
    }

    public Integer getParameterManagerRmiPort() {
        return parameterManagerRmiPort;
    }

    public void setParameterManagerRmiPort(Integer parameterManagerRmiPort) {
        this.parameterManagerRmiPort = parameterManagerRmiPort;
    }

    public Integer getQrKeyParamManagerRmiPort() {
        return qrKeyParamManagerRmiPort;
    }

    public void setQrKeyParamManagerRmiPort(Integer qrKeyParamManagerRmiPort) {
        this.qrKeyParamManagerRmiPort = qrKeyParamManagerRmiPort;
    }

    public String getRmiTaskHostIpAddr() {
        return rmiTaskHostIpAddr;
    }

    public void setRmiTaskHostIpAddr(String rmiTaskHostIpAddr) {
        this.rmiTaskHostIpAddr = rmiTaskHostIpAddr;
    }

    public Integer getDailyClearRmiPort() {
        return dailyClearRmiPort;
    }

    public void setDailyClearRmiPort(Integer dailyClearRmiPort) {
        this.dailyClearRmiPort = dailyClearRmiPort;
    }

    public String getRmiYKTHostIpAddr() {
        return rmiYKTHostIpAddr;
    }

    public void setRmiYKTHostIpAddr(String rmiYKTHostIpAddr) {
        this.rmiYKTHostIpAddr = rmiYKTHostIpAddr;
    }

    public Integer getFileOperationRmiPort() {
        return fileOperationRmiPort;
    }

    public void setFileOperationRmiPort(Integer fileOperationRmiPort) {
        this.fileOperationRmiPort = fileOperationRmiPort;
    }

    public String getRmiSecertIpAddr() {
        return rmiSecertIpAddr;
    }

    public void setRmiSecertIpAddr(String rmiSecertIpAddr) {
        this.rmiSecertIpAddr = rmiSecertIpAddr;
    }

    public Integer getQRKeyRmiPort() {
        return QRKeyRmiPort;
    }

    public void setQRKeyRmiPort(Integer QRKeyRmiPort) {
        this.QRKeyRmiPort = QRKeyRmiPort;
    }
}
