package com.lai.pai.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@TableName("a_code")
@EqualsAndHashCode(callSuper = true)
public class ACode extends Model<ACode> implements Serializable {
    @TableId
    private Long id;
    @TableId
    private String code;
    @TableId
    private String name;
    @TableId
    private String status;
}
