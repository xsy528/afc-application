/*
 * 日期：2018年12月10日
 *
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
/*

 */
package com.insigma.afc.ftp.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Ticket:
 *
 * @author xuzhemin
 * 2019/5/6 11:08
 */
@Component
@ConfigurationProperties("health")
public class HealthProperties {

    /**
     * 定时检测任务执行延时
     */
    private Long initialDelay = 60L;
    /**
     * 检测任务执行延迟间隔
     */
    private Long fixedDelay = 10L;

    private String ip = "127.0.0.1";

    private String port = "8527";

    private boolean isStart = true;

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Long getInitialDelay() {
        return initialDelay;
    }

    public void setInitialDelay(Long initialDelay) {
        this.initialDelay = initialDelay;
    }

    public Long getFixedDelay() {
        return fixedDelay;
    }

    public void setFixedDelay(Long fixedDelay) {
        this.fixedDelay = fixedDelay;
    }
}
