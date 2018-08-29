package com.evgen;

import org.springframework.dao.DataAccessException;

public interface CompanyPhoneDao {

    Company getCompanyById(Integer companyId) throws DataAccessException;

}
