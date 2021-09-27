package com.lai.pai.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("a_day_info")
@EqualsAndHashCode(callSuper = true)
public class ADayInfo extends Model<ADayInfo> implements Serializable {
    @TableId
    private Long id;
    @TableId
    private String code;
    @TableId
    private String name;
    @TableId
    private Date date;
    @TableId
    private double startPrice;
    @TableId
    private double endPrice;
    @TableId
    private double highPrice;
    @TableId
    private double lowPrice;
    @TableId
    private int tradeNum;
    @TableId
    private String percent;
}
