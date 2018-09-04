package com.evgen.dao;

import com.evgen.Company;
import com.evgen.Phone;
import com.evgen.dto.CompanyWithIgnoredPhones;
import com.evgen.dto.PhoneWithCompany;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.List;

@Repository
public class DaoImpl implements CompanyPhoneDao {

    private static final String COMPANY_ID = "companyId";
    private static final String COMPANY_NAME = "name";
    private static final String COMPANY_EMPLOYESS = "employees";
    private static final String PHONE_ID = "phoneId";
    private static final String PHONE_NAME = "name";
    private static final String PHONE_PRICE = "price";
    private static final String MIN_EMPLOYEES = "minEmployees";
    private static final String MAX_EMPLOYEES = "maxEmployees";
    private static final String MIN_PRICE = "minPrice";
    private static final String MAX_PRICE = "maxPrice";

    @Value("${CompanyPhoneDaoSql.getCompanyById}")
    String getCompanyByIdSql;
    @Value("${CompanyPhoneDaoSql.getCompanies}")
    String getCompaniesSql;
    @Value("${CompanyPhoneDaoSql.getCompanyByName}")
    String getCompanyByNameSql;
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
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public DaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<? extends Company> getCompanies(Integer minEmployees, Integer maxEmployees) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(MIN_EMPLOYEES, minEmployees);
        parameterSource.addValue(MAX_EMPLOYEES, maxEmployees);

        return namedParameterJdbcTemplate.query(getCompaniesSql, parameterSource, new CompanyWithIgnoredPhonesRowMapper());
    }

    @Override
    public Company getCompanyById(Integer companyId) throws DataAccessException {
        SqlParameterSource namedParameters = new MapSqlParameterSource(COMPANY_ID, companyId);
        Company company = namedParameterJdbcTemplate.queryForObject(getCompanyByIdSql, namedParameters, new CompanyRowMapper());

        return company;
    }

    @Override
    public Company getCompanyByName(String companyName) throws DataAccessException {
        SqlParameterSource namedParameters = new MapSqlParameterSource(COMPANY_NAME, companyName);
        Company company = namedParameterJdbcTemplate.queryForObject(getCompanyByNameSql, namedParameters, new CompanyRowMapper());

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
    public List<? extends Phone> getPhones(Integer minPrice, Integer maxPrice) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(MIN_PRICE, minPrice);
        parameterSource.addValue(MAX_PRICE, maxPrice);
        return namedParameterJdbcTemplate.query(getPhonesSql, parameterSource, new PhoneWithCompanyRowMapper());
    }

    @Override
    public Phone getPhoneById(Integer phoneId) throws DataAccessException {
        SqlParameterSource namedParameters = new MapSqlParameterSource(PHONE_ID, phoneId);

        return namedParameterJdbcTemplate.queryForObject(getPhoneByIdSql, namedParameters, new PhoneWithCompanyRowMapper());
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

        return namedParameterJdbcTemplate.update(updatePhonesSql, parameterSource);
    }

    @Override
    public Integer deletePhone(Integer phoneId) throws DataAccessException {
        SqlParameterSource namedParameters = new MapSqlParameterSource(PHONE_ID, phoneId);

        return namedParameterJdbcTemplate.update(deletePhonesSql, namedParameters);
    }


    private class CompanyWithIgnoredPhonesRowMapper implements RowMapper<CompanyWithIgnoredPhones> {

        public CompanyWithIgnoredPhones mapRow(ResultSet resultSet, int i) throws SQLException {
            return new CompanyWithIgnoredPhones(resultSet.getInt("company_id"),
                    resultSet.getString("name"),
                    resultSet.getInt("employees"),
                    resultSet.getFloat("price"));
        }
    }

    private class PhoneWithCompanyRowMapper implements RowMapper<PhoneWithCompany> {

        public PhoneWithCompany mapRow(ResultSet resultSet, int i) throws SQLException {
            return new PhoneWithCompany(resultSet.getInt("phone_id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price"),
                    resultSet.getInt("company_id"),
                    resultSet.getString("company.name"));
        }
    }

    private class CompanyRowMapper implements RowMapper<Company> {

        public Company mapRow(ResultSet resultSet, int i) throws SQLException {

            Company company = new Company(resultSet.getInt("company_id"),
                    resultSet.getString("name"),
                    resultSet.getInt("employees"),
                    new ArrayList<Phone>());

            do {
                company.getPhones().add(new Phone(
                        resultSet.getInt("phone_id"),
                        resultSet.getString("phone.name"),
                        resultSet.getInt("price")
                ));
            } while (resultSet.next());

            return company;
        }
    }
}
