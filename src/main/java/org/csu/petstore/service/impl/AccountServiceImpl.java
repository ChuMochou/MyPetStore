package org.csu.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpSession;
import org.csu.petstore.entity.Account;
import org.csu.petstore.persistence.AccountMapper;
import org.csu.petstore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Objects;


@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private HttpSession session;

    @Override
    public Account getAccountByUsername(String username) {
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userid",username);
        return accountMapper.selectOne(queryWrapper);
    }

    @Override
    public Account getAccountByUsernameAndPassword(String username, String password) {
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userid",username);
        queryWrapper.eq("password",password);
        return accountMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean checkUsernameAvailable(String username) {
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userid",username);
        Account account=accountMapper.selectOne(queryWrapper);
        return account == null;
    }

    @Override
    public boolean checkNewAccount(String username, String password, String repeatedPassword,Model model) {
        if(!checkUsernameAvailable(username)) {
            model.addAttribute("newAccountMsg","Username already exists!");
            return false;
        }
        else if(username.length()<4||username.length()>10) {
            model.addAttribute("newAccountMsg","Username must be 4 to 10 characters long!");
            return false;
        }
        else if(!Objects.equals(password, repeatedPassword)) {
            model.addAttribute("newAccountMsg","Password and confirm password do not match!");
            return false;
        }
        else if(password.length()<4||password.length()>10) {
            model.addAttribute("newAccountMsg","Password must be 4 to 10 characters long!");
            return false;
        }
        return true;
    }

    @Override
    public void insertNewAccount(String username, String password) {
        Account account=new Account();
        account.setUsername(username);
        account.setEmail("");
        account.setFirstName("");
        account.setLastName("");
        account.setStatus("OK");
        account.setAddress1("");
        account.setAddress2("");
        account.setCity("");
        account.setState("");
        account.setZip("");
        account.setCountry("");
        account.setPhone("");
        account.setLanguagePrefer("Chinese");
        account.setFavoriteCategory("CATS");
        account.setMyListOption(1);
        account.setBannerOption(1);
        account.setPassword(password);
        accountMapper.insert(account);
    }

    @Override
    public void editAccount(String firstName,String lastName,String email,String phone,
                            String address1,String address2,String city,String state,
                            String zip,String country,String languagePreference,String favouriteCategoryId) {
        Account account=(Account)session.getAttribute("loginAccount");
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setEmail(email);
        account.setPhone(phone);
        account.setAddress1(address1);
        account.setAddress2(address2);
        account.setCity(city);
        account.setState(state);
        account.setZip(zip);
        account.setCountry(country);
        account.setStatus("OK");
        account.setLanguagePrefer(languagePreference);
        account.setFavoriteCategory(favouriteCategoryId);
        account.setMyListOption(1);
        account.setBannerOption(1);
        session.setAttribute("loginAccount",account);
        accountMapper.updateById(account);
    }
}
