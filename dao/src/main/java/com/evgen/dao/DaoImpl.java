package com.evgen.dao;

import com.evgen.Company;
import com.evgen.CompanyPhoneDao;
import com.evgen.Phone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DaoImpl implements CompanyPhoneDao {

    @Value("${CompanyPhoneDaoSql.getCompanyById}")
    String getCompanyByIdSql;

    @Value("${CompanyPhoneDaoSql.getCompanies}")
    String getCompaniesSql;

    @Value("${CompanyPhoneDaoSql.addCompany}")
    String addCompanySql;

    @Value("${CompanyPhoneDaoSql.updateCompany}")
    String updateCompanySql;

    @Value("${CompanyPhoneDaoSql.deleteCompany}")
    String deleteCompanySql;


    @Value("${CompanyPhoneDaoSql.getPhoneById}")
    String getPhoneByIdSql;

    @Value("${CompanyPhoneDaoSql.getPhones}")
    String getPhonesSql;

    @Value("${CompanyPhoneDaoSql.addPhones}")
    String addPhonesSql;

    @Value("${CompanyPhoneDaoSql.updatePhones}")
    String updatePhonesSql;

    @Value("${CompanyPhoneDaoSql.deletePhones}")
    String deletePhonesSql;



    private static final String COMPANY_ID = "companyId";
    private static final String COMPANY_NAME = "name";
    private static final String COMPANY_EMPLOYESS = "employees";

    private static final String PHONE_ID = "phoneId";
    private static final String PHONE_NAME = "name";
    private static final String PHONE_PRICE = "price";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DaoImpl(DataSource dataSource) {

        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Company> getCompanies() throws DataAccessException {

       return namedParameterJdbcTemplate.query(getCompaniesSql, new CompanyRowMapper());
    }

    @Override
    public Company getCompanyById(Integer companyId) throws DataAccessException {
            SqlParameterSource namedParameters = new MapSqlParameterSource(COMPANY_ID, companyId);
            Company company = namedParameterJdbcTemplate.queryForObject(getCompanyByIdSql, namedParameters, new CompanyRowMapper());

            return company;
    }

    @Override
    public Integer addCompany(Company company) throws DataAccessException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(COMPANY_ID, company.getCompanyId());
        parameterSource.addValue(COMPANY_NAME, company.getName());
        parameterSource.addValue(COMPANY_EMPLOYESS, company.getEmployees());

        namedParameterJdbcTemplate.update(addCompanySql, parameterSource, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public Integer updateCompany(Company company) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(COMPANY_ID, company.getCompanyId());
        parameterSource.addValue(COMPANY_NAME, company.getName());
        parameterSource.addValue(COMPANY_EMPLOYESS, company.getEmployees());

        return namedParameterJdbcTemplate.update(updateCompanySql, parameterSource);
    }

    @Override
    public Integer deleteCompany(Integer companyId) throws DataAccessException {
        SqlParameterSource namedParameters = new MapSqlParameterSource(COMPANY_ID, companyId);

        return namedParameterJdbcTemplate.update(deleteCompanySql, namedParameters);
    }

    @Override
    public List<Phone> getPhones() throws DataAccessException {

        return namedParameterJdbcTemplate.query(getPhonesSql, new PhoneRowMapper());
    }

    @Override
    public Phone getPhoneById(Integer phoneId) throws DataAccessException {
            SqlParameterSource namedParameters = new MapSqlParameterSource(PHONE_ID, phoneId);
            Phone phone = namedParameterJdbcTemplate.queryForObject(getPhoneByIdSql, namedParameters, new PhoneRowMapper());

            return phone;
    }

    @Override
    public Integer addPhone(Phone phone) throws DataAccessException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(PHONE_ID, phone.getPhoneId());
        parameterSource.addValue(PHONE_NAME, phone.getName());
        parameterSource.addValue(PHONE_PRICE, phone.getPrice());
        parameterSource.addValue(COMPANY_ID, phone.getCompanyId());

        namedParameterJdbcTemplate.update(addPhonesSql, parameterSource, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public Integer updatePhone(Phone phone) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(PHONE_ID, phone.getPhoneId());
        parameterSource.addValue(PHONE_NAME, phone.getName());
        parameterSource.addValue(PHONE_PRICE, phone.getPrice());
        parameterSource.addValue(COMPANY_ID, phone.getCompanyId());

        return namedParameterJdbcTemplate.update(updatePhonesSql,parameterSource);
    }

    @Override
    public Integer deletePhone(Integer phoneId) throws DataAccessException {
        SqlParameterSource namedParameters = new MapSqlParameterSource(PHONE_ID, phoneId);

        return namedParameterJdbcTemplate.update(deletePhonesSql,namedParameters);
    }


    private class CompanyRowMapper implements RowMapper<Company> {

        public Company mapRow(ResultSet resultSet, int i) throws SQLException {
            Company company = new Company(resultSet.getInt("company_id"),
                    resultSet.getString("name"),
                    resultSet.getInt("employees"));

            return company;
        }
    }

    private class PhoneRowMapper implements RowMapper<Phone> {

        public Phone mapRow(ResultSet resultSet, int i) throws SQLException {
            Phone phone = new Phone(resultSet.getInt("phone_id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price"),
                    resultSet.getInt("company_id"));

            return phone;
        }
    }
}
