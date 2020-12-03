package com.nzxmmp.nzxm.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nzxmmp.nzxm.Service.NzxmqxService;
import com.nzxmmp.nzxm.Service.ServiceImpl.SeeState;
import com.nzxmmp.nzxm.Service.YzxmService;
import com.nzxmmp.nzxm.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
/**
 * 外资项目数据库操作接口
 */
@RestController
@RequestMapping("/Yzxmuser")
public class YzxmController {
    @Autowired
    @Qualifier("YzxmServiceImpl")
    private YzxmService yzxmService;
    @Autowired
    private SeeState seeState;

    /**
     * 分页查询所有，显示出相应字段
     * @param pageNum
     * @return
     */
    @GetMapping("/Yzxmbds/{pageNum}")
    @ResponseBody
    public IPage<YzFindAll> findAll(String zh, @PathVariable("pageNum") Integer pageNum){

        IPage<YzFindAll> findAllIPage = yzxmService.pageFind2(pageNum,zh);
        return  findAllIPage;

    }
    /**
     * 通过id查询单个
     * @param id
     * @return
     */
    @GetMapping("/Yzxmbd")
    @ResponseBody
    public Yzxmbd findOne(String id){
        Yzxmbd one = yzxmService.findOne(id);
        return one;
    }

    /**
     * 增加
     * @param yzxmbd
     * @return
     */
    @PostMapping("/insert")
    public String insertOne( Yzxmbd yzxmbd){
        String insert = yzxmService.insert(yzxmbd);
        return insert;
    }

    /**
     * 修改    xgr(修改人)xgyy(修改原因)不能为空   通过id修改
     * @param yzxmbd
     * @return
     */
    @PostMapping("/update")
    public  String updateOne(Yzxmbd yzxmbd){
        String result = yzxmService.update(yzxmbd);
        return result;
    }

    /**
     * 项目移除  scr(删除人) scyy(删除原因)不能为空 通过id删除
     * @param id
     * @return
     */
    @PostMapping("/move")
    public boolean moveOne(String id,String scr,String scyy){

        boolean move = yzxmService.move(id,scr,scyy);
        return move;
    }
    /**
     * 搜索
     * @param searchyzxmbd
     * @param page
     * @return
     */
    @GetMapping("/search/{pageNum}")
    @ResponseBody
    public IPage<YzxmSearch> search2(YzxmSearch searchyzxmbd, @PathVariable("pageNum") Integer page){

        IPage<YzxmSearch> search = yzxmService.search(searchyzxmbd, page);
        return  search;
    }
    /**
     * 领导查看页面的项目状态数量
     *
     */
    @GetMapping("/seestate")
    @ResponseBody
    public TheState seestate(){

        TheState state = seeState.YzState();
        return state;
    }



}
