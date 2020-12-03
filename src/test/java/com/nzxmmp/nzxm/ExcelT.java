package com.nzxmmp.nzxm;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.nzxmmp.nzxm.DAO.DAOImpl.before.nzxmDAO;
import com.nzxmmp.nzxm.Mapper.NzxmbdMapping;
import com.nzxmmp.nzxm.entity.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelT extends NzxmApplicationTests{

    @Test
    public void readhead() throws Exception{
        ClassPathResource classPathResourceFilter = new ClassPathResource("ExcxelTemplate/天津高新区内资半年有望项目明细表导入模板 .xlsx");
        InputStream inputStream = classPathResourceFilter.getInputStream();

        Workbook sheets = new XSSFWorkbook(inputStream);
        Sheet sheetAt = sheets.getSheetAt(0);
//        Sheet sheet = sheets.getSheet("内资重点推动项目清单");
        Row row = sheetAt.getRow(0);
        Cell cell = row.getCell(0);
        String stringCellValue = cell.getStringCellValue();
        System.out.println(stringCellValue);

    }
    @Autowired
    @Qualifier("nzxmDAOImpl2")
    private nzxmDAO nzxmdao;
    @Test
    public void outexcel(){
        OutSelectReq outSelectReq = new OutSelectReq();
        outSelectReq.setOutTempleta("sadf");
        List<Nzxmbd> nzxmbds = nzxmdao.outSelectAll(outSelectReq);
        System.out.println("======================");
        System.out.println(nzxmbds.toString());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        for (Nzxmbd nzxmbd : nzxmbds) {
            OutExcel outExcel = new OutExcel();
            BeanUtils.copyProperties(nzxmbd,outExcel);
            System.out.println(outExcel.toString());

        }


    }


    @Autowired
    private NzxmbdMapping nzxmbdMapping;

    @Test
    public  void  tOut() throws FileNotFoundException {

        FileOutputStream fileOutputStream = new FileOutputStream("d://aaa/abcd.xlsx");
        String fileName =   "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        List<Nzxmbd> nzxmbds = nzxmbdMapping.selectList(null);
        List<OutQyldExcel> excelZtxms=new ArrayList<>();
        for (Nzxmbd nzxmbd : nzxmbds) {
        OutQyldExcel excelZtxm = new OutQyldExcel();
            BeanUtils.copyProperties(nzxmbd,excelZtxm);
            excelZtxms.add(excelZtxm);
        }

        EasyExcel.write(fileOutputStream, OutQyldExcel.class).sheet("模板").doWrite(excelZtxms);

    }
}
