package com.evgen.service;

import com.evgen.Company;
import com.evgen.Phone;
import com.evgen.dao.CompanyPhoneDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class ServiceImpl implements Service {


    private CompanyPhoneDao companyPhoneDao;

    @Autowired
    public ServiceImpl(CompanyPhoneDao companyPhoneDao) {
        this.companyPhoneDao = companyPhoneDao;
    }

    @Override
    public List<Company> getCompanies(Integer minEmployees, Integer maxEmployees) throws DataAccessException {

        return companyPhoneDao.getCompanies(minEmployees, maxEmployees);
    }

    @Override
    public Company getCompanyById(Integer companyId) throws DataAccessException {

        Assert.notNull(companyId, "companyId should not be null");

        return companyPhoneDao.getCompanyById(companyId);
    }

    public Company getCompanyByName(String companyName) throws DataAccessException {

        Assert.notNull(companyName, "companyName should not be null");

        return companyPhoneDao.getCompanyByName(companyName);
    }

    @Override
    public Integer addCompany(Company company) throws DataAccessException {

        Assert.notNull(company, "Company should not be null");
        Assert.isNull(company.getCompanyId(), "Company ID should be null");
        Assert.hasText(company.getName(), "Company should has name");
        Assert.notNull(company.getEmployees(), "Company should has employees");

        try {
            if (companyPhoneDao.getCompanyByName(company.getName()) != null)
                throw new IllegalArgumentException("Company with name " + company.getName() + " already exist");
        } catch (DataAccessException e) {
            }

        return companyPhoneDao.addCompany(company);
    }

    @Override
    public Integer updateCompany(Company company) throws DataAccessException {

        Assert.notNull(company, "Company should not be null");
        Assert.notNull(company.getCompanyId(), "Company ID should not be null");
        Assert.hasText(company.getName(), "Company should has name");
        Assert.notNull(company.getEmployees(), "Company should has employees");

        Company existCompany = companyPhoneDao.getCompanyById(company.getCompanyId());
        Assert.notNull(existCompany, "Company should be correct");

        return companyPhoneDao.updateCompany(company);
    }

    @Override
    public Integer deleteCompany(Integer companyId) throws DataAccessException {

        Assert.notNull(companyId, "companyId should not be null");

        Company company = companyPhoneDao.getCompanyById(companyId);
        Assert.notNull(company, "Company should be correct");

        return companyPhoneDao.deleteCompany(companyId);
    }

    @Override
    public List<Phone> getPhones(Integer minPrice, Integer maxPrice) throws DataAccessException {

        return companyPhoneDao.getPhones(minPrice, maxPrice);
    }

    @Override
    public Phone getPhoneById(Integer phoneId) throws DataAccessException {

        Assert.notNull(phoneId, "phoneId should not be null");

        return companyPhoneDao.getPhoneById(phoneId);
    }

    @Override
    public Integer addPhone(Phone phone) throws DataAccessException {

        Assert.notNull(phone, "Phone should not be null");
        Assert.isNull(phone.getPhoneId(), "Phone ID should be null");
        Assert.hasText(phone.getName(), "Phone should has name");
        Assert.notNull(phone.getPrice(), "Phone should has price");
        Assert.state(phone.getPrice() >= 1 && phone.getPrice() <= 10000, "Phone should has correct price.");
        Assert.notNull(phone.getCompanyId(), "Phone should has company");

        Company company = companyPhoneDao.getCompanyById(phone.getCompanyId());
        Assert.notNull(company, "Phone should has correct company");

        return companyPhoneDao.addPhone(phone);
    }

    @Override
    public Integer updatePhone(Phone phone) throws DataAccessException {

        Assert.notNull(phone, "Phone should not be null");
        Assert.notNull(phone.getPhoneId(), "Phone ID should not be null");

        Phone existPhone = companyPhoneDao.getPhoneById(phone.getPhoneId());
        Assert.notNull(existPhone, "Phone ID should be correct");

        Assert.hasText(phone.getName(), "Phone should has name");
        Assert.notNull(phone.getPrice(), "Phone should has price");
        Assert.state(phone.getPrice() >= 1 && phone.getPrice() <= 10000, "Phone should has correct price.");
        Assert.notNull(phone.getCompanyId(), "Phone should has company");

        Company company = companyPhoneDao.getCompanyById(phone.getCompanyId());
        Assert.notNull(company, "Phone should has correct company");

        return companyPhoneDao.updatePhone(phone);
    }

    @Override
    public Integer deletePhone(Integer phoneId) throws DataAccessException {

        Assert.notNull(phoneId, "phoneId should not be null");
        Phone phone = companyPhoneDao.getPhoneById(phoneId);
        Assert.notNull(phone, "Phone with phoneId should be correct");

        return companyPhoneDao.deletePhone(phoneId);
    }
}