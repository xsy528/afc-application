package com.insigma.afc.config;

import com.insigma.afc.ftp.FTPThread;
import com.insigma.afc.ftp.properties.HealthProperties;
import com.insigma.afc.ftp.properties.RmiProperties;
import com.insigma.afc.workbench.rmi.IBaseCommandService;
import com.insigma.afc.workbench.rmi.ICommandService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * Ticket: rmi配置
 *
 * @author xingshaoya
 * 2019-10-23 10:49
 */
@Configuration
@EnableConfigurationProperties({RmiProperties.class,HealthProperties.class})
public class RmiAutoConfig {

    private RmiProperties rmiProperties;
    private HealthProperties healthProperties;

    public RmiAutoConfig(RmiProperties rmiProperties,HealthProperties healthProperties) {
        this.rmiProperties = rmiProperties;
        this.healthProperties = healthProperties;
    }

//    @Bean
////    @ConditionalOnMissingBean(ICommandService.class)
////    public RmiProxyFactoryBean rmiCommandService() {
////        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
////        bean.setServiceInterface(ICommandService.class);
////        bean.setServiceUrl("rmi://" + rmiProperties.getRmiHostIpAddr() + ":" + rmiProperties.getCommandServiceRmiPort()
////                + "/CommandService");
////        bean.setLookupStubOnStartup(false);
////        bean.setRefreshStubOnConnectFailure(true);
////        return bean;
////    }

    @Bean
    @ConditionalOnMissingBean(IBaseCommandService.class)
    public RmiProxyFactoryBean baseCommandService() {
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(IBaseCommandService.class);
        bean.setServiceUrl("rmi://" + healthProperties.getIp() + ":"
                + healthProperties.getPort() + "/CommunicationRegisterService");
        bean.setLookupStubOnStartup(false);
        bean.setRefreshStubOnConnectFailure(true);
        return bean;
    }

    @Bean(initMethod = "init",destroyMethod = "destroy")
    public FTPThread registerHealthIndicator(IBaseCommandService baseCommandService,
                                             HealthProperties healthProperties){
        return new FTPThread(baseCommandService,healthProperties);
    }
}
