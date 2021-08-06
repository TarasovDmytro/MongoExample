package service;

import dao.AccountDao;

public class AccountService {

    private final AccountDao accountDao;

    public AccountService(AccountDao accountDao) {

        this.accountDao = accountDao;
    }
}
