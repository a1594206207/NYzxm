package com.nzxmmp.nzxm.DAO.DAOImpl.before;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nzxmmp.nzxm.entity.*;
import com.nzxmmp.nzxm.entity.before.Oldnzxmbd;

import java.util.List;

public interface nzxmDAO {

    /**
     * 查询所有
     */
    public List<Nzxmbd> SelectAll(Integer page);

    public IPage<FindAll> fandAll(Integer page);

    /**
     *查询单个
     */
    public Nzxmbd selectOne(Integer id);

    public Nzxmbd selectToName(String xmmc);

    /**
     * 修改
     */
    public Integer update(Nzxmbd nzxmbd);

    /*
     *增加
     */
    public Integer insert(Nzxmbd nzxmbd);

    /*
     *增加入oldnzxmbd表中
     */
    public Integer insertOld(Oldnzxmbd oldNzxmbd);

    /**
     * 移除
     */
    public Integer delete(Integer id);

    /**
     * 搜索
     */
    public  IPage<SearchNzxmbd> search(SearchNzxmbd searchNzxmbd,Integer page);


    /**
     * 基于表单查询导出功能
     * @param outSelectReq
     * @param
     * @return
     */
    public List<Nzxmbd> outSelectAll(OutSelectReq outSelectReq);
}
