package org.font.ibatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = SecondaryDataSourceConfig.PACKAGE, sqlSessionTemplateRef = "secondarySqlSessionFactory")
public class SecondaryDataSourceConfig {

    static final String PACKAGE = "org.font.ibatis.w";

//    @Bean(name = "secondaryDataSource")
//    @Qualifier("secondaryDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.secondary")
//    public DataSource secondaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }

//    @Bean(name = "rdsDataSource")
//    @Primary
//    public DataSource rdsDataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl(dbUrl);
//        dataSource.setUsername(dbUser);
//        dataSource.setPassword(dbPassword);
//        return dataSource;
//    }
//
//    @Bean(name = "adsDataSource")
//    public DataSource adsDataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl(dbUrl);
//        dataSource.setUsername(dbUser);
//        dataSource.setPassword(dbPassword);
//        return dataSource;
//    }


    @Bean(name = "secondaryTransactionManager")
    public DataSourceTransactionManager adsTransactionManager(@Qualifier("secondaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "secondarySqlSessionFactory")
    public SqlSessionFactory adsSqlSessionFactory(@Qualifier("secondaryDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }
}
