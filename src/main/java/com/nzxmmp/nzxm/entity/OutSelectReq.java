package com.nzxmmp.nzxm.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 导出类型
 */
@Data
public class OutSelectReq {

    //起始年月

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy/MM/dd")
    private java.util.Date Creattime ;

    //终止年月
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy/MM/dd")
    private Date Endtime;

    private String unitName;
    private String outTempleta;
    private String zh;//账号，权限功能
}
