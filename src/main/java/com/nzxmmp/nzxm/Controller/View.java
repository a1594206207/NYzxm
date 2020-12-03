package com.nzxmmp.nzxm.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class View {
    @RequestMapping(value = "/sea",method = RequestMethod.GET)
    public String seach(){
        return "seach";
    }

   @RequestMapping(value = "/index",method = RequestMethod.GET)
   public String Index(){
    return "index";
   }

   @RequestMapping(value = "/outandinput",method = RequestMethod.GET)
    public String outandInput(){ return "outAndInput"; }

    @RequestMapping(value = "/outYz",method = RequestMethod.GET)
    public String YzoutandInput(){ return "yzOutAndInput"; }

    @RequestMapping(value = "/insert" ,method = RequestMethod.GET)
    public String Insert(){return "insert";}
    @RequestMapping(value = "/update")
    public  String Updata(){return "update";}

}
