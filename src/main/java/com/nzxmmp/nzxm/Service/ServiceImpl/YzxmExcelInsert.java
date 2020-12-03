package com.nzxmmp.nzxm.Service.ServiceImpl;

import com.nzxmmp.nzxm.Service.ExcelServic;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * 外资项目
 * 为导入表单加入数据库做表头判断
 *  判断为哪种类型（半年有望，签约落地，在谈项目）的表
 *
 */
@Service
public class YzxmExcelInsert {
    @Autowired
    @Qualifier("YzxmExcelServiceZtxmlmpl")
    private ExcelServic excelServiceZ;

    @Autowired
    @Qualifier("YzxmExcelServiceBnywImpl")
    private  ExcelServic excelServiceB;

    @Autowired
    @Qualifier("YzxmExcelServiceQyldImpl")
    private  ExcelServic excelServiceQ;

    public  String compare(MultipartFile file){


        InputStream inputStream = null;
        try {

            inputStream = file.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Workbook sheets = null;
        try {
            sheets = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = sheets.getSheetAt(0);
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        String headName = cell.getStringCellValue();

        String result=null;

        if (headName.equals("天津高新区外资在谈项目项目明细表")){
            result = excelServiceZ.excelAddMysql(file);
        }else if(headName.equals("天津高新区外资半年有望项目明细表")){

            result=excelServiceB.excelAddMysql(file);

        }else if(headName.equals("天津高新区外资签约落地项目明细表")){
            result=excelServiceQ.excelAddMysql(file);
        }else {
            result="表头名称无法识别，建议重新下载模板";
        }

        return result;
    }

}
