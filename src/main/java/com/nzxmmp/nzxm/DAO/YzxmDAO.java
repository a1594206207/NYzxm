package com.nzxmmp.nzxm.DAO;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nzxmmp.nzxm.entity.*;
import com.nzxmmp.nzxm.entity.before.Oldnzxmbd;

import java.util.List;
/**
 * 外资项目DAO
 */
public interface YzxmDAO {

    /**
     * 查询所有
     */
    public List<Yzxmbd> SelectAll(Integer page);

    public IPage<YzFindAll> fandAll(Integer page, String zh);

    /**
     *查询单个
     */
    public Yzxmbd selectOne(String id);

    public Yzxmbd selectToName(String xmmc);

    /**
     * 修改
     */
    public Integer update(Yzxmbd yzxmbd);

    /*
     *增加
     */
    public Integer insert(Yzxmbd yzxmbd);


    /**
     * 移除
     */
    public Integer delete(String id);

    /**
     * 搜索 strcmp strcpy
     */
    public  IPage<YzxmSearch> search(YzxmSearch yzxmSearch, Integer page);


    /**
     * 基于表单查询导出功能
     * @param outSelectReq
     * @param
     * @return
     */
    public List<Yzxmbd> outSelectAll(OutSelectReq outSelectReq);
}
