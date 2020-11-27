package com.nzxmmp.nzxm.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 内资项目总实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nzxmbd {

  @TableId(type= IdType.AUTO)
  private Integer id;
  private String xmmc;
  private String tzfmc;
  private String tzfszqy;
  private String tzfjj;
  private String xgsmc;
  private String tyxydm;
  private String qylx;
  @JSONField(format = "yyyy/MM/dd")
  private java.util.Date ldsj;
  private String xmjj;
  private String hydl;
  private String hyzl;
  private String hyxl;
  private String xmlb;
  private String xmly;
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
  private String tzze;
  private String zczb;
  private String dysjdwje;
  private String nnsze;
  private String nyysr;
  private String njck;
  private String gdzctz;
  private String ddjy;
  private String userName;
  private String userMobilePhone;
  private String bz;
  private String unitName;
  private String xmlydSheng;
  private String xmlydShi;
  @JSONField(format = "yyyy/MM/dd")
  private java.util.Date nldsj;
  private String zsjd;
  @JSONField(format = "yyyy/MM/dd")
  private java.util.Date creattime;
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
  private String xgr;
  private String xgyy;
  private String scr;



}
