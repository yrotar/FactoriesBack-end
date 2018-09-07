package com.evgen.config;

import com.evgen.dao.CompanyPhoneDao;
import com.evgen.dao.CompanyPhoneDaoImpl;
import com.evgen.service.CompanyPhoneService;
import com.evgen.service.CompanyPhoneServiceImpl;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:sql.properties")
@EnableTransactionManagement
public class ServiceImplTestConfig {

    @Bean
    public DataSource getDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("create-tables.sql")
                .addScript("data-script.sql")
                .build();

        return db;
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(getDataSource());
    }

    @Bean
    public CompanyPhoneDao getCompanyPhoneDao(){
        return new CompanyPhoneDaoImpl(getDataSource());
    }

    @Bean
    public CompanyPhoneService getCompanyPhoneService(){
        return new CompanyPhoneServiceImpl(getCompanyPhoneDao());
    }
}
