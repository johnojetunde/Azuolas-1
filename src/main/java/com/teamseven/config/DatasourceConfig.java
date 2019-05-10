package com.teamseven.config;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableJpaRepositories(basePackages = "com.teamseven",
        transactionManagerRef = "cloudTransactionManager", entityManagerFactoryRef = "cloudEntityManagerFactory")
public class DatasourceConfig {

    @Value("${database.url}")
    private String databaseUrl;

    @Value("${database.username}")
    private String databaseUsername;

    @Value("${database.password}")
    private String databasePassword;

    @Value("${database.driver}")
    private String databaseDriver;

    @Value("${database.max-active:100}")
    private String maxActive;

    @Value("${hibernate.dialect:org.hibernate.dialect.MySQLDialect}")
    private String hibernateDialect;

    @Value("${hibernate.hbm2ddl.auto:update}")
    private String hibernateHBM2DDL;

    @Value("${hibernate.show_sql:false}")
    private String hibernateShowSql;



    @Primary
    @Bean
    public SessionFactory sessionFactory() {
        HibernateEntityManagerFactory em = (HibernateEntityManagerFactory) cloudEntityManagerFactory().getObject();
        return em.unwrap(SessionFactory.class);
    }

    @Primary
    @Bean

    public LocalContainerEntityManagerFactoryBean cloudEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(appDataSource());
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaPropertyMap(additionalProperties());
        factoryBean.setPackagesToScan("com.teamseven");

        return factoryBean;
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "datasource")
    public DataSource appDataSource() {

        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();

        dataSource.setDriverClassName(databaseDriver);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setMaxActive(Integer.parseInt(maxActive.trim()));
        return dataSource;
    }

    private Map<String, String> additionalProperties() {
        Map<String, String> props = new HashMap<>();

        props.put("hibernate.dialect", hibernateDialect);
        props.put("hibernate.physical_naming_strategy", "com.teamseven.utils.PhysicalNamingStrategyImpl");
        props.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        props.put("hibernate.id.new_generator_mappings", "false");
        props.put("hibernate.hbm2ddl.auto", hibernateHBM2DDL);
        props.put("hibernate.show_sql", hibernateShowSql);


        return props;
    }


    @Primary
    @Bean
    @DependsOn(value = "cloudEntityManagerFactory")
    public PlatformTransactionManager cloudTransactionManager() {
        return new JpaTransactionManager(cloudEntityManagerFactory().getObject());
    }


}
