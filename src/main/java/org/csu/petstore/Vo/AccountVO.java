package org.csu.petstore.Vo;

import lombok.Data;

@Data
public class AccountVO {
    //Account
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String status;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    //Profile
    private String languagePrefer;
    private String favoriteCategory;
    private int myListOption;
    private int bannerOption;
    //SignOnInfo
    private String password;
}
