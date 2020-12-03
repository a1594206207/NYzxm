package com.nzxmmp.nzxm.DAO.DAOImpl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.nzxmmp.nzxm.DAO.RyqxDAO;
import com.nzxmmp.nzxm.Mapper.RyqxMapping;
import com.nzxmmp.nzxm.entity.Ryqx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 人员权限DAO
 */
@Component("RyqxDAOImpl")
public class RyqxDAOImpl implements RyqxDAO {


    @Autowired
   private RyqxMapping ryqxMapping;

    /**
     * 通过账号获取人员权限实体类
     * @param zh
     * @return
     */
    @Override
    public Ryqx selectByZh(String zh) {
        QueryWrapper<Ryqx> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("zh",zh);
        Ryqx ryqx = ryqxMapping.selectOne(queryWrapper);

        return ryqx;
    }

    /**
     * 增加方法
     * @param ryqx
     * @return
     */
    @Override
    public String insert(Ryqx ryqx) {
        Ryqx ryqx1 = selectByZh(ryqx.getZh());
        if(ryqx1!=null){
            return "账号已经存在";
        }
        int insert = ryqxMapping.insert(ryqx);
        if(!(insert>0)){
            return "添加失败";

        }else{
            return  "添加成功";
        }
    }


    /**
     * 查询所有人员权限
     * @return
     */
    @Override
    public List<Ryqx> selectAll() {

        List<Ryqx> ryqxes = ryqxMapping.selectList(null);

        return ryqxes;
    }

    /**
     * 删除
     * @param zh
     * @return
     */
    @Override
    public Integer delete(String zh) {

        UpdateWrapper<Ryqx> updateWrapper=new UpdateWrapper<>();
         updateWrapper.eq("zh",zh);
        int delete = ryqxMapping.delete(updateWrapper);
        return delete;
    }
    /**
     * 修改
     * @param ryqx
     * @return
     */
    @Override
    public Integer update(Ryqx ryqx) {
        UpdateWrapper<Ryqx> ryqxUpdateWrapper = new UpdateWrapper<>();
        ryqxUpdateWrapper.eq("zh",ryqx.getZh());
        int update = ryqxMapping.update(ryqx, ryqxUpdateWrapper);

        return update;
    }


}
