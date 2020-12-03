package com.nzxmmp.nzxm.entity;

import lombok.Data;

/**
 * 领导查看各状态的实体类
 */
@Data
public class TheState {

    //洽谈跟踪
    private Integer qtgz;
    //半年有望
    private Integer bnyw;
    //签约落地
    private Integer qyld;
}
