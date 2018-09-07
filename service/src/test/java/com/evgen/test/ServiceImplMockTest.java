package com.evgen.test;

import com.evgen.config.ServiceImplMockTestConfig;
import com.evgen.dao.CompanyPhoneDao;
import com.evgen.service.CompanyPhoneService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceImplMockTestConfig.class})
public class ServiceImplMockTest {

    private static final Logger LOGGER = LogManager.getLogger(ServiceImplMockTest.class);

    @Autowired
    private CompanyPhoneService companyPhoneService;

    @Autowired
    private CompanyPhoneDao companyPhoneMockDao;

    @After
    public void clean() {
        verify();
    }

    @Before
    public void setUp() {
        reset(companyPhoneMockDao);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPhoneByIdTest() throws Exception {
        LOGGER.debug("test: getPhoneById()");

        expect(companyPhoneMockDao.getPhoneById(null)).andThrow(new IllegalArgumentException("NULL ID"));
        replay(companyPhoneMockDao);

        companyPhoneService.getPhoneById(null);
    }
}
