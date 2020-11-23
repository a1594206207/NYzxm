package com.nzxmmp.nzxm.Service;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelServic {

    //导出模板
    public Workbook outTemplate();


    //导出全部数据
    public Workbook outAll();

    //将导入的表导入数据库
    public String excelAddMysql(MultipartFile file);
}
