package com.nzxmmp.nzxm.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nzxmmp.nzxm.entity.*;
/**
 * 外资项目操作数据库的接口
 */
public interface YzxmService {
    public IPage<Yzxmbd> pageFind(Integer pageNum);
    public IPage<YzFindAll> pageFind2(Integer pageNum, String zh);

    public Yzxmbd findOne(String id);

    public  String insert(Yzxmbd yzxmbd);

    public String update(Yzxmbd yzxmbd);

    /**
     * 将nzxmbd的移动到回收站
     * @param id
     * @return
     */
    public boolean move(String id,String scr,String scyy);

    /**
     * 搜索功能
     * @param searchyzxmbd
     * @param page
     * @return
     */
    public IPage<YzxmSearch> search(YzxmSearch searchyzxmbd, Integer page);
}
