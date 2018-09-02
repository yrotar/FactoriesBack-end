package com.evgen.service;

import com.evgen.Company;
import com.evgen.Phone;
import com.evgen.dao.CompanyPhoneDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceImpl implements ServiceApi {


    private CompanyPhoneDao companyPhoneDao;

    @Autowired
    public ServiceImpl(CompanyPhoneDao companyPhoneDao) {
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

    public Company getCompanyByName(String companyName) throws DataAccessException {

        return companyPhoneDao.getCompanyByName(companyName);
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
    public List<? extends Phone> getPhones() throws DataAccessException {

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