package com.nzxmmp.nzxm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nzxmmp.nzxm.Mapper.NzxmbdMapping;
import com.nzxmmp.nzxm.Service.NzxmService;
import com.nzxmmp.nzxm.entity.Nzxmbd;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class text2 extends NzxmApplicationTests{


    @Autowired
    @Qualifier("NzxmServiceImpl")
    private NzxmService nzxmService;
    @Autowired
    private NzxmbdMapping nzxmbdMapping;

    @Test
    public void testselectall(){

        IPage<Nzxmbd> nzxmbdIPage = nzxmService.pageFind(2);

        List<Nzxmbd> records = nzxmbdIPage.getRecords();
        records.forEach(System.out::println);

    }

    @Test
    public void insert(){
        Nzxmbd nzxmbd = new Nzxmbd();
        nzxmbd.setXmmc("打滴滴");
        nzxmbd.setTzfmc("和平路");

        String insert = nzxmService.insert(nzxmbd);
        System.out.println(insert);

    }

    @Test
    public void move(){
        boolean move = nzxmService.move(29);
        System.out.println(move);
    }
    @Test
    public void insert2(){
        Nzxmbd nzxmbd = new Nzxmbd();
        nzxmbd.setXmmc("沐恩");
        nzxmbd.setTzfmc("和平路");
        int insert = nzxmbdMapping.insert(nzxmbd);


        System.out.println(insert);
    }
}
