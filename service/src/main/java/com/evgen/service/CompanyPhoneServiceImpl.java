package com.evgen.service;

import com.evgen.Company;
import com.evgen.Phone;
import com.evgen.dao.CompanyPhoneDao;
import com.evgen.exception.OperationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional
public class CompanyPhoneServiceImpl implements CompanyPhoneService {

    private CompanyPhoneDao companyPhoneDao;

    @Autowired
    public CompanyPhoneServiceImpl(CompanyPhoneDao companyPhoneDao) {
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

        return companyPhoneDao.addCompany(company);
    }

    @Override
    public void updateCompany(Company company) throws DataAccessException {
        Assert.notNull(company, "Company should not be null");
        Assert.notNull(company.getCompanyId(), "Company ID should not be null");
        Assert.hasText(company.getName(), "Company should has name");
        Assert.notNull(company.getEmployees(), "Company should has employees");

        if (companyPhoneDao.updateCompany(company) != 1) {
            throw new OperationFailedException("Operation Failed");
        }
    }

    @Override
    public void deleteCompany(Integer companyId) throws DataAccessException {
        Assert.notNull(companyId, "companyId should not be null");

        if (companyPhoneDao.deleteCompany(companyId) != 1) {
            throw new OperationFailedException("Operation Failed");
        }
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
        Assert.notNull(phone.getCompany().getCompanyId(), "Phone should has company");

        return companyPhoneDao.addPhone(phone);
    }

    @Override
    public void updatePhone(Phone phone) throws DataAccessException {
        Assert.notNull(phone, "Phone should not be null");
        Assert.notNull(phone.getPhoneId(), "Phone ID should not be null");

        Assert.hasText(phone.getName(), "Phone should has name");
        Assert.notNull(phone.getPrice(), "Phone should has price");
        Assert.state(phone.getPrice() >= 1 && phone.getPrice() <= 10000, "Phone should has correct price.");
        Assert.notNull(phone.getCompany().getCompanyId(), "Phone should has company");

        if (companyPhoneDao.updatePhone(phone) != 1) {
            throw new OperationFailedException("Operation Failed");
        }
    }

    @Override
    public void deletePhone(Integer phoneId) throws DataAccessException {
        Assert.notNull(phoneId, "phoneId should not be null");

        if (companyPhoneDao.deletePhone(phoneId) != 1) {
            throw new OperationFailedException("Operation Failed");
        }
    }
}