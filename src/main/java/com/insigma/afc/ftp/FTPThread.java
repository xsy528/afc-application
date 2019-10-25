package com.insigma.afc.ftp;

import com.insigma.afc.ftp.properties.HealthProperties;
import com.insigma.afc.workbench.rmi.IBaseCommandService;
import com.insigma.afc.workbench.rmi.RegisterResult;
import com.insigma.commons.communication.ftp.FtpInfo;
import com.insigma.commons.spring.datasource.DESUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FTPThread extends AbstractHealthIndicator {
    private static final Logger LOGGER = LoggerFactory.getLogger(FTPThread.class);

    private FTPInfoCacheUtil ftpUtil = FTPInfoCacheUtil.getInstance();

    private IBaseCommandService cmdService;
    private boolean isOnline;
    private Throwable throwable;
    private HealthProperties properties;
    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,new ThreadFactory());
  //  private CDRmiProxyFactory<IBaseCommandService> rmiProxyFactory;


    private class ThreadFactory implements java.util.concurrent.ThreadFactory{

        @Override
        public Thread newThread(Runnable r) {
            Thread singleThread = new Thread(r);
            singleThread.setName("通讯前置机连接检测线程");
            return singleThread;
        }
    }

    public FTPThread(IBaseCommandService cmdService, HealthProperties properties) {
        this.cmdService = cmdService;
        this.properties = properties;
    }
    /**
     * 初始化方法
     */
    @PostConstruct
    public void init() {
//        String serviceUrl = "rmi://" + properties.getIp() + ":" + properties.getPort() + "/CommunicationRegisterService" ;
//        LOGGER.info("rmi地址为: " + serviceUrl);
//        //设置rmi代理对象属性
//        RmiProxyFactoryBean rmiBean = new RmiProxyFactoryBean();
//        rmiBean.setServiceUrl(serviceUrl);
//        rmiBean.setServiceInterface(IBaseCommandService.class);
//        rmiBean.setLookupStubOnStartup(false);
//        rmiBean.setRefreshStubOnConnectFailure(true);
//        //获取远程对象
//        rmiBean.afterPropertiesSet();
//        cmdService = (IBaseCommandService) rmiBean.getObject();

        this.executorService.scheduleWithFixedDelay(new Task(),properties.getInitialDelay(),properties.getFixedDelay()
                , TimeUnit.SECONDS);
    }

    @Override
    protected void doHealthCheck(Health.Builder builder){
        if (isOnline) {
            builder.up();
        } else if (this.throwable != null) {
            builder.down(throwable);
        } else {
            builder.down();
        }
    }

    @PreDestroy
    public void destroy() {
        if (executorService != null) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(2, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e1) {
                executorService.shutdownNow();
            } finally {
                executorService = null;
            }
        }
    }

    /**
     * 获取FTP信息
     * @return
     */
    private class Task implements Runnable {

        @Override
        public void run() {
            throwable = null;
            if(properties.isStart()){
//            if (isOnline) {
//                try {
//                    cmdService.isAlive();
//                    isOnline = true;
//                } catch (Exception e) {
//                    throwable = e;
//                    isOnline = false;
//                    LOGGER.error("检测到服务器离线。", e);
//                }
//            } else {
                try {

                    try {
                        cmdService.isAlive();

                    } catch (Exception e) {
                        throwable = e;
                        LOGGER.error("检测到服务器离线。", e);
                    }
                    RegisterResult registerResult = cmdService.register();
                    if (registerResult != null && registerResult.getResult() == 0) {
                        List<FtpInfo> importFtpList = registerResult.getImportFTPList();
                        for (FtpInfo ftpInfo : importFtpList) {
                            if (!ftpInfo.isEmpty() && !"".equals(ftpInfo.getPass()) && ftpInfo.getPass() != null) {
                                String pass = DESUtil.decrypt(ftpInfo.getPass());
                                ftpInfo.setPass(pass);
                            }
                        }
                        List<FtpInfo> exportFtpList = registerResult.getExportFTPList();
                        for (FtpInfo ftpInfo : exportFtpList) {
                            if (!ftpInfo.isEmpty() && !"".equals(ftpInfo.getPass()) && ftpInfo.getPass() != null) {
                                String pass = DESUtil.decrypt(ftpInfo.getPass());
                                ftpInfo.setPass(pass);
                            }
                        }

                        ftpUtil.setInfoManager(new FTPInfoManager(importFtpList,exportFtpList));

                        LOGGER.info("获取服务器ftp信息");
                        if (registerResult.getExportFTPList() != null) {
                           // LOGGER.info("-----------------导出FTP信息------------------");
                           // logFtpInfo(registerResult.getExportFTPList());
                        }
                        if (registerResult.getImportFTPList() != null) {
                            LOGGER.info("-----------------导入FTP信息------------------");
                            logFtpInfo(registerResult.getImportFTPList());
                        }
                        isOnline = true;
                        LOGGER.info("注册服务端成功。");

                    }
                } catch (Exception e) {
                    LOGGER.error("无法注册到到服务器。", e);
                    // 修改状态
                    throwable = e;
                    isOnline = false;
  //              }
            }}
        }


    }
    private void logFtpInfo(List<FtpInfo> infos) {
        for (FtpInfo info : infos) {
            LOGGER.info("FTP的KEY类型=" + info.getFtpKey());
            LOGGER.info("FTP目录类别=" + info.getFileType());
            LOGGER.info("FTP服务器IP=" + info.getHost());
            LOGGER.info("FTP服务器访问用户名=" + info.getUser());
            LOGGER.info("FTP服务器访问密码=" + info.getPass());
            LOGGER.info("FTP目录工作目录=" + info.getWorkDir());
            LOGGER.info("FTP是否为被动模式=" + info.isPassiveMode());
            LOGGER.info("-----------------------------------");
        }
    }
}
