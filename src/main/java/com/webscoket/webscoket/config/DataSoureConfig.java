package com.webscoket.webscoket.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:application.properties"})
class DataSourceConfig {


    @Value("${datasource.exam.url}")
    private String examUrl;
    @Value("${datasource.exam.username}")
    private String examUserName;
    @Value("${datasource.exam.password}")
    private String examPassword;

    @Value("${datasource.maxActive}")
    private int maxActive;
    @Value("${datasource.initialSize}")
    private int initialSize;
    @Value("${datasource.maxWaitMillis}")
    private long maxWaitMillis;
    @Value("${datasource.minIdle}")
    private int minIdle;
    @Value("${datasource.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${datasource.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;
    @Value("${datasource.validationQuery}")
    private String validationQuery;
    @Value("${datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${datasource.testOnReturn}")
    private boolean testOnReturn;



    @Bean(value = "examDB")
    @Primary
    public DataSource getExamDataSource(){
        DruidDataSource druidDataSource=new DruidDataSource();
        druidDataSource.setName("examDB");
        druidDataSource.setUrl(examUrl);
        druidDataSource.setUsername(examUserName);
        druidDataSource.setPassword(examPassword);

        //druidDataSource配置
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMaxWait(maxWaitMillis);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        return druidDataSource;

    }
}
