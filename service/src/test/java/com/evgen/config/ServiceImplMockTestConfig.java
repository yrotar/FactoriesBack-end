package com.evgen.config;

import com.evgen.dao.CompanyPhoneDao;
import com.evgen.dao.CompanyPhoneDaoImpl;
import com.evgen.service.CompanyPhoneService;
import com.evgen.service.CompanyPhoneServiceImpl;
import org.easymock.EasyMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceImplMockTestConfig {

    @Bean
    public CompanyPhoneDao companyPhoneDao() {
        return EasyMock.createMock(CompanyPhoneDaoImpl.class);
    }

    @Bean
    public CompanyPhoneService companyPhoneService(){
        return new CompanyPhoneServiceImpl(companyPhoneDao());
    }
}
