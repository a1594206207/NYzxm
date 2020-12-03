package com.nzxmmp.nzxm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 查看所有的实体类(外资项目表单）
 */

@Data
@TableName("yzxmbd")
public class YzFindAll {
    @TableId
    private String id;
    private String state;
    private  String xmmc;
    private  String hydl;
    private String hyzl;
    private String hyxl;
    private String tzze;
    private String zczb;  //注册资本
    private String tzfjj;
    private String unitName;
    private String userName;
}
