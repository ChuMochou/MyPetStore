package org.csu.petstore.controller;

import jakarta.servlet.http.HttpSession;
import org.csu.petstore.entity.Account;
import org.csu.petstore.entity.Profile;
import org.csu.petstore.entity.SignOnInfo;
import org.csu.petstore.persistence.AccountMapper;
import org.csu.petstore.persistence.ProfileMapper;
import org.csu.petstore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private HttpSession session;
    @Autowired
    private ProfileMapper profileMapper;

    @GetMapping("/signOnForm")
    public String signOnForm() {
        return "account/signOn";
    }

    @PostMapping("/signOn")
    public String signOn(String username, String password,String inputCaptcha, Model model) {
        if(!(session.getAttribute("trueCaptcha")).toString().equalsIgnoreCase(inputCaptcha)) {
            model.addAttribute("signOnMsg","Captcha error!");
            return "account/signOn";
        }
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
        session.setAttribute("loginAccountProfile",profileMapper.selectById(username));
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
    public String newAccount(String username,String password,String inputCaptcha,String repeatedPassword,Model model) {
        if(!(session.getAttribute("trueCaptcha")).toString().equalsIgnoreCase(inputCaptcha)) {
            model.addAttribute("newAccountMsg","Captcha error!");
            return "account/newAccount";
        }
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

    @GetMapping("/editAccountForm")
    public String editAccountForm() {
        List<String> languages = java.util.Arrays.asList("Chinese","English","Japanese");
        List<String> categories = java.util.Arrays.asList("DOGS", "CATS", "FISH", "REPTILES", "BIRDS");
        session.setAttribute("languages",languages);
        session.setAttribute("categories",categories);
        return "account/editAccount";
    }

    @PostMapping("/editAccount")
    public String editAccount(String firstName,String lastName,String email,String phone,
                              String address1,String address2,String city,String state,
                              String zip,String country,String languagePreference,String favouriteCategoryId) {
        accountService.editAccount(firstName,lastName,email,phone,address1,address2,city,state,
                                   zip,country,languagePreference,favouriteCategoryId);
        return "account/accountInfo";
    }
}
