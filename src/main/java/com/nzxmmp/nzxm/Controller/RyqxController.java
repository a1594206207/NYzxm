package com.nzxmmp.nzxm.Controller;

import com.nzxmmp.nzxm.Service.RyqxService;
import com.nzxmmp.nzxm.entity.Ryqx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ryqx")
public class RyqxController {

    @Autowired
    @Qualifier("ryqxServiceImpl")
    private RyqxService ryqxService;

    @GetMapping("/Ryqxs")
    @ResponseBody
    public List<Ryqx> selectAll(){
       return ryqxService.selectAll();
    }

    @PostMapping("/insert")
    @ResponseBody
    public String insert(Ryqx ryqx){
        return ryqxService.insert(ryqx);
    }

    @GetMapping("/delete")
    @ResponseBody
    public String delete(String zh){
        return ryqxService.delete(zh);
    }

    @PostMapping("/update")
    @ResponseBody
    public String update(Ryqx ryqx){
        return ryqxService.update(ryqx);
    }
}
