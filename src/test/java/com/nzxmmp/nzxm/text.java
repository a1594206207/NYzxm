package com.nzxmmp.nzxm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nzxmmp.nzxm.DAO.DAOImpl.before.nzxmDAO;
import com.nzxmmp.nzxm.Mapper.NzxmbdMapping;
import com.nzxmmp.nzxm.Mapper.OldnzxmbdMapper;
import com.nzxmmp.nzxm.Mapper.SearchMapping;
import com.nzxmmp.nzxm.entity.before.Oldnzxmbd;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.nzxmmp.nzxm.entity.*;

import java.util.List;


public class text extends  NzxmApplicationTests{
    @Test
    public void test(){

        List<Nzxmbd> nzxmbds = nzxmbdMapping.selectList(null);
        nzxmbds.forEach(System.out::println);

    }
    @Autowired
    private NzxmbdMapping nzxmbdMapping;

    /**
     * 查询所有并分页
     *
     */
    @Test
    void textSelectAll(){

        //传入值
        int page=2;

        QueryWrapper<Nzxmbd> queryWrapper=new QueryWrapper<Nzxmbd>();
       // queryWrapper.like("xmmc","球");
        Page<Nzxmbd> objectPage = new Page<>(page, 4);


        IPage<Nzxmbd> nzxmbdIPage = nzxmbdMapping.selectPage(objectPage,null);

        List<Nzxmbd> records = objectPage.getRecords();
        System.out.println("===========");
        System.out.println(nzxmbdIPage.toString());

    }


    /**
     *查询单个
     */
    @Test
    public void selectOne(){

        //传入值
        String xmmc="坐公交";

        QueryWrapper<Nzxmbd> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("xmmc",xmmc);
//        Nzxmbd nzxmbd = nzxmbdMapping.selectOne(queryWrapper);
          int id=8;
        Nzxmbd nzxmbd = nzxmbdMapping.selectById(id);

        System.out.println(nzxmbd.toString());


    }


    /**
     * 修改
     */
    @Test
    public void update(){


        //传入值
        Nzxmbd nzxmbd=new Nzxmbd();
        nzxmbd.setTzfmc("文峰店");
//        nzxmbd.setId(25);

        UpdateWrapper wrapper=new UpdateWrapper();

        int result = nzxmbdMapping.updateById(nzxmbd);

        System.out.println("影响记录数："+result);


    }

    /*
     *增加
     */
    @Test
    public void insert(){
        Nzxmbd nzxmbd=new Nzxmbd();
        nzxmbd.setXmmc("羽毛球");
        nzxmbd.setTzfmc("和平路");
        nzxmbd.setTzfszqy("1241sdads");
        nzxmbd.setTzfjj("sadoiuqwe");


        int result = nzxmbdMapping.insert(nzxmbd);
        System.out.println("影响记录数："+result);

    }

    /*
     *增加入oldnzxmbd表中
     */
    @Autowired
    OldnzxmbdMapper oldnzxmbdMapper;
    @Test
    public void insertOld(){
        Oldnzxmbd nzxmbd=new Oldnzxmbd();
        nzxmbd.setXmmc("羽毛球");
        nzxmbd.setTzfmc("和平路");
        nzxmbd.setTzfszqy("1241sdads");
        nzxmbd.setTzfjj("sadoiuqwe");


        int result = oldnzxmbdMapper.insert(nzxmbd);
        System.out.println("影响记录数："+result);


    }


    /**
     * 移除
     */
    @Test
    public void delete(){

        Nzxmbd nzxmbd=new Nzxmbd();
//        nzxmbd.setId(26);
        int result = nzxmbdMapping.deleteById(nzxmbd.getId());
        System.out.println("删除行数:"+result);

    }

    /**
     * 搜索
     */
    @Autowired
    SearchMapping searchMapping;
    @Test
    public  void search(){

        SearchNzxmbd searchNzxmbd=new SearchNzxmbd();
        searchNzxmbd.setXmmc("球");
        QueryWrapper<SearchNzxmbd> queryWrapper=new QueryWrapper<>(searchNzxmbd);

        int current=1;
        Page<SearchNzxmbd> page= new Page<>(current, 4);
        Page<SearchNzxmbd> searchNzxmbdPage1 = searchMapping.selectPage(page, queryWrapper);
        List<SearchNzxmbd> records = searchNzxmbdPage1.getRecords();
        records.forEach(System.out::println);

    }

    @Autowired
    @Qualifier("nzxmDAOImpl")
    private nzxmDAO nzxmDAO;
    @Test
    public void test32(){
        System.out.println("=======");

        Nzxmbd nzxmbd = nzxmDAO.selectOne(25);
        Oldnzxmbd oldnzxmbd=new Oldnzxmbd();
        BeanUtils.copyProperties(nzxmbd,oldnzxmbd);
        System.out.println(oldnzxmbd.toString());
//        Integer integer = nzxmDAO.insertOld(nzxmbd);
//        System.out.println(integer);
        System.out.println("========");
    }



}
