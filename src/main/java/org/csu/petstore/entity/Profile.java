package org.csu.petstore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("profile")
public class Profile {
    @TableId("userid")
    private String username;
    @TableField("langpref")
    private String languagePrefer;
    @TableField("favcategory")
    private String favoriteCategory;
    @TableField("mylistopt")
    private int myListOption;
    @TableField("banneropt")
    private int bannerOption;
}
