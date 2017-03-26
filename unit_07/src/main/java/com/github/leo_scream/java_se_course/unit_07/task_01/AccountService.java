package com.github.leo_scream.java_se_course.unit_07.task_01;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class AccountService {
    private static volatile AccountService instance;
    private final Set<Account> accounts;

    private AccountService() {
        accounts = new HashSet<>();
    }

    public static AccountService getInstance() {
        AccountService localInstance = instance;
        if (localInstance == null) {
            synchronized (AccountService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = instance = new AccountService();
                }
            }
        }
        return localInstance;
    }

    public void add(Account account) {
    }

    public void remove(Account account) {
    }
}
