package com.evgen.test;

import com.evgen.Company;
import com.evgen.Phone;
import com.evgen.config.ServiceImplTestConfig;
import com.evgen.exception.OperationFailedException;
import com.evgen.service.CompanyPhoneService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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

        List<Company> companies = companyPhoneService.getCompanies(null, null);
        assertEquals((Integer)companies.size(), (Integer)5);

        companies = companyPhoneService.getCompanies(1000, 2000);
        assertEquals((Integer)companies.size(), (Integer)2);
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

    @Test
    public void addCompanyTest() throws Exception {
        LOGGER.debug("test: addCompany");

        Company company = new Company("IBM", 100);
        Integer id = companyPhoneService.addCompany(company);

        company = companyPhoneService.getCompanyById(id);
        assertEquals(company.getName(), "IBM");
    }

    @Test(expected = DuplicateKeyException.class)
    public void addBadCompanyTest() throws Exception {
        LOGGER.debug("test: addBadCompany");

        Company company = new Company("Apple", 2000);
        companyPhoneService.addCompany(company);
    }

    @Test
    public void updateCompanyTest() throws Exception {
        LOGGER.debug("test: updateCompany");

        Company company = new Company(1, "Apple(Updated)", 3000);
        companyPhoneService.updateCompany(company);

        company = companyPhoneService.getCompanyById(1);
        assertEquals(company.getName(), "Apple(Updated)");
    }

    @Test(expected = OperationFailedException.class)
    public void updateNonexistentCompanyTest() throws Exception {
        LOGGER.debug("test: updateNonexistentCompany");

        Company company = new Company(30, "IBM", 30000);
        companyPhoneService.updateCompany(company);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void deleteCompanyTest() throws Exception {
        LOGGER.debug("test: deleteCompany");

        companyPhoneService.deleteCompany(1);

        Integer allPhonesCount = companyPhoneService.getPhones(null,null).size();
        assertEquals(allPhonesCount, (Integer)5);

        companyPhoneService.getCompanyById(1);
    }

    @Test(expected = OperationFailedException.class)
    public void deleteNonexistentCompanyTest() throws Exception {
        LOGGER.debug("test: deleteNonexistentCompany");

        companyPhoneService.deleteCompany(30);
    }

    @Test
    public void getPhonesTest() throws Exception {
        LOGGER.debug("test: getPhones");

        List<Phone> phones = companyPhoneService.getPhones(null, null);
        assertEquals((Integer)phones.size(), (Integer)7);

        phones = companyPhoneService.getPhones(100, 200);
        assertEquals((Integer)phones.size(), (Integer)3);
    }

    @Test
    public void getPhoneByIdTest() throws Exception {
        LOGGER.debug("test: getPhoneById");

        Phone phone = companyPhoneService.getPhoneById(1);
        assertEquals(phone.getName(), "Iphone 5");
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getPhoneByNonexistentIdTest() throws Exception {
        LOGGER.debug("test: getPhoneByNonexistentId");

        companyPhoneService.getPhoneById(50);
    }

    @Test
    public void addPhoneTest() throws Exception {
        LOGGER.debug("test: addPhone");

        Phone phone = new Phone("Iphone XS", 1000, new Company(1,"Apple"));
        Integer phoneId = companyPhoneService.addPhone(phone);

        phone = companyPhoneService.getPhoneById(phoneId);
        assertEquals(phone.getName(), "Iphone XS");
    }

    @Test(expected = IllegalStateException.class)
    public void addBadPhoneTest() throws Exception {
        LOGGER.debug("test: addBadPhone");

        Phone phone = new Phone( "Iphone XS", -1000, new Company(1,"Apple"));
        companyPhoneService.addPhone(phone);
    }

    @Test
    public void updatePhoneTest() throws Exception {
        LOGGER.debug("test: updatePhone");

        Phone phone = new Phone(1, "Iphone(Updated)", 500, new Company(1,"Apple"));
        companyPhoneService.updatePhone(phone);

        phone = companyPhoneService.getPhoneById(1);
        assertEquals(phone.getName(), "Iphone(Updated)");

        Integer phonesCount = companyPhoneService.getPhones(null,null).size();
        assertEquals(phonesCount, (Integer)7);
    }

    @Test(expected = OperationFailedException.class)
    public void updateNonexistentPhoneTest() throws Exception {
        LOGGER.debug("test: updateNonexistentPhone");

        Phone phone = new Phone(50, "Iphone(Updated)", 400, new Company(1,"Apple"));
        companyPhoneService.updatePhone(phone);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void deletePhoneTest() throws Exception {
        LOGGER.debug("test: deletePhone");

        companyPhoneService.deletePhone(1);
        companyPhoneService.getPhoneById(1);
    }

    @Test(expected = OperationFailedException.class)
    public void deleteNonexistentPhoneTest() throws Exception {
        LOGGER.debug("test: deleteNonexistentPhone");

        companyPhoneService.deletePhone(50);
    }
}