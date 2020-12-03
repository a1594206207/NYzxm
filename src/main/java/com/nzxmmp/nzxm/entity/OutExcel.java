package com.nzxmmp.nzxm.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 导出Excel文件的实体类
 */
@Data
@HeadRowHeight(40)
@ContentRowHeight(20)
@ColumnWidth(25)
public class OutExcel {


    @ExcelProperty("项目名称")
    @ColumnWidth(60)
    private String  xmmc;
    @ExcelProperty("行业大类")
    private String hydl;
    @ExcelProperty("行业中类")
    private String hyzl;
    @ExcelProperty("行业小类")
    private String hyxl;
    @ExcelProperty("投资方名称")
    @ColumnWidth(120)
    private String tzfmc;
    @ExcelProperty("投资总额（万元)")
    private String tzze;
    @ExcelProperty("企业类型")
    private String qylx;
    @ExcelProperty("省、直辖市、自治区")
    private String xmlydSheng;
    @ExcelProperty("市、自治州")
    private String xmlydShi;
    @ExcelProperty("项目类别")
    private String xmlb;
    @ExcelProperty("项目简介")
    @ColumnWidth(250)
    private String xmjj;
    @DateTimeFormat("yyyy/MM/dd")
    @ExcelProperty("拟落地时间")
    private Date nldsj;
    @ExcelProperty("是否500强")
    private String sfwbq;
    @ExcelProperty("是否企业总部")
    private String sfqyzb;
    @ExcelProperty("是否属于新业态")
    private String sfsyxyt;
    @ExcelProperty("是否属于人工智能")
    private String sfsyrgzn;
    @ExcelProperty("功能中心类型")
    private String sfgnxzx;
    @ExcelProperty("功能中心类型")
    private String gnzxlx;
    @ExcelProperty("招商进度")
    @ColumnWidth(120)
    private String zsjd;
    @ExcelProperty("用地需求（平方米）")
    private String ydxq;
    @ExcelProperty("厂库房需求（平方米）")
    private String ckfxq;
    @ExcelProperty("办公楼需求（平方米)")
    private String bglxq;
    @ExcelProperty("注册资本（万元)")
    private String zczb;
    @ExcelProperty("是否为已签约项目")
    private String sfwyqyxm;
    @ExcelProperty("签约项目所属活动")
    private String qyxmsshd;
    @ExcelProperty("是否已上报新区")
    private String sfybxq;
    @ExcelProperty("上报部门")
    private String unitName;
    @ExcelProperty("填报人")
    private String userName;
    @ExcelProperty("联系电话")
    private String userMobilePhone;
    @ExcelProperty("项目方联系人 ")
    private String bak1;
    @ExcelProperty("项目方联系方式")
    private String bak2;



}
