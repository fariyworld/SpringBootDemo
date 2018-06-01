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
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * description: cluster 数据源
 * <br />
 * Created by mace on 9:52 2018/5/2.
 */
@Configuration
@MapperScan(basePackages = "com.mace.mapper_oracle", sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterDruidDataSourceConfig {

    @Value("${spring.datasource.cluster.clusterMapperLocations}")
    private String clusterMapperLocation;

    /**
     * description: cluster datasource
     * <br /><br />       
     * create by mace on 2018/5/2 14:47.
     * @param   
     * @return: javax.sql.DataSource
     */
    @ConfigurationProperties(prefix = "spring.datasource.cluster")
    @Bean(name = "clusterDataSource")
    public DataSource masterDataSource() {

        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        druidDataSource.setName("cluster-datasource");
        return druidDataSource;

    }

    /**
     * description: cluster SqlSessionFactory
     * <br /><br />       
     * create by mace on 2018/5/2 14:46.
     * @param dataSource  
     * @return: org.apache.ibatis.session.SqlSessionFactory
     */
    @Bean(name = "clusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 配置mapper文件位置
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(clusterMapperLocation));

        return sqlSessionFactoryBean.getObject();
    }

}
