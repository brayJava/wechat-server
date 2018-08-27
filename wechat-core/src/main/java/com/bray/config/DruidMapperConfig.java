package com.bray.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 14:33 2018/7/21
 * @Modified By:
 */
@Configuration
@MapperScan(basePackages = "com.bray.mapper"
        ,sqlSessionTemplateRef = "sqlSessionTemplate")
public class DruidMapperConfig {

    @Bean(name = "druidDatasource")
    @ConfigurationProperties(prefix = "datasource")
    @Primary
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        try {
            druidDataSource.setFilters("stat,wall,log4j");
            druidDataSource.setUseGlobalDataSourceStat(true);
        } catch (SQLException e) {
            //
        }
        return druidDataSource;
    }
    /**
     * SqlSessionFactory配置
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "wpSqlSessionFactory")
    @Primary
    public SqlSessionFactory wpSqlSessionFactory(@Qualifier("druidDatasource") DataSource dataSource)
        throws Exception{

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置事物管理器
     * @param dataSource
     * @return
     */
    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("druidDatasource") DataSource dataSource) {

        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
    @Bean(name = "sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("wpSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
