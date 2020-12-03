package com.nzxmmp.nzxm.Controller;

import com.nzxmmp.nzxm.Service.ExcelServic;
import com.nzxmmp.nzxm.Service.OutSelectService;
import com.nzxmmp.nzxm.Service.ServiceImpl.ExcelInsert;
import com.nzxmmp.nzxm.Service.ServiceImpl.YzxmExcelInsert;
import com.nzxmmp.nzxm.entity.OutSelectReq;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * 外资操作excel表接口
 */
@RestController
@RequestMapping("/excelYzxm")
public class YzxmExcelController {
    @Autowired
    @Qualifier("YzxmExcelServiceZtxmlmpl")
    private ExcelServic excelServiceZ;

    @Autowired
    @Qualifier("YzxmExcelServiceBnywImpl")
    private  ExcelServic excelServiceB;

    @Autowired
    @Qualifier("YzxmExcelServiceQyldImpl")
    private  ExcelServic excelServiceQ;

    @Autowired
    private YzxmExcelInsert excelInsert;

    @Autowired
    @Qualifier("outSelectServiceImpl2")
    private OutSelectService outSelectService;





    /**
     * 通过传入的state属性获取需要导出的模板
     * @param response
     * @param request
     * @throws Exception
     */
    @GetMapping("/downTemplate")
    public void downloadTemplate(HttpServletResponse response, HttpServletRequest request) throws Exception {
        //
        String state = request.getParameter("state");
        Workbook workbook=null;
        if (state.equals("外资半年有望模板")) {
            workbook = excelServiceB.outTemplate();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode("天津高新区外资半年有望项目明细表导入模板.xlsx", "UTF8"));

        } else if (state.equals("外资签约落地模板")) {
            workbook = excelServiceQ.outTemplate();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode("天津高新区外资签约落地项目明细表导入模板.xlsx", "UTF8"));

        } else {
            workbook = excelServiceZ.outTemplate();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode("天津高新区内资在谈项目明细表导入模板.xlsx", "UTF8"));

        }
        response.setHeader("Access-Control-Expose-Headers", "content-Disposition");
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();

    }

    /**
     * 导入文件加入数据库
     * @param file
     * @return
     */
    @PostMapping("/insertExcel")
    public String readExcel(@RequestParam("file") MultipartFile file){

        String s = excelInsert.compare(file);
        return s;

    }

    /**
     * 导出所有数据  (无用)
     * @param outSelectReq
     * @param response
     * @throws IOException
     */
    @PostMapping("/select")
    public void outSelect(OutSelectReq outSelectReq, HttpServletResponse response) throws IOException {
        Workbook workbook = outSelectService.outputSelect(outSelectReq);
        response.setContentType("application/vnd.ms-excel");
        if(outSelectReq.getOutTempleta().equals("天津高新区内资半年有望项目明细表")){

            response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode("天津高新区内资半年有望项目明细表.xlsx", "UTF8"));

        }else if (outSelectReq.getOutTempleta().equals("天津高新区内资签约落地项目明细表")){

            response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode("天津高新区内资签约落地项目明细表.xlsx", "UTF8"));

        }else {

            response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode("天津高新区内资在谈项目明细表.xlsx", "UTF8"));

        }

        response.setHeader("Access-Control-Expose-Headers", "content-Disposition");
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 导出所有数据
     * @param outSelectReq
     * @param response
     * @throws IOException
     */
    @PostMapping("/outYz")
    public void outNz(OutSelectReq outSelectReq,HttpServletResponse response) throws IOException {

        response.setContentType("application/vnd.ms-excel");
        if(outSelectReq.getOutTempleta().equals("天津高新区外资半年有望项目明细表")){

            response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode("天津高新区外资半年有望项目明细表.xlsx", "UTF8"));

        }else if (outSelectReq.getOutTempleta().equals("天津高新区外资签约落地项目明细表")){

            response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode("天津高新区外资签约落地项目明细表.xlsx", "UTF8"));

        }else {

            response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode("天津高新区外资在谈项目明细表.xlsx", "UTF8"));

        }

        response.setHeader("Access-Control-Expose-Headers", "content-Disposition");
        OutputStream outputStream = response.getOutputStream();

        //导入方法
        outSelectService.outputYzxm(outputStream,outSelectReq);

        outputStream.flush();
        outputStream.close();

    }

}
