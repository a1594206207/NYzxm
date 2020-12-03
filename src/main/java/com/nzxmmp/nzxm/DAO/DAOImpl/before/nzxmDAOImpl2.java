package com.nzxmmp.nzxm.DAO.DAOImpl.before;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nzxmmp.nzxm.Mapper.FindAllMapping;
import com.nzxmmp.nzxm.Mapper.NzxmbdMapping;
import com.nzxmmp.nzxm.Mapper.OldnzxmbdMapper;
import com.nzxmmp.nzxm.Mapper.SearchMapping;
import com.nzxmmp.nzxm.entity.*;
import com.nzxmmp.nzxm.entity.before.Oldnzxmbd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("nzxmDAOImpl2")
public class nzxmDAOImpl2 implements nzxmDAO {
    @Autowired
    private NzxmbdMapping nzxmbdMapping;
    @Autowired
    private OldnzxmbdMapper oldnzxmbdMapper;
    @Autowired
    private SearchMapping searchMapping;
    @Autowired
    private FindAllMapping findAllMapping;

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
    public IPage<FindAll> fandAll(Integer page) {
        QueryWrapper<FindAll> queryWrapper=new QueryWrapper<FindAll>();

        queryWrapper.orderByDesc("Creattime").eq("deletestate","0")
                .select("id","state","xmmc","hydl","hyzl","hyxl","tzfmc","xmlb","tzze","zczb","tzfjj","unit_name","user_name");

        Page<FindAll> objectPage = new Page<>(page, 20);


        Page<FindAll> findAllPage = findAllMapping.selectPage(objectPage, queryWrapper);

        return findAllPage;
    }



    /**
     * 显示单个
     * @param id
     * @return
     */
    @Override
    public Nzxmbd selectOne(Integer id) {

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
        int result = oldnzxmbdMapper.insert(oldnzxmbd);

        return result;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Integer delete(Integer id) {

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

        QueryWrapper<SearchNzxmbd> queryWrapper=new QueryWrapper<>(searchNzxmbd);
          //查询判断语句
        queryWrapper.eq("deletestate","0");
        if(!(searchNzxmbd.getCreattime()==null)){
            queryWrapper.ge("Creattime",searchNzxmbd.getCreattime());
        }
        if(!(searchNzxmbd.getEndtime()==null)){
            queryWrapper.le("Creattime",searchNzxmbd.getEndtime());
        }

        Page<SearchNzxmbd> page1= new Page<>(page, 20);
        IPage<SearchNzxmbd> searchNzxmbdPage1 = searchMapping.selectPage(page1, queryWrapper);

        return searchNzxmbdPage1;
    }

    @Override
    public List<Nzxmbd> outSelectAll(OutSelectReq outSelectReq) {

        QueryWrapper<Nzxmbd> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deletestate","0");  //导出未被删除的
        if(outSelectReq.getOutTempleta().equals("天津高新区内资半年有望项目明细表")){   //根据相应的模板
            queryWrapper.eq("state","半年有望");
        }else if(outSelectReq.getOutTempleta().equals("天津高新区内资签约落地项目明细表")){
            queryWrapper.eq("state","签约落地");
        } else   {
            queryWrapper.eq("state","洽谈跟踪");
        }

        if (!(outSelectReq.getUnitName()==null)){
            queryWrapper.eq("unit_name",outSelectReq.getUnitName());
        }


        if (!(outSelectReq.getCreattime()==null)){
            queryWrapper.ge("Creattime",outSelectReq.getCreattime());
        }
        if (!(outSelectReq.getEndtime()==null)){
            queryWrapper.le("Creattime",outSelectReq.getEndtime());
        }

        List<Nzxmbd> nzxmbds = nzxmbdMapping.selectList(queryWrapper);


        return nzxmbds;
    }

}
