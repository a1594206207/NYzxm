package com.nzxmmp.nzxm.Service;

import com.nzxmmp.nzxm.entity.Ryqx;

import java.util.List;

public interface RyqxService {

    /**
     * 增加方法
     * @param ryqx
     * @return
     */
    public String insert(Ryqx ryqx);

    /**
     * 查询所有人员权限
     * @return
     */
    public List<Ryqx> selectAll();

    /**
     * 删除
     * @param zh
     * @return
     */
    public String delete(String zh);

    /**
     * 修改
     * @param ryqx
     * @return
     */
    public String update(Ryqx ryqx);


}
