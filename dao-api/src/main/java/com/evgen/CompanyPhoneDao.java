package com.evgen;

import org.springframework.dao.DataAccessException;

import java.util.List;

public interface CompanyPhoneDao {

    List<Company> getCompanies() throws DataAccessException;

    Company getCompanyById(Integer companyId) throws DataAccessException;

    Integer addCompany(Company company) throws DataAccessException;

    Integer updateCompany(Company company) throws DataAccessException;

    Integer deleteCompany(Integer companyId) throws DataAccessException;

    List<Phone> getPhones() throws DataAccessException;

    Phone getPhoneById(Integer phoneId) throws DataAccessException;

    Integer addPhone(Phone phone) throws DataAccessException;

    Integer updatePhone(Phone phone) throws DataAccessException;

    Integer deletePhone(Integer phoneId) throws DataAccessException;


}
