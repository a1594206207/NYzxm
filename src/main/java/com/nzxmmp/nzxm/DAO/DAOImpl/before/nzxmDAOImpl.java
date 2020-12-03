package com.nzxmmp.nzxm.DAO.DAOImpl.before;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nzxmmp.nzxm.Mapper.NzxmbdMapping;
import com.nzxmmp.nzxm.Mapper.OldnzxmbdMapper;
import com.nzxmmp.nzxm.Mapper.SearchMapping;
import com.nzxmmp.nzxm.entity.*;
import com.nzxmmp.nzxm.entity.before.Oldnzxmbd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("nzxmDAOImpl")
public class nzxmDAOImpl implements nzxmDAO {

    @Autowired
    private NzxmbdMapping nzxmbdMapping;
    @Autowired
    private OldnzxmbdMapper oldnzxmbdMapper;
    @Autowired
    private SearchMapping searchMapping;

    /**
     * 分页查询所有
     * @param page
     * @return
     */
    @Override
    public List<Nzxmbd> SelectAll(Integer page) {
        QueryWrapper<Nzxmbd> queryWrapper=new QueryWrapper<Nzxmbd>();

        Page<Nzxmbd> objectPage = new Page<>(page, 4);


        List<Nzxmbd> nzxmbds = nzxmbdMapping.selectList(null);

        return nzxmbds;
    }

    @Override
    public IPage<FindAll> fandAll(Integer page) {
        return null;
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
        queryWrapper.eq("xmmc",xmmc);
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
    public IPage<SearchNzxmbd> search(SearchNzxmbd searchNzxmbd,Integer page) {

        QueryWrapper<SearchNzxmbd> queryWrapper=new QueryWrapper<>(searchNzxmbd);
        Page<SearchNzxmbd> page1= new Page<>(page, 4);
        IPage<SearchNzxmbd> searchNzxmbdPage1 = searchMapping.selectPage(page1, queryWrapper);

        return searchNzxmbdPage1;
    }

    @Override
    public List<Nzxmbd> outSelectAll(OutSelectReq outSelectReq) {
        return null;
    }


}
