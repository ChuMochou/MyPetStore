package org.csu.petstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("inventory")
public class ItemQuantity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("itemid")
    private String itemId;
    @TableField("qty")
    private Integer quantity;
    @TableField("create_time")
    private String createTime;
    @TableField("update_time")
    private String updateTime;
    private int deleted;
}
