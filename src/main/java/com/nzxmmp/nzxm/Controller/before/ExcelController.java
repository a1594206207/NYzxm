package com.nzxmmp.nzxm.Controller.before;


import com.nzxmmp.nzxm.Service.ExcelServic;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;


@RestController
@RequestMapping("/excelx")
public class ExcelController {

    @Autowired
    @Qualifier("ExcelSericeImpl")
    private ExcelServic excelService;
    //模板的下载
    @GetMapping("/downloadTemplatex")
    public void downloadTemplate(HttpServletResponse response) throws Exception {
        //

        Workbook workbook = excelService.outTemplate();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode("内资项目模板.xlsx", "utf-8"));
        response.setHeader("Access-Control-Expose-Headers", "content-Disposition");
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

//

    @GetMapping("/downloadAllx")
    public void downloadAll(HttpServletResponse response) throws Exception{


        Workbook workbook = excelService.outAll();


        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode("内资项目总表单.xlsx", "utf-8"));
        response.setHeader("Access-Control-Expose-Headers", "content-Disposition");
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();


    }
    @PostMapping("/insertExcel")
    public String readExcel(@RequestParam("file") MultipartFile file){

        String s = excelService.excelAddMysql(file);
        return s;

    }

}
