package org.csu.petstore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("category")
public class Category {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value="catid")
    private String categoryId;
    private String name;
    @TableField("descn")
    private String description;
    @TableField("create_time")
    private String createTime;
    @TableField("update_time")
    private String updateTime;
    private int deleted;
}
