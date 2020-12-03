package com.nzxmmp.nzxm.DAO.DAOImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nzxmmp.nzxm.DAO.RyqxDAO;
import com.nzxmmp.nzxm.DAO.YzxmDAO;
import com.nzxmmp.nzxm.Mapper.*;
import com.nzxmmp.nzxm.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 外资项目DAO
 */
@Repository("yzxmDAOImpl")
public class YzxmDAOImpl implements YzxmDAO {

    @Autowired
    private YzxmbdMapping yzxmbdMapping;
    @Autowired
    private YzxmSearchMapping searchMapping;
    @Autowired
    @Qualifier("RyqxDAOImpl")
    private RyqxDAO ryqxDAO;
    @Autowired
    private YzxmFindAllMapping yzxmFindAllMapping;


    /**
     * 查询所有
     * @param page
     * @return
     */
    @Override
    public List<Yzxmbd> SelectAll(Integer page) {
        QueryWrapper<Yzxmbd> queryWrapper=new QueryWrapper<>();

        queryWrapper.orderByDesc("Creattime").eq("deletestate","0");

        List<Yzxmbd> yzxmbds = yzxmbdMapping.selectList(queryWrapper);

        return yzxmbds;
    }
    /**
     * 按照需求字段导出
     * @param page
     * @return
     */
    @Override
    public IPage<YzFindAll> fandAll(Integer page, String zh) {

        IPage<YzFindAll> findAllPage=null;   //定义分页
        Ryqx ryqx = ryqxDAO.selectByZh(zh);
        if(ryqx==null){

            return findAllPage;
        }

        QueryWrapper<YzFindAll> queryWrapper=new QueryWrapper<YzFindAll>();

        queryWrapper.orderByDesc("Creattime").eq("deletestate","0")
                .select("id","state","xmmc","hydl","hyzl","hyxl","tzze","zczb","tzfjj","unit_name","user_name");

        if(ryqx.getQx()==2||ryqx.getQx()==3) {
            queryWrapper.eq("unit_name", ryqx.getUnit());
        }else if (ryqx.getQx()==1||ryqx.getQx()==4){

        }else {
            return findAllPage;
        }

        Page<YzFindAll> objectPage = new Page<>(page, 20);


        findAllPage= yzxmFindAllMapping.selectPage(objectPage,queryWrapper);


        return findAllPage;
    }

    /**
     * 显示单个
     * @param id
     * @return
     */
    @Override
    public Yzxmbd selectOne(String id) {
        Yzxmbd yzxmbd = yzxmbdMapping.selectById(id);
        return yzxmbd;
    }

    @Override
    public Yzxmbd selectToName(String xmmc) {

        QueryWrapper<Yzxmbd> queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("xmmc",xmmc).eq("deletestate","0");
        Yzxmbd yzxmbd = yzxmbdMapping.selectOne(queryWrapper);



        return yzxmbd;
    }

    /**
     * 修改
     * @param yzxmbd
     * @return
     */
    @Override
    public Integer update(Yzxmbd yzxmbd) {
        int result = yzxmbdMapping.updateById(yzxmbd);

        return result;
    }

    /**
     * 增加
     * @param yzxmbd
     * @return
     */
    @Override
    public Integer insert(Yzxmbd yzxmbd) {
        int result = yzxmbdMapping.insert(yzxmbd);

        return result;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Integer delete(String id) {

        int result = yzxmbdMapping.deleteById(id);
        return result;
    }

    /**
     * 搜索功能
     * @param yzxmSearch
     * @param page
     * @return
     */
    @Override
    public IPage<YzxmSearch> search(YzxmSearch yzxmSearch, Integer page) {

        IPage<YzxmSearch> searchyzxmbdPage1=null;
        Ryqx ryqx = ryqxDAO.selectByZh(yzxmSearch.getZh());//导入权限对象
        if(ryqx==null){

            return searchyzxmbdPage1;
        }

        QueryWrapper<YzxmSearch> queryWrapper=new QueryWrapper<>(yzxmSearch);
        //查询判断语句
        queryWrapper.eq("deletestate","0");
        if(!(yzxmSearch.getCreattime()==null)){
            queryWrapper.ge("Creattime",yzxmSearch.getCreattime());
        }
        if(!(yzxmSearch.getEndtime()==null)){
            queryWrapper.le("Creattime",yzxmSearch.getEndtime());
        }


        if(ryqx.getQx()==2||ryqx.getQx()==3) {   //权限判断
            queryWrapper.eq("unit_name", ryqx.getUnit());
        }else if (ryqx.getQx()==1||ryqx.getQx()==4){

        }else {
            return searchyzxmbdPage1;
        }

        Page<YzxmSearch> page1= new Page<>(page, 20);
        searchyzxmbdPage1 = searchMapping.selectPage(page1, queryWrapper);

        return searchyzxmbdPage1;

    }

    @Override
    public List<Yzxmbd> outSelectAll(OutSelectReq outSelectReq) {
        QueryWrapper<Yzxmbd> queryWrapper = new QueryWrapper<>();
        List<Yzxmbd> yzxmbds=null;


        queryWrapper.eq("deletestate","0");  //导出未被删除的
        if(outSelectReq.getOutTempleta().equals("天津高新区外资半年有望项目明细表")){   //根据相应的模板
            queryWrapper.eq("state","半年有望");
        }else if(outSelectReq.getOutTempleta().equals("天津高新区外资签约落地项目明细表")){
            queryWrapper.eq("state","签约落地");
        } else   {
            queryWrapper.eq("state","洽谈跟踪");
        }


        //权限方法
        Ryqx ryqx = ryqxDAO.selectByZh(outSelectReq.getZh());//导入权限对象
        if(ryqx==null){

            return yzxmbds;
        }  if(ryqx.getQx()==2||ryqx.getQx()==3) {   //权限判断
            queryWrapper.eq("unit_name", ryqx.getUnit());
        }else if (ryqx.getQx()==1||ryqx.getQx()==4){
            if (!(outSelectReq.getUnitName()==null)){
                queryWrapper.eq("unit_name",outSelectReq.getUnitName());
            }
        }else {
            return yzxmbds;
        }








        if (!(outSelectReq.getCreattime()==null)){
            queryWrapper.ge("Creattime",outSelectReq.getCreattime());
        }
        if (!(outSelectReq.getEndtime()==null)){
            queryWrapper.le("Creattime",outSelectReq.getEndtime());
        }

        yzxmbds = yzxmbdMapping.selectList(queryWrapper);



        return yzxmbds;
    }
}
