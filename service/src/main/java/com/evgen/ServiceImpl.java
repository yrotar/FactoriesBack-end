package com.evgen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

public class ServiceImpl implements ServiceApi {

    @Autowired
    private CompanyPhoneDao companyPhoneDao;

    public void setCompanyPhoneDao(CompanyPhoneDao companyPhoneDao) {
        this.companyPhoneDao = companyPhoneDao;
    }

    @Override
    public Company getCompanyById(Integer companyId) throws DataAccessException {

        return companyPhoneDao.getCompanyById(companyId);
    }

}
