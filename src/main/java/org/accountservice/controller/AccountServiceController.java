package org.accountservice.controller;

import org.accountservice.logger.AccountServiceLogger;
import org.accountservice.service.AccountService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

/**
 * Account service controller
 */
@RestController
public class AccountServiceController {

    private final AccountService accountService;
    private final AccountServiceLogger accountServiceLogger;
    AccountServiceController(AccountService accountService,
                             AccountServiceLogger accountServiceLogger) {
        this.accountService = accountService;
        this.accountServiceLogger = accountServiceLogger;
    }

    @GetMapping("/api/amount")
    public Long getAmount(@RequestParam("id") @NonNull Integer id) {
        accountServiceLogger.getCountIncrease();
        return accountService.getAmount(id);
    }

    @PostMapping("/api/amount")
    public void addAmount(@RequestParam("id") @NonNull Integer id, @RequestParam("value") @NonNull Long value) {
        accountServiceLogger.addCountIncrease();
        accountService.addAmount(id, value);
    }

    @PostMapping("/log")
    public void getLog() {
        accountServiceLogger.getLog();
    }

    @DeleteMapping("/log")
    public void clearLog() {
        accountServiceLogger.clearLog();
    }

}
