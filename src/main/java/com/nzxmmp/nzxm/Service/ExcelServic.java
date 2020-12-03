package com.nzxmmp.nzxm.Service;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * excel 的模板导入导出
 *
 */
public interface ExcelServic {

    //导出模板
    public Workbook outTemplate();


    //导出全部数据
    @Deprecated//过时
    public Workbook outAll();

    //将导入的表导入数据库
    public String excelAddMysql(MultipartFile file);
}
