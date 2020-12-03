package com.nzxmmp.nzxm.Service.ServiceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nzxmmp.nzxm.DAO.YzxmDAO;
import com.nzxmmp.nzxm.Service.YzxmService;
import com.nzxmmp.nzxm.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
/**
 * 外资项目增删改查业务类
 */
@Service("YzxmServiceImpl")
public class YzxmServiceImpl implements YzxmService {
    @Autowired
    @Qualifier("yzxmDAOImpl")
    private YzxmDAO yzxmDAO;


    @Deprecated  //声明过时
    @Override
    public IPage<Yzxmbd> pageFind(Integer pageNum) {
        return null;
    }
    /**
     * 传入页数，查询所有
     * @param pageNum
     * @return
     */
    @Override
    public IPage<YzFindAll> pageFind2(Integer pageNum, String zh) {
        IPage<YzFindAll> findAllIPage = yzxmDAO.fandAll(pageNum,zh);
        return findAllIPage;
    }

    /**
     * 通过id，查询单个
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Yzxmbd findOne(String id) {
        Yzxmbd yzxmbd = yzxmDAO.selectOne(id);
        return yzxmbd;
    }

    /**
     * 添加信息     Excelserviceimpl此方法被重写
     * @param yzxmbd
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insert(Yzxmbd yzxmbd) {
        String reson=null;
        try {

            Yzxmbd one = yzxmDAO.selectToName(yzxmbd.getXmmc());
            if(one!=null){
                reson="“"+yzxmbd.getXmmc()+"“"+"项目名以存在";
                throw new Exception();
            }
            //初始化创建时间和删除状态
            yzxmbd.setCreattime(new Date());
            yzxmbd.setDeletestate("0");

            Integer result = yzxmDAO.insert(yzxmbd);

            if (!(result>0)){
                throw new Exception();
            }


        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return reson;
        }
        return "添加成功";
    }

    /**
     * 通过id进行更新
     * @param yzxmbd
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String update(Yzxmbd yzxmbd) {
        String reason=null;
        try{
            yzxmbd.setUpdatetime(new Date());
            if(yzxmbd.getXgyy()==null){
                reason="修改原因未填写";
                throw  new RuntimeException();
            }
            Integer result = yzxmDAO.update(yzxmbd);

            if (!(result>0)){
                reason="修改失败";
                throw new Exception();
            }

        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return  reason;
        }
        return "修改成功";
    }


    /**
     * 通过id移除入回收站(已修改）
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean move(String id, String scr, String scyy) {

        try {
            Yzxmbd yzxmbd = yzxmDAO.selectOne(id);
            yzxmbd.setDeletestate("1");
            yzxmbd.setScr(scr);
            yzxmbd.setScyy(scyy);
            yzxmbd.setDeletetime(new Date());
            Integer update = yzxmDAO.update(yzxmbd);
            if(!(update>0)){
                throw  new RuntimeException();
            }


        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }

        return true;
    }

    /**
     * 搜索功能
     * @param searchyzxmbd
     * @param page
     * @return
     */
    @Override
    public IPage<YzxmSearch> search(YzxmSearch searchyzxmbd, Integer page) {
        IPage<YzxmSearch> search = yzxmDAO.search(searchyzxmbd, page);

        return search;
    }
}
