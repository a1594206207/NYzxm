package com.nzxmmp.nzxm.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 外资项目搜索实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("yzxmbd")
public class YzxmSearch {

    @TableId
    private String id;

    //项目名称
    @TableField(condition = SqlCondition.LIKE)
    private String xmmc;

    //企业类型
    @TableField(condition = SqlCondition.LIKE)
    private String qylx;

    //上报部门
    @TableField(condition = SqlCondition.LIKE)
    private String unitName;

    //起始年月
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField(exist = false)  //表示非表内字段
    @JSONField(format = "yyyy/MM/dd")
    private java.util.Date Creattime;

    //终止年月
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField(exist = false)
    @JSONField(format = "yyyy/MM/dd")
    private Date Endtime;

    //注册资金
    @TableField(condition = SqlCondition.LIKE)
    private String zczb;

    //投资总额
    @TableField(condition = SqlCondition.LIKE)
    private String tzze;

    //外方投资方
    @TableField(condition = SqlCondition.LIKE)
    private String wftzf;

    //中方投资方
    @TableField(condition = SqlCondition.LIKE)
    private String zftzf;

    //项目状态
    @TableField(condition = SqlCondition.LIKE)
    private String state;

    //项目类别（落地类别）
    @TableField(condition = SqlCondition.LIKE)
    private String ldlb;

    //定义账户属性，表示非nzxm表所有的字段，作用于权限查询
    @TableField(exist = false)
    private String zh;



}

