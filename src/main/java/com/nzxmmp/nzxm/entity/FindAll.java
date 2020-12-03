package com.nzxmmp.nzxm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 查看所有的实体类
 */

@Data
@TableName("nzxmbd")
public class FindAll {
    @TableId
    private String id;
    private String state;
    private  String xmmc;
    private  String hydl;
    private String hyzl;
    private String hyxl;
    private String tzfmc;
    private String xmlb;
    private String tzze;
    private String zczb;
    private String tzfjj;
    private String unitName;
    private String userName;
}
