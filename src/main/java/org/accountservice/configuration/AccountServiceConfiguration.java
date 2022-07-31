package org.accountservice.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Account service configuration
 */
@Configuration
public class AccountServiceConfiguration {

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(BasicDataSource basicDataSource){
        return new NamedParameterJdbcTemplate(basicDataSource);
    }

    @Bean
    public BasicDataSource basicDataSource(
            @Value("${database.url}") String url,
            @Value("${database.username}") String userName,
            @Value("${database.password}") String password,
            @Value("${database.schema}") String schema
    ) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setDriverClassName("org.postgresql.Driver");
        basicDataSource.setUsername(userName);
        basicDataSource.setPassword(password);
        basicDataSource.setDefaultSchema(schema);
        return basicDataSource;
    }
}
