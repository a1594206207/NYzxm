package com.nzxmmp.nzxm.Controller.before;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.nzxmmp.nzxm.Service.ServiceImpl.before.NzxmService;
import com.nzxmmp.nzxm.entity.FindAll;
import com.nzxmmp.nzxm.entity.Nzxmbd;
import com.nzxmmp.nzxm.entity.SearchNzxmbd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Nzxm")
public class NzxmController {

    @Autowired
    @Qualifier("NzxmServiceImpl2")
    private NzxmService nzxmService;



    /**
     * 分页查询所有
     * @param pageNum
     * @return
     */
    @GetMapping("/Nzxmbds1/{pageNum}")
    @ResponseBody
    public IPage<Nzxmbd> findAll1(@PathVariable("pageNum") Integer pageNum){

        IPage<Nzxmbd> nzxmbdIPage = nzxmService.pageFind(pageNum);
        return  nzxmbdIPage;

    }

    /**
     * 分页查询所有，显示出相应字段
     * @param pageNum
     * @return
     */
    @GetMapping("/Nzxmbds/{pageNum}")
    @ResponseBody
    public IPage<FindAll> findAll(@PathVariable("pageNum") Integer pageNum){

        IPage<FindAll> findAllIPage = nzxmService.pageFind2(pageNum);
        return  findAllIPage;

    }

    /**
     * 通过id查询单个
     * @param id
     * @return
     */
    @GetMapping("/Nzxmbd")
    @ResponseBody
    public Nzxmbd findOne(Integer id){
        Nzxmbd one = nzxmService.findOne(id);
        return one;
    }

    /**
     * 增加
     * @param nzxmbd
     * @return
     */
    @PostMapping("/insert")
    public String insertOne( Nzxmbd nzxmbd){
        String insert = nzxmService.insert(nzxmbd);
        return insert;
    }

    /**
     * 修改    xgr(修改人)xgyy(修改原因)不能为空
     * @param nzxmbd
     * @return
     */
    @PostMapping("/update")
    public  String updateOne(Nzxmbd nzxmbd){
        String result = nzxmService.update(nzxmbd);
        return result;
    }

    /**
     * 项目移除  scr(删除人)不能为空
     * @param id
     * @return
     */
    @GetMapping("/move")
    public boolean moveOne(Integer id){

        boolean move = nzxmService.move(id);
        return move;
    }

    /**
     * 搜索
     * @param searchNzxmbd
     * @param page
     * @return
     */
    @GetMapping("/searchx")
    @ResponseBody
    public IPage<SearchNzxmbd> search( SearchNzxmbd searchNzxmbd,Integer page){

        IPage<SearchNzxmbd> search = nzxmService.search(searchNzxmbd, page);
        return  search;
    }

    @GetMapping("/search/{pageNum}")
    @ResponseBody
    public IPage<SearchNzxmbd> search2( SearchNzxmbd searchNzxmbd,@PathVariable("pageNum") Integer page){

        IPage<SearchNzxmbd> search = nzxmService.search(searchNzxmbd, page);
        return  search;
    }

}
