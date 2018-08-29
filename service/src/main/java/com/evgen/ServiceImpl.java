package com.evgen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceImpl implements ServiceApi {

    @Autowired
    private CompanyPhoneDao companyPhoneDao;

    public void setCompanyPhoneDao(CompanyPhoneDao companyPhoneDao) {
        this.companyPhoneDao = companyPhoneDao;
    }

    @Override
    public List<Company> getCompanies() throws DataAccessException {
        return companyPhoneDao.getCompanies();
    }

    @Override
    public Company getCompanyById(Integer companyId) throws DataAccessException {

        return companyPhoneDao.getCompanyById(companyId);
    }

    @Override
    public Integer addCompany(Company company) throws DataAccessException {

        return companyPhoneDao.addCompany(company);
    }

    @Override
    public Integer updateCompany(Company company) throws DataAccessException {

        return companyPhoneDao.updateCompany(company);
    }

    @Override
    public Integer deleteCompany(Integer companyId) throws DataAccessException {

        return companyPhoneDao.deleteCompany(companyId);
    }

    @Override
    public List<Phone> getPhones() throws DataAccessException {

        return companyPhoneDao.getPhones();
    }

    @Override
    public Phone getPhoneById(Integer phoneId) throws DataAccessException {

        return companyPhoneDao.getPhoneById(phoneId);
    }

    @Override
    public Integer addPhone(Phone phone) throws DataAccessException {

        return companyPhoneDao.addPhone(phone);
    }

    @Override
    public Integer updatePhone(Phone phone) throws DataAccessException {

        return companyPhoneDao.updatePhone(phone);
    }

    @Override
    public Integer deletePhone(Integer phoneId) throws DataAccessException {

        return companyPhoneDao.deletePhone(phoneId);
    }
}
