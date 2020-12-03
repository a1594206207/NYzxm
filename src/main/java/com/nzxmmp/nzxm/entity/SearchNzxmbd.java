package com.nzxmmp.nzxm.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 内资项目搜索实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("nzxmbd")
public class SearchNzxmbd {

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
        private  String unitName;

        //起始年月
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @TableField(exist = false)  //表示非表内字段
        @JSONField(format = "yyyy/MM/dd")
        private java.util.Date Creattime ;

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

        //投资方所在企业
        @TableField(condition = SqlCondition.LIKE)
        private String tzfszqy;

        //投资方名称
        @TableField(condition = SqlCondition.LIKE)
        private  String tzfmc;

        //项目状态
        @TableField(condition = SqlCondition.LIKE)
        private  String state;

        //项目类别
        @TableField(condition = SqlCondition.LIKE)
        private String xmlb;

        //定义账户属性，表示非nzxm表所有的字段，作用于权限查询
        @TableField(exist = false)
        private String zh;



        private java.util.Date Updatetime;
        private String tzfjj;
        private String xgsmc;
        private String tyxydm;
        private java.util.Date ldsj;
        private String xmjj;
        private String hydl;
        private String hyzl;
        private String hyxl;
        private String sfwbq;
        private String sfqyzb;
        private String sfgnxzx;
        private String gnzxlx;
        private String sfsyrgzn;
        private String sfsyxyt;
        private String sfwyqyxm;
        private String qyxmsshd;
        private String sfybxq;
        private String hjxy;
        private String ydxq;
        private String ckfxq;
        private String bglxq;
        private String dysjdwje;
        private String nnsze;
        private String nyysr;
        private String njck;
        private String gdzctz;
        private String ddjy;
        private String userName;
        private String userMobilePhone;
        private String bz;
        private String xmlydSheng;
        private String xmlydShi;
        private java.util.Date nldsj;
        private String zsjd;
        private java.util.Date updatetime;
        private String deletestate;
        private String bak1;
        private String bak2;
        private String bak3;
        private String bak4;
        private String bak5;
        private String bak6;
        private String bak7;
        private String bak8;
        private String bak9;
        private String bak10;

    }
