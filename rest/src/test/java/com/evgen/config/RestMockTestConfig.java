package com.evgen.config;

import com.evgen.service.CompanyPhoneService;
import com.evgen.service.CompanyPhoneServiceImpl;
import org.easymock.EasyMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.evgen.rest")
public class RestMockTestConfig {

    @Bean
    public CompanyPhoneService companyPhoneService() {
        return EasyMock.createMock(CompanyPhoneService.class);
    }

}
