package com.evgen;

import org.springframework.dao.DataAccessException;

public interface ServiceApi {

    Company getCompanyById(Integer companyId) throws DataAccessException;

}
