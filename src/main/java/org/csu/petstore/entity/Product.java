package org.csu.petstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("product")
public class Product {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "productid")
    private String productId;
    @TableField(value = "category")
    private String categoryId;
    private String name;
    @TableField(value = "descn")
    private String description;
    @TableField("create_time")
    private String createTime;
    @TableField("update_time")
    private String updateTime;
    private int deleted;
}
