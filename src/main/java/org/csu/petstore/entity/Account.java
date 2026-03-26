package org.csu.petstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("account")
public class Account {
    //account
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("userid")
    private String username;
    private String email;
    @TableField("firstname")
    private String firstName;
    @TableField("lastname")
    private String lastName;
    private String status;
    @TableField("addr1")
    private String address1;
    @TableField("addr2")
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    //profile
    @TableField("langpref")
    private String languagePrefer;
    @TableField("favcategory")
    private String favoriteCategory;
    @TableField("mylistopt")
    private int myListOption;
    @TableField("banneropt")
    private int bannerOption;
    //signOn
    private String password;

    @TableField("create_time")
    private String createTime;
    @TableField("update_time")
    private String updateTime;
    private int deleted;
}
