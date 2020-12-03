package com.nzxmmp.nzxm.Service.ServiceImpl;

import com.nzxmmp.nzxm.DAO.RyqxDAO;
import com.nzxmmp.nzxm.Service.RyqxService;
import com.nzxmmp.nzxm.entity.Ryqx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人员权限业务层
 */
@Service("ryqxServiceImpl")
public class RyqxServiceImpl implements RyqxService {

    @Autowired
    @Qualifier("RyqxDAOImpl")
    private RyqxDAO ryqxDAO;

    /**
     * 增加方法
     * @param ryqx
     * @return
     */
    @Override
    public String insert(Ryqx ryqx) {
        String insert = ryqxDAO.insert(ryqx);

        return insert;
    }
    /**
     * 查询所有人员权限
     * @return
     */
    @Override
    public List<Ryqx> selectAll() {

        List<Ryqx> ryqxes = ryqxDAO.selectAll();
        return ryqxes;
    }
    /**
     * 删除
     * @param zh
     * @return
     */
    @Override
    public String  delete(String zh) {

        Integer delete = ryqxDAO.delete(zh);
        if (!(delete > 0)) {

            return "删除失败";
        }
        return "删除成功";
    }
    /**
     * 修改
     * @param ryqx
     * @return
     */
    @Override
    public String  update(Ryqx ryqx) {

        Integer update = ryqxDAO.update(ryqx);
        if(!(update>0)){

            return "修改失败";
        }

        return "修改成功";
    }
}
