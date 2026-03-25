package org.csu.petstore.service;

import org.csu.petstore.controller.AccountController;
import org.csu.petstore.entity.Account;
import org.springframework.ui.Model;

public interface AccountService {
    Account getAccountByUsername(String username);
    Account getAccountBySignOnInfo(String username, String password);
    boolean checkUsernameAvailable(String username);
    boolean checkNewAccount(String username, String password, String repeatedPassword, Model model);
    void insertNewAccount(String username,String password);
    void editAccount(String firstName,String lastName,String email,String phone,
                     String address1,String address2,String city,String state,
                     String zip,String country,String languagePreference,String favouriteCategoryId);
}
