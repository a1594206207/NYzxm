package com.nzxmmp.nzxm.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nzxmmp.nzxm.entity.FindAll;
import com.nzxmmp.nzxm.entity.Nzxmbd;
import com.nzxmmp.nzxm.entity.SearchNzxmbd;
import org.springframework.stereotype.Repository;


public interface NzxmService {

    public IPage<Nzxmbd> pageFind(Integer pageNum);
    public IPage<FindAll> pageFind2(Integer pageNum);

    public Nzxmbd findOne(Integer id);

    public  String insert(Nzxmbd nzxmbd);

    public String update(Nzxmbd nzxmbd);

    /**
     * 将nzxmbd的移动到回收站
     * @param id
     * @return
     */
    public boolean move(Integer id);

    /**
     * 搜索功能
     * @param searchNzxmbd
     * @param page
     * @return
     */
    public IPage<SearchNzxmbd> search(SearchNzxmbd searchNzxmbd, Integer page);

}
