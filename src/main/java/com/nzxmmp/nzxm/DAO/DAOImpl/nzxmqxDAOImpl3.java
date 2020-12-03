package com.nzxmmp.nzxm.DAO.DAOImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nzxmmp.nzxm.DAO.RyqxDAO;
import com.nzxmmp.nzxm.DAO.nzxmqxDAO;
import com.nzxmmp.nzxm.Mapper.*;
import com.nzxmmp.nzxm.entity.*;
import com.nzxmmp.nzxm.entity.before.Oldnzxmbd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 内资项目DAO
 */
@Component("nzxmqxDAOImpl3")
public class nzxmqxDAOImpl3 implements nzxmqxDAO {
    @Autowired
    private NzxmbdMapping nzxmbdMapping;
    @Autowired
    private SearchMapping searchMapping;
    @Autowired
    private FindAllMapping findAllMapping;
    @Autowired
    @Qualifier("RyqxDAOImpl")
    private RyqxDAO ryqxDAO;

    /**
     * 查询所有
     * @param page
     * @return
     */
    @Override
    public List<Nzxmbd> SelectAll(Integer page) {
        QueryWrapper<Nzxmbd> queryWrapper=new QueryWrapper<Nzxmbd>();

        queryWrapper.orderByDesc("Creattime").eq("deletestate","0");

        List<Nzxmbd> nzxmbds = nzxmbdMapping.selectList(queryWrapper);

        return nzxmbds;
    }

    /**
     * 按照需求字段导出
     * @param page
     * @return
     */
    @Override
    public IPage<FindAll> fandAll(Integer page,String zh) {

        Page<FindAll> findAllPage=null;   //定义分页
        Ryqx ryqx = ryqxDAO.selectByZh(zh);
        if(ryqx==null){

            return findAllPage;
        }

        QueryWrapper<FindAll> queryWrapper=new QueryWrapper<FindAll>();

        queryWrapper.orderByDesc("Creattime").eq("deletestate","0")
                .select("id","state","xmmc","hydl","hyzl","hyxl","tzfmc","xmlb","tzze","zczb","tzfjj","unit_name","user_name");

        if(ryqx.getQx()==2||ryqx.getQx()==3) {
            queryWrapper.eq("unit_name", ryqx.getUnit());
        }else if (ryqx.getQx()==1||ryqx.getQx()==4){

        }else {
            return findAllPage;
        }

        Page<FindAll> objectPage = new Page<>(page, 20);


        findAllPage = findAllMapping.selectPage(objectPage, queryWrapper);

        return findAllPage;
    }



    /**
     * 显示单个
     * @param id
     * @return
     */
    @Override
    public Nzxmbd selectOne(String id) {

        Nzxmbd nzxmbd = nzxmbdMapping.selectById(id);
        return nzxmbd;
    }

    @Override
    public Nzxmbd selectToName(String xmmc) {

        QueryWrapper<Nzxmbd> queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("xmmc",xmmc).eq("deletestate","0");
        Nzxmbd nzxmbd = nzxmbdMapping.selectOne(queryWrapper);



        return nzxmbd;
    }

    /**
     * 修改
     * @param nzxmbd
     * @return
     */
    @Override
    public Integer update(Nzxmbd nzxmbd) {

        int result = nzxmbdMapping.updateById(nzxmbd);

        return result;
    }

    /**
     * 增加
     * @param nzxmbd
     * @return
     */
    @Override
    public Integer insert(Nzxmbd nzxmbd) {

        int result = nzxmbdMapping.insert(nzxmbd);

        return result;
    }

    /**
     * 增加进回收站
     * @param oldnzxmbd
     * @return
     */
    @Override
    public Integer insertOld(Oldnzxmbd oldnzxmbd) {
//        int result = oldnzxmbdMapper.insert(oldnzxmbd);

        return null;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Integer delete(String id) {

        int result = nzxmbdMapping.deleteById(id);
        return result;
    }

    /**
     * 搜索功能
     * @param searchNzxmbd
     * @return
     */
    @Override
    public IPage<SearchNzxmbd> search(SearchNzxmbd searchNzxmbd, Integer page) {
        IPage<SearchNzxmbd> searchNzxmbdPage1=null;
        Ryqx ryqx = ryqxDAO.selectByZh(searchNzxmbd.getZh());//导入权限对象
        if(ryqx==null){

            return searchNzxmbdPage1;
        }

        QueryWrapper<SearchNzxmbd> queryWrapper=new QueryWrapper<>(searchNzxmbd);
        //查询判断语句
        queryWrapper.eq("deletestate","0");
        if(!(searchNzxmbd.getCreattime()==null)){
            queryWrapper.ge("Creattime",searchNzxmbd.getCreattime());
        }
        if(!(searchNzxmbd.getEndtime()==null)){
            queryWrapper.le("Creattime",searchNzxmbd.getEndtime());
        }


        if(ryqx.getQx()==2||ryqx.getQx()==3) {   //权限判断
            queryWrapper.eq("unit_name", ryqx.getUnit());
        }else if (ryqx.getQx()==1||ryqx.getQx()==4){

        }else {
            return searchNzxmbdPage1;
        }

        Page<SearchNzxmbd> page1= new Page<>(page, 20);
        searchNzxmbdPage1 = searchMapping.selectPage(page1, queryWrapper);

        return searchNzxmbdPage1;
    }

    @Override
    public List<Nzxmbd> outSelectAll(OutSelectReq outSelectReq) {

        QueryWrapper<Nzxmbd> queryWrapper = new QueryWrapper<>();
        List<Nzxmbd> nzxmbds=null;


        queryWrapper.eq("deletestate","0");  //导出未被删除的
        if(outSelectReq.getOutTempleta().equals("天津高新区内资半年有望项目明细表")){   //根据相应的模板
            queryWrapper.eq("state","半年有望");
        }else if(outSelectReq.getOutTempleta().equals("天津高新区内资签约落地项目明细表")){
            queryWrapper.eq("state","签约落地");
        } else   {
            queryWrapper.eq("state","洽谈跟踪");
        }


        //权限方法
        Ryqx ryqx = ryqxDAO.selectByZh(outSelectReq.getZh());//导入权限对象
        if(ryqx==null){

            return nzxmbds;
        }  if(ryqx.getQx()==2||ryqx.getQx()==3) {   //权限判断
            queryWrapper.eq("unit_name", ryqx.getUnit());
        }else if (ryqx.getQx()==1||ryqx.getQx()==4){
            if (!(outSelectReq.getUnitName()==null)){
                queryWrapper.eq("unit_name",outSelectReq.getUnitName());
            }
        }else {
            return nzxmbds;
        }








        if (!(outSelectReq.getCreattime()==null)){
            queryWrapper.ge("Creattime",outSelectReq.getCreattime());
        }
        if (!(outSelectReq.getEndtime()==null)){
            queryWrapper.le("Creattime",outSelectReq.getEndtime());
        }

         nzxmbds = nzxmbdMapping.selectList(queryWrapper);



        return nzxmbds;
    }

}