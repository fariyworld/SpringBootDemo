package com.mace.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * description: master 数据源
 * <br />
 * Created by mace on 9:52 2018/5/2.
 */
@Configuration
@MapperScan(basePackages = "com.mace.mapper_mysql", sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDruidDataSourceConfig {

    @Value("${spring.datasource.master.masterMapperLocations}")
    private String masterMapperLocation;

    /**
     * description: master datasource
     * <br /><br />       
     * create by mace on 2018/5/2 14:47.
     * @param   
     * @return: javax.sql.DataSource
     */
    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Bean(name = "masterDataSource")
    @Primary
    public DataSource masterDataSource() {

        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        druidDataSource.setName("master-datasource");
        return druidDataSource;

    }

    /**
     * description: master SqlSessionFactory
     * <br /><br />       
     * create by mace on 2018/5/2 14:46.
     * @param dataSource  
     * @return: org.apache.ibatis.session.SqlSessionFactory
     */
    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 配置mapper文件位置
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(masterMapperLocation));

        return sqlSessionFactoryBean.getObject();
    }

}
