package org.csu.petstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.petstore.entity.Account;
import org.csu.petstore.entity.Profile;
import org.csu.petstore.entity.SignOnInfo;
import org.csu.petstore.persistence.AccountMapper;
import org.csu.petstore.persistence.ProfileMapper;
import org.csu.petstore.persistence.SignOnInfoMapper;
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
    private SignOnInfoMapper signOnInfoMapper;
    @Autowired
    private ProfileMapper profileMapper;

    @Override
    public Account getAccountBySignOnInfo(String username, String password) {
        Account account=new Account();
        QueryWrapper<SignOnInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        queryWrapper.eq("password",password);
        SignOnInfo signOnInfo=signOnInfoMapper.selectOne(queryWrapper);
        if(signOnInfo!=null) {
            account.setUsername(signOnInfo.getUsername());
        }
        return account;
    }

    @Override
    public boolean checkUsernameAvailable(String username) {
        QueryWrapper<SignOnInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        SignOnInfo signOnInfo=signOnInfoMapper.selectOne(queryWrapper);
        return signOnInfo == null;
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
        SignOnInfo signOnInfo=new SignOnInfo();
        signOnInfo.setUsername(username);
        signOnInfo.setPassword(password);
        signOnInfoMapper.insert(signOnInfo);//插入登录信息表
        Account account=new Account();
        account.setUsername(username);
        account.setEmail("");
        account.setFirstName("");
        account.setLastName("");
        account.setStatus("");
        account.setAddress1("");
        account.setAddress2("");
        account.setCity("");
        account.setState("");
        account.setZip("");
        account.setCountry("");
        account.setPhone("");
        accountMapper.insert(account);//插入用户信息表
        Profile profile=new Profile();
        profile.setUsername(username);
        profile.setLanguagePrefer("Chinese");
        profile.setFavoriteCategory("CATS");
        profile.setMyListOption(1);
        profile.setBannerOption(1);
        profileMapper.insert(profile);//插入用户喜好表
    }
}
