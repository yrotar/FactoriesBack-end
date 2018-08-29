package com.evgen.dao;

import com.evgen.Company;
import com.evgen.CompanyPhoneDao;
import com.evgen.Phone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoImpl implements CompanyPhoneDao {

    @Value("${CompanyPhoneDaoSql.getCompanyById}")
    String getCompanyByIdSql;

    private static final String COMPANY_ID = "companyId";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Company getCompanyById(Integer companyId) throws DataAccessException {
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource(COMPANY_ID, companyId);
            Company company = namedParameterJdbcTemplate.queryForObject(getCompanyByIdSql, namedParameters, new CompanyRowMapper());

            return company;
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {

            return null;
        }

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
