package com.nzxmmp.nzxm.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 外资项目表单实体类
 */
@Data
public class Yzxmbd {

  private String id;
  private String xmmc;
  private String wftzf;
  private String zftzf;
  private String tzgb;
  private String qtgb;
  private String tzfjj;
  private String xgsmc;
  private String tyshxydm;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JSONField(format = "yyyy/MM/dd")
  private java.util.Date ldsj;
  private String xmjj;
  private String qylx;
  private String hydl;
  private String hyzl;
  private String hyxl;
  private String ldlb;
  private String xmly;
  private String sfwbq;
  private String sfqyzb;
  private String sfwyfzx;
  private String sfsyrgzn;
  private String sfsyxyt;
  private String sfwyqyxm;
  private String qyxmsshd;
  private String sfysbxq;
  private String hjxy;
  private String yd;
  private String ckf;
  private String bgl;
  private String tzze;
  private String zczb;
  private String zczbdw;
  private String htwze;
  private String nnszev;
  private String nyysr;
  private String njck;
  private String gdzctz;
  private String ddjy;
  private String unitName;
  private String userName;
  private String userMobilePhone;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JSONField(format = "yyyy/MM/dd")
  private java.util.Date creattime;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JSONField(format = "yyyy/MM/dd")
  private java.util.Date updatetime;
  private String state;
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
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JSONField(format = "yyyy/MM/dd")
  private java.util.Date nldsj;
  private String xgr;
  private String xgyy;
  private String scr;
  private String scyy;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JSONField(format = "yyyy/MM/dd")
  private java.util.Date deletetime;
  private String bz;



}
