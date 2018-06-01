package com.mace.solr.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.params.ModifiableSolrParams;
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


    @Bean
    @Scope(value = "singleton")
    public SolrTemplate solrTemplate(){

        ModifiableSolrParams params = new ModifiableSolrParams();
        params.set(HttpClientUtil.PROP_MAX_CONNECTIONS, SolrProperties.maxConnections);
        params.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, SolrProperties.maxConnectionsPerHost);
        params.set(HttpClientUtil.PROP_FOLLOW_REDIRECTS, SolrProperties.followRedirects);
//        solr安全认证
        params.set(HttpClientUtil.PROP_BASIC_AUTH_USER, SolrProperties.username);
        params.set(HttpClientUtil.PROP_BASIC_AUTH_PASS, SolrProperties.password);

        HttpSolrClient solrClient = new HttpSolrClient(SolrProperties.host, HttpClientUtil.createClient(params));

        log.info("装配SolrTemplate成功");

        return new SolrTemplate(solrClient);
    }
}
