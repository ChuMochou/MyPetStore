package org.csu.petstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("item")
public class Item {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("itemId")
    private String itemId;
    @TableField("productid")
    private String productId;
    @TableField("listprice")
    private BigDecimal listPrice;
    @TableField("unitcost")
    private BigDecimal unitCost;
    @TableField("supplier")
    private int supplierId;
    private String status;
    @TableField("attr1")
    private String attribute1;
    @TableField("attr2")
    private String attribute2;
    @TableField("attr3")
    private String attribute3;
    @TableField("attr4")
    private String attribute4;
    @TableField("attr5")
    private String attribute5;
    @TableField("create_time")
    private String createTime;
    @TableField("update_time")
    private String updateTime;
    private int deleted;
}
