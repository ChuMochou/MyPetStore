package org.csu.petstore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {
    @TableId("itemId")
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
}
