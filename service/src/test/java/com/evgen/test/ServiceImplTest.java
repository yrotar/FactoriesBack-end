package com.evgen.test;

import com.evgen.Company;
import com.evgen.config.ServiceImplTestConfig;
import com.evgen.service.CompanyPhoneService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceImplTestConfig.class)
@Transactional
public class ServiceImplTest {
    private static final Logger LOGGER = LogManager.getLogger(ServiceImplTest.class);

    @Autowired
    private CompanyPhoneService companyPhoneService;

    @Test
    public void getCompaniesTest() throws Exception {
        LOGGER.debug("test: getCompanies");

        List<Company> companyList = companyPhoneService.getCompanies(null, null);
        assertEquals((Integer)companyList.size(), (Integer)5);

        companyList = companyPhoneService.getCompanies(1000, 2000);
        assertEquals((Integer)companyList.size(), (Integer)2);
    }

    @Test
    public void getCompanyByIdTest() throws Exception {
        LOGGER.debug("test: getCompanyById");

        Company company = companyPhoneService.getCompanyById(1);
        assertEquals(company.getName(), "Apple");
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getCompanyByNonexistentIdTest() throws Exception {
        LOGGER.debug("test: getCompanyByNonexistentId");

        companyPhoneService.getCompanyById(30);
    }

    @Test
    public void getCompanyByNameTest() throws Exception {
        LOGGER.debug("test: getCompanyByName");

        Company company = companyPhoneService.getCompanyByName("Apple");
        assertEquals(company.getName(), "Apple");
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getCompanyByNonexistentNameTest() throws Exception {
        LOGGER.debug("test: getCompanyByNonexistentName");

        Company company = companyPhoneService.getCompanyByName("Siaomi");
        assertNull(company);
    }


}
