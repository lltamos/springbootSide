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
@MapperScan(basePackages = PrimaryDataSourceConfig.PACKAGE, sqlSessionTemplateRef = "primarySqlSessionFactory")
public class PrimaryDataSourceConfig {

    static final String PACKAGE = "com.aliyun.xxx.repository.mybatis.rds";
//    @Value("${xxx_mysql_url}")
//    private String dbUrl;
//    @Value("${xxx_mysql_username}")
//    private String dbUser;
//    @Value("${xxx_mysql_password}")
//    private String dbPassword;


//    @Bean(name = "primaryDataSource")
//    @Qualifier("primaryDataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.primary")
//    public DataSource primaryDataSource() {
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

    @Bean(name = "primaryTransactionManager")
    @Primary
    public DataSourceTransactionManager rdsTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory rdsSqlSessionFactory(@Qualifier("primaryDataSource") DataSource rdsDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(rdsDataSource);
        return sessionFactory.getObject();
    }

}
