package com.evgen.test;

import com.evgen.Company;
import com.evgen.Phone;
import com.evgen.config.ServiceImplMockTestConfig;
import com.evgen.dao.CompanyPhoneDao;
import com.evgen.exception.OperationFailedException;
import com.evgen.service.CompanyPhoneService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceImplMockTestConfig.class)
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
        LOGGER.debug("test: getPhoneById");

        expect(companyPhoneMockDao.getPhoneById(null)).andThrow(new IllegalArgumentException("NULL ID"));
        replay(companyPhoneMockDao);

        companyPhoneService.getPhoneById(null);
    }

    @Test
    public void addPhoneTest() throws Exception {
        LOGGER.debug("test: addPhone");

        Phone phone = new Phone("Iphone SE2", 500, new Company(1, "Apple"));

        expect(companyPhoneMockDao.addPhone(phone)).andReturn(8);
        expect(companyPhoneMockDao.getCompanyById(phone.getCompany().getCompanyId())).andReturn(new Company());
        replay(companyPhoneMockDao);

        Integer id = companyPhoneService.addPhone(phone);

        assertEquals(id, (Integer)8);
    }

    @Test(expected = OperationFailedException.class)
    public void updateNonexistentPhoneTest() throws Exception {
        LOGGER.debug("test: updateNonexistentPhone");

        Phone phone = new Phone(50, "Iphone(Updated)", 500, new Company(1,"Apple"));

        expect(companyPhoneMockDao.updatePhone(phone)).andThrow(new OperationFailedException("Operation Failed"));
        replay(companyPhoneMockDao);

        companyPhoneService.updatePhone(phone);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void deleteNonexistentPhoneTest() throws Exception {
        LOGGER.debug("test: deleteNonexistentPhone");

        expect(companyPhoneMockDao.deletePhone(50)).andThrow(new EmptyResultDataAccessException(1));
        replay(companyPhoneMockDao);

        companyPhoneService.deletePhone(50);
    }

    @Test(expected = DuplicateKeyException.class)
    public void addExistCompanyTest() throws Exception {
        LOGGER.debug("test: addExistCompany");

        Company company = new Company("Apple", 1000);

        expect(companyPhoneMockDao.addCompany(company)).andThrow(new DuplicateKeyException("Operation Failed"));
        replay(companyPhoneMockDao);

        companyPhoneService.addCompany(company);
    }

    @Test(expected = OperationFailedException.class)
    public void updateNonexistentCompanyTest() throws Exception {
        LOGGER.debug("test: updateNonexistentCompany");

        Company company = new Company(50,"Apple(update)", 1000);

        expect(companyPhoneMockDao.updateCompany(company)).andThrow(new OperationFailedException("Operation Failed"));
        replay(companyPhoneMockDao);

        companyPhoneService.updateCompany(company);

    }

    @Test(expected = OperationFailedException.class)
    public void deleteNonexistentCompanyTest() throws Exception {
        LOGGER.debug("test: deleteNonexistentCompany");

        expect(companyPhoneMockDao.deleteCompany(50)).andThrow(new OperationFailedException("Operation Failed"));
        replay(companyPhoneMockDao);

        companyPhoneService.deleteCompany(50);
    }
}
