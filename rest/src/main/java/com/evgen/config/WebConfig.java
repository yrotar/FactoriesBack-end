package com.evgen.config;

import com.evgen.CompanyPhoneDao;
import com.evgen.ServiceImpl;
import com.evgen.dao.DaoImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.util.List;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@Configuration
@EnableWebMvc
@PropertySource("classpath:sql.properties")
@ComponentScan(basePackages = {"com.evgen.rest","com.evgen.logger"})
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class WebConfig extends WebMvcConfigurerAdapter {

    @Value("create-tables.sql")
    private String dbSchemaSqlScript;
    @Value("data-script.sql")
    private String testDataSqlScript;

    @Bean
    public DataSource getDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setType(H2).addScript(dbSchemaSqlScript).addScript(testDataSqlScript);

        return builder.build();
    }

    @Bean
    public PlatformTransactionManager txManager() {

        return new DataSourceTransactionManager(getDataSource());
    }

    @Bean
    public CompanyPhoneDao companyPhoneDao(){

        return new DaoImpl(getDataSource());
    }

    @Bean
    public ServiceImpl serviceImpl(){
        ServiceImpl serviceImpl = new ServiceImpl();
        serviceImpl.setCompanyPhoneDao(companyPhoneDao());

        return serviceImpl;
    }

    @Bean
    public MappingJackson2HttpMessageConverter jsonConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        jsonConverter.setObjectMapper(objectMapper);

        return jsonConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jsonConverter());
        super.configureMessageConverters(converters);
    }
}
