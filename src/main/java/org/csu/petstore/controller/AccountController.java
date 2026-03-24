package org.csu.petstore.controller;

import jakarta.servlet.http.HttpSession;
import org.csu.petstore.entity.Account;
import org.csu.petstore.entity.SignOnInfo;
import org.csu.petstore.persistence.AccountMapper;
import org.csu.petstore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/signOnForm")
    public String signOnForm() {
        return "account/signOn";
    }
    @PostMapping("/signOn")
    public String signOn(String username, String password, Model model, HttpSession session) {
        if(accountService.checkUsernameAvailable(username)) {
            model.addAttribute("signOnMsg","Username is not exists!");
            return "account/signOn";
        }
        Account account=accountService.getAccountBySignOnInfo(username,password);
        if(account.getUsername()==null) {
            model.addAttribute("signOnMsg","Password error!");
            return "account/signOn";
        }
        session.setAttribute("loginAccount",account);
        return "catalog/main";
    }
    @GetMapping("/signOff")
    public String signOff(HttpSession session) {
        session.removeAttribute("loginAccount");
        return "catalog/main";
    }
    @GetMapping("/newAccountForm")
    public String newAccountForm() {
        return "account/newAccount";
    }
    @PostMapping("/newAccount")
    public String newAccount(String username,String password,String repeatedPassword,Model model) {
        if(!accountService.checkNewAccount(username,password,repeatedPassword,model)) {
            return "account/newAccount";
        }
        accountService.insertNewAccount(username,password);
        return "account/signOn";
    }
    @GetMapping("/viewAccount")
    public String viewAccount() {
        return "account/accountInfo";
    }
}
