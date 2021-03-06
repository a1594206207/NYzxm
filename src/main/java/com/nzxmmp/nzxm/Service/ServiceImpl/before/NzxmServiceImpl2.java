package com.nzxmmp.nzxm.Service.ServiceImpl.before;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nzxmmp.nzxm.entity.FindAll;
import com.nzxmmp.nzxm.entity.Nzxmbd;
import com.nzxmmp.nzxm.entity.SearchNzxmbd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;

@Service("NzxmServiceImpl2")
public class NzxmServiceImpl2 implements NzxmService {
    @Autowired
    @Qualifier("nzxmDAOImpl2")
    private com.nzxmmp.nzxm.DAO.DAOImpl.before.nzxmDAO nzxmDAO;

    /**
     * 传入页数，查询所有
     * @param pageNum
     * @return
     */
    @Override
    public IPage<FindAll> pageFind2(Integer pageNum) {
        IPage<FindAll> findAllIPage = nzxmDAO.fandAll(pageNum);
        return findAllIPage;
    }
    @Override
    @Transactional(readOnly = true)
    public IPage<Nzxmbd> pageFind(Integer pageNum) {

//        IPage<Nzxmbd> nzxmbdIPage = nzxmDAO.SelectAll(pageNum);

//        return nzxmbdIPage;
        return null;
    }

    /**
     * 通过id，查询单个
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Nzxmbd findOne(Integer id) {
        Nzxmbd nzxmbd = nzxmDAO.selectOne(id);
        return nzxmbd;
    }

    /**
     * 添加信息     Excelserviceimpl此方法被重写
     * @param nzxmbd
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insert(Nzxmbd nzxmbd) {

        String reson=null;
        try {

            Nzxmbd one = nzxmDAO.selectToName(nzxmbd.getXmmc());
            if(one!=null){
                reson="“"+nzxmbd.getXmmc()+"“"+"项目名以存在";
                throw new Exception();
            }
            //初始化创建时间和删除状态
            nzxmbd.setCreattime(new Date());
            nzxmbd.setDeletestate("0");

            Integer result = nzxmDAO.insert(nzxmbd);

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
     * @param nzxmbd
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String update(Nzxmbd nzxmbd) {
        String reason=null;
        try{
            nzxmbd.setUpdatetime(new Date());
            if(nzxmbd.getXgyy()==null){
                reason="修改原因未填写";
                throw  new RuntimeException();
            }
            Integer result = nzxmDAO.update(nzxmbd);

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
    public boolean move(Integer id) {

        try {
            Nzxmbd nzxmbd = nzxmDAO.selectOne(id);
            nzxmbd.setDeletestate("1");
            Integer update = nzxmDAO.update(nzxmbd);
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
     * @param searchNzxmbd
     * @param page
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public IPage<SearchNzxmbd> search(SearchNzxmbd searchNzxmbd, Integer page) {

        IPage<SearchNzxmbd> search = nzxmDAO.search(searchNzxmbd, page);

        return search;
    }
}
