package org.csu.petstore.service;

import org.csu.petstore.entity.Account;
import org.springframework.ui.Model;

public interface AccountService {
    Account getAccountBySignOnInfo(String username, String password);
    boolean checkUsernameAvailable(String username);
    boolean checkNewAccount(String username, String password, String repeatedPassword, Model model);
    void insertNewAccount(String username,String password);
}
