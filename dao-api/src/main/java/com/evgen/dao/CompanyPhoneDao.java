package com.evgen.dao;

import com.evgen.Company;
import com.evgen.Phone;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * DAO interface for Company and Phone.
 */
public interface CompanyPhoneDao {

    /**
     * Get companies considering parameters (minEmployees <= company.employees <= maxEmployees).
     *
     * @param minEmployees
     * @param maxEmployees
     * @return List of companies.
     * @throws DataAccessException
     */
    List<Company> getCompanies(Integer minEmployees, Integer maxEmployees) throws DataAccessException;

    /**
     * Get company by ID.
     *
     * @param companyId
     * @return Company.
     * @throws DataAccessException
     */
    Company getCompanyById(Integer companyId) throws DataAccessException;

    /**
     * Get company by name.
     *
     * @param companyName
     * @return Company.
     * @throws DataAccessException
     */
    Company getCompanyByName(String companyName) throws DataAccessException;

    /**
     * Add new company to DB.
     *
     * @param company
     * @return Company ID.
     * @throws DataAccessException
     */
    Integer addCompany(Company company) throws DataAccessException;

    /**
     * Update company.
     *
     * @param company
     * @return Count of updated companies.
     * @throws DataAccessException
     */
    Integer updateCompany(Company company) throws DataAccessException;

    /**
     * Delete company from DB.
     *
     * @param companyId
     * @return Count of deleted companies.
     * @throws DataAccessException
     */
    Integer deleteCompany(Integer companyId) throws DataAccessException;

    /**
     * Get phones considering parameters (minPrice <= phone.price <= maxPrice).
     *
     * @param minPrice
     * @param maxPrice
     * @return List of phones.
     * @throws DataAccessException
     */
    List<Phone> getPhones(Integer minPrice, Integer maxPrice) throws DataAccessException;

    /**
     * Get phone by ID.
     *
     * @param phoneId phone ID.
     * @return Phone with following ID.
     * @throws DataAccessException
     */
    Phone getPhoneById(Integer phoneId) throws DataAccessException;

    /**
     * Add phone to DB.
     *
     * @param phone
     * @return phones ID.
     * @throws DataAccessException
     */
    Integer addPhone(Phone phone) throws DataAccessException;

    /**
     * Update phone.
     *
     * @param phone
     * @return Count of updated phones.
     * @throws DataAccessException
     */
    Integer updatePhone(Phone phone) throws DataAccessException;

    /**
     * Delete phone from DB.
     *
     * @param phoneId
     * @return Count of deleted phones.
     * @throws DataAccessException
     */
    Integer deletePhone(Integer phoneId) throws DataAccessException;

}