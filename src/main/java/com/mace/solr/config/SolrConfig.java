package com.mace.solr.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

/**
 * @Description: solr配置
 *
 * Created by mace on 18:14 2018/4/22.
 */
@Configuration
@Slf4j
public class SolrConfig {

    @Value("${spring.data.solr.username}")
    private String username;
    @Value("${spring.data.solr.host}")
    private String host;
    @Value("${spring.data.solr.password}")
    private String password;
    @Value("${spring.data.solr.maxConnections}")
    private int maxConnections;
    @Value("${spring.data.solr.maxConnectionsPerHost}")
    private int maxConnectionsPerHost;
    @Value("${spring.data.solr.followRedirects}")
    private boolean followRedirects;

//    private static String username;
//    private static String password;
//    private static String host;
//    private static int maxConnections;
//    private static int maxConnectionsPerHost;
//    private static boolean followRedirects;
//
//    static {
//
//        host = SolrProperties.host;
//        username = SolrProperties.username;
//        password = SolrProperties.password;
//        maxConnections = SolrProperties.maxConnections;
//        maxConnectionsPerHost = SolrProperties.maxConnectionsPerHost;
//        followRedirects = SolrProperties.followRedirects;
//    }



    @Bean
    @Scope(value = "singleton")
    public SolrTemplate solrTemplate(){


        log.info("max-con: {}",maxConnections);
        log.info("maxConnectionsPerHost: {}",maxConnectionsPerHost);
        log.info("followRedirects: {}",followRedirects);
        log.info("username: {}",username);
        log.info("password: {}",password);
        log.info("host: {}",host);

        ModifiableSolrParams params = new ModifiableSolrParams();
        params.set(HttpClientUtil.PROP_MAX_CONNECTIONS, maxConnections);
        params.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, maxConnectionsPerHost);
        params.set(HttpClientUtil.PROP_FOLLOW_REDIRECTS, followRedirects);
//        solr安全认证
        params.set(HttpClientUtil.PROP_BASIC_AUTH_USER, username);
        params.set(HttpClientUtil.PROP_BASIC_AUTH_PASS, password);

        HttpSolrClient solrClient = new HttpSolrClient(host, HttpClientUtil.createClient(params));

        log.info("装配SolrTemplate成功");

        return new SolrTemplate(solrClient);
    }
}
