package com.webscoket.webscoket.config;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class SQLManagerConfig {

    @Bean(name = "examSqlManager")
    @Primary
    public SqlManagerFactoryBean getSqlManagerFactoryBean(@Qualifier("examDB") DataSource master){
        SqlManagerFactoryBean factoryBean=new SqlManagerFactoryBean();
        BeetlSqlDataSource source=new BeetlSqlDataSource();
        source.setMasterSource(master);
        factoryBean.setCs(source);
        factoryBean.setDbStyle(new MySqlStyle());
        //开启驼峰
        factoryBean.setNc(new UnderlinedNameConversion());
        //sql文件路径
        factoryBean.setSqlLoader(new ClasspathLoader("/sql"));
        return factoryBean;
    }

    /**
     * 配置包扫描
     * @return
     */
    @Bean(name = "examSqlScannerConfigurer")
    public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer() {
        BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
        conf.setBasePackage("com.webscoket.webscoket.dao");
        conf.setDaoSuffix("Dao");
        conf.setSqlManagerFactoryBeanName("examSqlManager");
        return conf;
    }
}