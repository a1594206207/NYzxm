package com.nzxmmp.nzxm.DAO;

import com.nzxmmp.nzxm.entity.Ryqx;

import java.util.List;

/**
 * 人员权限DAO
 */
public interface RyqxDAO {
     /**
      * 通过账号获取人员权限实体类
      * @param zh
      * @return
      */
     public Ryqx selectByZh(String zh);
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
     public Integer delete(String zh);

     /**
      * 修改
      * @param ryqx
      * @return
      */
     public Integer update(Ryqx ryqx);


}
