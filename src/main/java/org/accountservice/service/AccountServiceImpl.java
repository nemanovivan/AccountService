package org.accountservice.service;

import org.accountservice.exception.AccountServiceException;
import org.accountservice.dao.AccountServiceDao;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Account service implementation
 */
@Service
public class AccountServiceImpl implements AccountService {

    AccountServiceDao accountServiceDao;
    public AccountServiceImpl(AccountServiceDao accountServiceDao) {
        this.accountServiceDao = accountServiceDao;
    }

    @Override
    @Cacheable("cacheValue")
    public Long getAmount(Integer id) {
        return accountServiceDao.getAmount(id).orElseThrow(
                () -> new AccountServiceException("Account with id " + id + " does not exist")
        );
    }

    @Override
    public void addAmount(Integer id, Long value) {
        if (!accountServiceDao.addAmount(id, value)){
            throw new AccountServiceException("Account with id " + id + " does not exist");
        }
    }
}
