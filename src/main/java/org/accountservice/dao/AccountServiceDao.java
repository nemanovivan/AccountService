package org.accountservice.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Account service data access object
 */
@Repository
public class AccountServiceDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AccountServiceDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Retrieves current balance or zero if addAmount() method was not called before for specified id
     * Returns null if id not found
     *
     * @param id balance identifier
     */
    public Optional<Long> getAmount(Integer id) {
        try {
            return Optional.ofNullable(
                    namedParameterJdbcTemplate.queryForObject(
                            "SELECT amount FROM accounts WHERE id = :id",
                            new MapSqlParameterSource().addValue("id", id),
                            (rs, rn) -> rs.getLong("amount")
                    )
            );
        } catch (EmptyResultDataAccessException ignored) {
            return Optional.empty();
        }
    }

    /**
     * Increases balance or set if addAmount() method was called first time
     *
     * @param id balance identifier
     * @param value positive or negative value, which must be added to current balance
     */
    public boolean addAmount(Integer id, Long value) {
        return namedParameterJdbcTemplate.update(
                "UPDATE accounts SET amount = amount + :value WHERE id = :id",
                new MapSqlParameterSource().addValue("id", id).addValue("value", value)
        ) > 0;
    }
}
