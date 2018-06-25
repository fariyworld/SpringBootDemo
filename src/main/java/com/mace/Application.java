package com.mace;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * description: SpringBoot项目启动主类
 * <br />
 * Created by mace on 19:36 2018/5/27.
 */
@SpringBootApplication(exclude = {SolrAutoConfiguration.class})//不自动装配solr
@EnableScheduling//启动定时任务
@EnableKafka//启用对Kafka的支持
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }


    /**
     * description:SpringBoot实现FastJson解析json数据 统一设置时间戳转换
     *
     * 格式化日期格式
     * @JSONField(format = "yyyy-MM-dd HH:mm:ss")
     *
     * 不进行序列化
     * @JSONField(serialize = false)
     *
     * SerializerFeature属性
     *
     * <br /><br />
     * create by mace on 2018/5/27 19:41.
     * @param
     * @return: org.springframework.boot.autoconfigure.http.HttpMessageConverters
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        fastConverter.setFeatures(
                SerializerFeature.PrettyFormat,
                //统一转换时间戳: yyyy-MM-dd HH:mm:ss
                SerializerFeature.WriteDateUseDateFormat
                //输出为null的
                //SerializerFeature.WriteMapNullValue
        );

        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        mediaTypes.add(new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8")));
        mediaTypes.add(new MediaType(MediaType.TEXT_HTML, Charset.forName("UTF-8")));
        fastConverter.setSupportedMediaTypes(mediaTypes);

        HttpMessageConverter<?> converter = fastConverter;

        return new HttpMessageConverters(converter);
    }
}
