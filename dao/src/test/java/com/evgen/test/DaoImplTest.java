package com.evgen.test;

import com.evgen.Company;
import com.evgen.Phone;
import com.evgen.config.DaoImplTestConfig;
import com.evgen.dao.CompanyPhoneDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoImplTestConfig.class)
@Transactional
public class DaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(DaoImplTest.class);

    @Autowired
    CompanyPhoneDao companyPhoneDao;

    @Test
    public void getCompaniesTest() throws Exception {
        LOGGER.debug("test: getCompanies");

        List<Company> companies = companyPhoneDao.getCompanies(null, null);
        Assert.assertTrue(companies.size() == 5);

        companies = companyPhoneDao.getCompanies(1000, 2000);
        Assert.assertTrue(companies.size() == 2);
    }

    @Test
    public void addCompanyTest() throws Exception {
        LOGGER.debug("test: addCompany");

        Company company = new Company("IBM", 20000);
        Integer companyId = companyPhoneDao.addCompany(company);

        company = companyPhoneDao.getCompanyById(companyId);
        assertEquals(company.getName(), "IBM");
    }

    @Test(expected = DuplicateKeyException.class)
    public void addExistCompanyTest() throws Exception {
        LOGGER.debug("test: addExistCompany");

        Company company = new Company("Apple", 10000);
        companyPhoneDao.addCompany(company);
    }

    @Test
    public void getCompanyByNameTest() throws Exception {
        LOGGER.debug("test: getCompanyByName");

        Company company = companyPhoneDao.getCompanyByName("Apple");
        Assert.assertEquals(company.getCompanyId(),((Integer)1));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getNonexistentCompanyByName() throws Exception {
        LOGGER.debug("test: getNonexistentCompanyByName");

        companyPhoneDao.getCompanyByName("Siaomi");
    }

    @Test
    public void updateCompanyTest() throws Exception {
        LOGGER.debug("test: updateCompany");

        Company company = companyPhoneDao.getCompanyById(1);
        company.setName("Apple(update)");
        company.setEmployees(300);

        companyPhoneDao.updateCompany(company);
        company = companyPhoneDao.getCompanyById(1);
        Assert.assertEquals(company.getName(),"Apple(update)");
        Assert.assertTrue(company.getEmployees() == 300);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void updateNonexistentCompanyTest() throws Exception {
        LOGGER.debug("test: updateNonexistentCompany");

        Company company = new Company(10,"Apple", 1000);

        Integer updatedCount = companyPhoneDao.updateCompany(company);

        assertEquals(updatedCount, (Integer)0);

        companyPhoneDao.getCompanyById(10);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void deleteCompanyTest() throws Exception {
        LOGGER.debug("test: deleteCompany");

        companyPhoneDao.deleteCompany(1);
        Integer allPhonesCount = companyPhoneDao.getPhones(null,null).size();
        assertEquals(allPhonesCount, (Integer)5);

        Company company = companyPhoneDao.getCompanyById(1);
    }

    @Test
    public void deleteNonexistentCompanyTest() throws Exception {
        LOGGER.debug("test: deleteNonexistentCompany");

        Integer deletedCount = companyPhoneDao.deleteCompany(10);
        assertEquals(deletedCount, (Integer)0);
    }

    @Test
    public void getPhonesTest() throws Exception {
        LOGGER.debug("test: getPhones");

        List<Phone> phones = companyPhoneDao.getPhones(null, null);
        assertTrue(phones.size() == 7);

        phones = companyPhoneDao.getPhones(100, 200);
        assertTrue(phones.size() == 3);
    }

    @Test
    public void addPhoneTest() throws Exception {
        LOGGER.debug("test: addPhone");

        Phone phone = new Phone("Iphone XS", 100, new Company(1,"Apple"));
        Integer phoneId = companyPhoneDao.addPhone(phone);

        phone = companyPhoneDao.getPhoneById(phoneId);
        Assert.assertEquals(phone.getName(),"Iphone XS");
    }

    @Test
    public void updatePhoneTest() throws Exception {
        LOGGER.debug("test: updatePhone");

        Phone phone = companyPhoneDao.getPhoneById(1);
        phone.setPrice(150);

        companyPhoneDao.updatePhone(phone);
        phone = companyPhoneDao.getPhoneById(1);
        Assert.assertTrue(phone.getPrice() == 150);
    }

    @Test
    public void updateNonexistentPhoneTest() throws Exception {
        LOGGER.debug("test: updateNonexistentPhone");

        Phone phone = new Phone(100,"newPhone", 300, new Company(1,"Apple"));
        Integer updatedCount = companyPhoneDao.updatePhone(phone);
        Assert.assertTrue(updatedCount == 0);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void deletePhoneTest() throws Exception {
        LOGGER.debug("test: deletePhone");

        companyPhoneDao.deletePhone(1);
        companyPhoneDao.getPhoneById(1);
    }

    @Test
    public void deleteNonexistentPhoneTest() throws Exception {
        LOGGER.debug("test: deleteNonexistentPhone");

        Integer deletedCount = companyPhoneDao.deletePhone(100);
        Assert.assertTrue(deletedCount == 0);
    }
}
