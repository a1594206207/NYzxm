package com.nzxmmp.nzxm.Service.ServiceImpl.before;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nzxmmp.nzxm.DAO.DAOImpl.before.nzxmDAO;
import com.nzxmmp.nzxm.entity.FindAll;
import com.nzxmmp.nzxm.entity.Nzxmbd;
import com.nzxmmp.nzxm.entity.before.Oldnzxmbd;
import com.nzxmmp.nzxm.entity.SearchNzxmbd;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service("NzxmServiceImpl")
public class NzxmServiceImpl implements NzxmService {


    @Autowired
    @Qualifier("nzxmDAOImpl")
    private nzxmDAO nzxmDAO;

    /**
     * 传入页数，查询所有
     * @param pageNum
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public IPage<Nzxmbd> pageFind(Integer pageNum) {

//        IPage<Nzxmbd> nzxmbdIPage = nzxmDAO.SelectAll(pageNum);

//        return nzxmbdIPage;
     return  null;
    }

    @Override
    public IPage<FindAll> pageFind2(Integer pageNum) {
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
     * 添加信息
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
                reson="：项目名以存在";
                throw new Exception();
            }
            Integer result = nzxmDAO.insert(nzxmbd);
            if (!(result>0)){

                throw new Exception();
            }


        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "添加失败"+reson;
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
        try{
            Integer result = nzxmDAO.update(nzxmbd);

            if (!(result>0)){
                throw new Exception();
            }

        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return  "false";
        }
        return "true";

    }

    /**
     * 通过id移除入回收站
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean move(Integer id) {

        try {
            Nzxmbd nzxmbd = nzxmDAO.selectOne(id);
            Oldnzxmbd oldnzxmbd = new Oldnzxmbd();
            BeanUtils.copyProperties(nzxmbd,oldnzxmbd);

//            System.out.println(nzxmbd.toString());

            Integer integer = nzxmDAO.insertOld(oldnzxmbd);
            if (!(integer>0)){
                throw new RuntimeException();
            }
            Integer delete = nzxmDAO.delete(id);
            if(!(delete>0)){
                throw new RuntimeException();
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
