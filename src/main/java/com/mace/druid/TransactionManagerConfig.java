package com.mace.druid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * description: xml事务配置
 * <br />
 * Created by mace on 15:37 2018/4/28.
 */
@Configuration
@ImportResource("classpath:spring-tr.xml")
public class TransactionManagerConfig {

    @Bean("masterTransactionManager")
    @Primary
    public PlatformTransactionManager masterTxManager(@Qualifier("masterDataSource") DataSource dataSource){

        DataSourceTransactionManager masterTxManager = new DataSourceTransactionManager();

        masterTxManager.setDataSource(dataSource);

        return masterTxManager;
    }

    @Bean("clusterTransactionManager")
    public PlatformTransactionManager clusterTxManager(@Qualifier("clusterDataSource") DataSource dataSource){

        DataSourceTransactionManager clusterTxManager = new DataSourceTransactionManager();

        clusterTxManager.setDataSource(dataSource);

        return clusterTxManager;
    }

}
