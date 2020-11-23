package com.nzxmmp.nzxm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.nzxmmp.nzxm.Service.NzxmService;
import com.nzxmmp.nzxm.entity.Nzxmbd;
import com.nzxmmp.nzxm.entity.SearchNzxmbd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Nzxm")
public class NzxmController {

    @Autowired
    @Qualifier("NzxmServiceImpl")
    private NzxmService nzxmService;

    //分页查询所有
    @GetMapping("/Nzxmbds/{pageNum}")
    public IPage<Nzxmbd> findAll(@PathVariable("pageNum") Integer pageNum){

        IPage<Nzxmbd> nzxmbdIPage = nzxmService.pageFind(pageNum);
        return  nzxmbdIPage;

    }

    //通过项目名称查询单个
    @GetMapping("/Nzxmbd")
    public Nzxmbd findOne(Integer id){
        Nzxmbd one = nzxmService.findOne(id);
        return one;
    }

    //增添
    @PostMapping("/insert")
    public String insertOne(Nzxmbd nzxmbd){
        String insert = nzxmService.insert(nzxmbd);
        return insert;
    }

    //修改
    @PostMapping("/update")
    public  boolean updateOne(Nzxmbd nzxmbd){
        boolean update = nzxmService.update(nzxmbd);
        return update;
    }

    //项目移除
    @GetMapping("/move")
    public boolean moveOne(Integer id){

        boolean move = nzxmService.move(id);
        return move;
    }

    //搜索
    @GetMapping("/search")
    public IPage<SearchNzxmbd> search(SearchNzxmbd searchNzxmbd, Integer page){

        IPage<SearchNzxmbd> search = nzxmService.search(searchNzxmbd, page);
        return  search;
    }


}
