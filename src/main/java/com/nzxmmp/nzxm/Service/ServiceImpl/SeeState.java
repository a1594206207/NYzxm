package com.nzxmmp.nzxm.Service.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nzxmmp.nzxm.Mapper.NzxmbdMapping;
import com.nzxmmp.nzxm.Mapper.YzxmbdMapping;
import com.nzxmmp.nzxm.entity.TheState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内外资领导查看导出个数据实现
 */
@Service
public class SeeState {

    @Autowired
    private NzxmbdMapping nzxmbdMapping;

    @Autowired
    private YzxmbdMapping yzxmbdMapping;


    /**
     * 内资领导查看
     * @return
     */
    public TheState NzState(){

        TheState theState = new TheState();

        //获取洽谈跟踪的数量
        QueryWrapper queryQtgz = new QueryWrapper();
        queryQtgz.eq("state","洽谈跟踪");
        List qtgz = nzxmbdMapping.selectList(queryQtgz);
        theState.setQtgz(qtgz.size());


        //获取半年有望的数量
        QueryWrapper queryBnyw=new QueryWrapper();
        queryBnyw.eq("state","半年有望");
        List bnyw = nzxmbdMapping.selectList(queryBnyw);
        theState.setBnyw(bnyw.size());

        //获取终止项目的数量
        QueryWrapper queryLdqy=new QueryWrapper();
        queryLdqy.eq("state","签约落地");
        List ldqy = nzxmbdMapping.selectList(queryLdqy);
        theState.setQyld(ldqy.size());


        return theState;

    }


    /**
     * 外资领导查看
     * @return
     */
   public TheState YzState(){

       TheState theState = new TheState();

       //获取洽谈跟踪的数量
       QueryWrapper queryQtgz = new QueryWrapper();
       queryQtgz.eq("state","洽谈跟踪");
       List qtgz = yzxmbdMapping.selectList(queryQtgz);
       theState.setQtgz(qtgz.size());


       //获取半年有望的数量
       QueryWrapper queryBnyw=new QueryWrapper();
       queryBnyw.eq("state","半年有望");
       List bnyw = yzxmbdMapping.selectList(queryBnyw);
       theState.setBnyw(bnyw.size());

       //获取终止项目的数量
       QueryWrapper queryLdqy=new QueryWrapper();
       queryLdqy.eq("state","签约落地");
       List ldqy = yzxmbdMapping.selectList(queryLdqy);
       theState.setQyld(ldqy.size());


       return theState;

   }


}
