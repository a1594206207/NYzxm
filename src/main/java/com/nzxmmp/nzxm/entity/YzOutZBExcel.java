package com.nzxmmp.nzxm.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 外资在谈、半年 execl 导出数据实体类
 */

@Data
@HeadRowHeight(40)
@ContentRowHeight(20)
@ColumnWidth(25)
public class YzOutZBExcel {


    @ExcelProperty("项目名称")
    @ColumnWidth(60)
    private  String xmmc;
    @ExcelProperty("企业类型")
    private String qylx;
    @ExcelProperty("行业大类")
    private String hydl;
    @ExcelProperty("行业中类")
    private String hyzl;
    @ExcelProperty("行业小类")
    private String hyxl;
    @ExcelProperty("外方投资方")
    @ColumnWidth(60)
    private String wftzf;
    @ExcelProperty("中方投资方")
    @ColumnWidth(60)
    private  String zftzf;
    @ExcelProperty("投资国别")
    private  String tzgb;
    @ExcelProperty("其他国别")
    private  String qtgb;
    @ExcelProperty("投资总额（万元）")
    private String tzze;
    @ExcelProperty("注册资本（万元)")
    private String zczb;
    @ExcelProperty("合同外资额（万美元）")
    private String htwze;
    @ExcelProperty("项目简介")
    @ColumnWidth(240)
    private  String xmjj;
    @ExcelProperty("拟落地时间")
    @ColumnWidth(50)
    @DateTimeFormat("yyyy/MM/dd")
    private java.util.Date nldsj;
    @ExcelProperty("是否为世界500强投资")
    private String sfwbq;
    @ExcelProperty("是否为地区总部")
    private  String sfqyzb;
    @ExcelProperty("是否为研发中心 ")
    private String sfwyfzx;
    @ExcelProperty("是否属于人工智能")
    private  String sfsyrgzn;
    @ExcelProperty("是否属于新业态")
    private  String sfsyxyt;
    @ColumnWidth(50)
    @ExcelProperty("推进部门")
    private String unitName;
    @ExcelProperty("填报人")
    private String userName;
    @ExcelProperty("联系电话")
    private String userMobilePhone;
    @ColumnWidth(50)
    @ExcelProperty("项目方联系人 ")
    private String bak2;
    @ColumnWidth(50)
    @ExcelProperty("项目方联系方式 ")
    private String bak3;


}
