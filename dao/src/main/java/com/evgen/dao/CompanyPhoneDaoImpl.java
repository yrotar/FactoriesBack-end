package com.evgen.dao;

import com.evgen.Company;
import com.evgen.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyPhoneDaoImpl implements CompanyPhoneDao {

    private static final String COMPANY_ID = "companyId";
    private static final String COMPANY_NAME = "name";
    private static final String COMPANY_EMPLOYEES = "employees";
    private static final String PHONE_ID = "phoneId";
    private static final String PHONE_NAME = "name";
    private static final String PHONE_PRICE = "price";
    private static final String MIN_EMPLOYEES = "minEmployees";
    private static final String MAX_EMPLOYEES = "maxEmployees";
    private static final String MIN_PRICE = "minPrice";
    private static final String MAX_PRICE = "maxPrice";

    @Value("${CompanyPhoneDaoSql.getCompanyById}")
    private String getCompanyByIdSql;
    @Value("${CompanyPhoneDaoSql.getCompanies}")
    private String getCompaniesSql;
    @Value("${CompanyPhoneDaoSql.getCompanyByName}")
    private String getCompanyByNameSql;
    @Value("${CompanyPhoneDaoSql.addCompany}")
    private String addCompanySql;
    @Value("${CompanyPhoneDaoSql.updateCompany}")
    private String updateCompanySql;
    @Value("${CompanyPhoneDaoSql.deleteCompany}")
    private String deleteCompanySql;
    @Value("${CompanyPhoneDaoSql.getPhoneById}")
    private String getPhoneByIdSql;
    @Value("${CompanyPhoneDaoSql.getPhones}")
    private String getPhonesSql;
    @Value("${CompanyPhoneDaoSql.addPhones}")
    private String addPhonesSql;
    @Value("${CompanyPhoneDaoSql.updatePhones}")
    private String updatePhonesSql;
    @Value("${CompanyPhoneDaoSql.deletePhones}")
    private String deletePhonesSql;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CompanyPhoneDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Company> getCompanies(Integer minEmployees, Integer maxEmployees) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(MIN_EMPLOYEES, minEmployees);
        parameterSource.addValue(MAX_EMPLOYEES, maxEmployees);

        return namedParameterJdbcTemplate.query(getCompaniesSql, parameterSource, new CompanyWithIgnoredPhonesRowMapper());
    }

    @Override
    public Company getCompanyById(Integer companyId) throws DataAccessException {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource(COMPANY_ID, companyId);

        return namedParameterJdbcTemplate.queryForObject(getCompanyByIdSql, namedParameters, new CompanyRowMapper());

    }

    @Override
    public Company getCompanyByName(String companyName) throws DataAccessException {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource(COMPANY_NAME, companyName);

        return namedParameterJdbcTemplate.queryForObject(getCompanyByNameSql, namedParameters, new CompanyRowMapper());
    }

    @Override
    public Integer addCompany(Company company) throws DataAccessException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(COMPANY_NAME, company.getName());
        parameterSource.addValue(COMPANY_EMPLOYEES, company.getEmployees());

        namedParameterJdbcTemplate.update(addCompanySql, parameterSource, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public Integer updateCompany(Company company) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(COMPANY_ID, company.getCompanyId());
        parameterSource.addValue(COMPANY_NAME, company.getName());
        parameterSource.addValue(COMPANY_EMPLOYEES, company.getEmployees());

        return namedParameterJdbcTemplate.update(updateCompanySql, parameterSource);
    }

    @Override
    public Integer deleteCompany(Integer companyId) throws DataAccessException {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource(COMPANY_ID, companyId);
        return namedParameterJdbcTemplate.update(deleteCompanySql, namedParameters);
    }

    @Override
    public List<Phone> getPhones(Integer minPrice, Integer maxPrice) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(MIN_PRICE, minPrice);
        parameterSource.addValue(MAX_PRICE, maxPrice);

        return namedParameterJdbcTemplate.query(getPhonesSql, parameterSource, new PhoneWithCompanyRowMapper());
    }

    @Override
    public Phone getPhoneById(Integer phoneId) throws DataAccessException {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource(PHONE_ID, phoneId);

        return namedParameterJdbcTemplate.queryForObject(getPhoneByIdSql, namedParameters, new PhoneWithCompanyRowMapper());
    }

    @Override
    public Integer addPhone(Phone phone) throws DataAccessException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(PHONE_NAME, phone.getName());
        parameterSource.addValue(PHONE_PRICE, phone.getPrice());
        parameterSource.addValue(COMPANY_ID, phone.getCompany().getCompanyId());

        namedParameterJdbcTemplate.update(addPhonesSql, parameterSource, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public Integer updatePhone(Phone phone) throws DataAccessException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue(PHONE_ID, phone.getPhoneId());
        parameterSource.addValue(PHONE_NAME, phone.getName());
        parameterSource.addValue(PHONE_PRICE, phone.getPrice());
        parameterSource.addValue(COMPANY_ID, phone.getCompany().getCompanyId());

        return namedParameterJdbcTemplate.update(updatePhonesSql, parameterSource);
    }

    @Override
    public Integer deletePhone(Integer phoneId) throws DataAccessException {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource(PHONE_ID, phoneId);
        return namedParameterJdbcTemplate.update(deletePhonesSql, namedParameters);
    }


    private class CompanyWithIgnoredPhonesRowMapper implements RowMapper<Company> {

        public Company mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Company(resultSet.getInt("company_id"),
                    resultSet.getString("name"),
                    resultSet.getInt("employees"),
                    resultSet.getFloat("avg_price"));
        }
    }

    private class PhoneWithCompanyRowMapper implements RowMapper<Phone> {

        public Phone mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Phone(resultSet.getInt("phone_id"),
                    resultSet.getString("phone.name"),
                    resultSet.getInt("price"),
                    new Company(
                            resultSet.getInt("company_id"),
                            resultSet.getString("company.name")));
        }
    }

    private class CompanyRowMapper implements RowMapper<Company> {

        public Company mapRow(ResultSet resultSet, int i) throws SQLException {
            Company company = new Company(
                    resultSet.getInt("company_id"),
                    resultSet.getString("company_name"),
                    resultSet.getInt("employees"),
                    resultSet.getObject("avg_price", Float.class),
                    new ArrayList<>()
            );

            if (resultSet.getObject("phone_id", Integer.class) == null) {
                return company;
            }

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