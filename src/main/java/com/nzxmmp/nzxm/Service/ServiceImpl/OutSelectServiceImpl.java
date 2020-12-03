package com.nzxmmp.nzxm.Service.ServiceImpl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.nzxmmp.nzxm.DAO.DAOImpl.nzxmqxDAOImpl3;
import com.nzxmmp.nzxm.DAO.nzxmqxDAO;
import com.nzxmmp.nzxm.Service.OutSelectService;
import com.nzxmmp.nzxm.entity.Nzxmbd;
import com.nzxmmp.nzxm.entity.OutExcel;
import com.nzxmmp.nzxm.entity.OutQyldExcel;
import com.nzxmmp.nzxm.entity.OutSelectReq;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 内资项目通过excel导出需要查询的项目
 * （不用）
 * 具体实现看OutSelectServiceImpl2(整合内外资导出数据)
 *
 */
@Service("OutSelectServiceImpl")
public class OutSelectServiceImpl implements OutSelectService {

    @Autowired
    @Qualifier("nzxmqxDAOImpl3")
    private nzxmqxDAO nzxmdao;
    @Override
    public Workbook outputSelect(OutSelectReq outSelectReq ) {


        ClassPathResource classPathResource = new ClassPathResource("ExcxelTemplate/内资导出数据模板.xlsx");
        InputStream inputStream = null;
        try {
            inputStream = classPathResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Workbook workbook = null;
        try {
            workbook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheetAt(0);


        Font titleFont = workbook.createFont();
        titleFont.setFontName("simsun");
        titleFont.setBold(true);
        titleFont.setColor(IndexedColors.BLACK.index);

        //表格导出数据方法
        List<Nzxmbd> dataList = nzxmdao.outSelectAll(outSelectReq);


        //创建数据行并写入值
        for (int j = 0; j < dataList.size(); j++) {
            Nzxmbd nzxmbdExcelModel = dataList.get(j);
            OutExcel outExcel = new OutExcel();
            BeanUtils.copyProperties(nzxmbdExcelModel,outExcel);

            Row dataRow = sheet.createRow(j+ 1);

            dataRow.createCell(0).setCellValue(outExcel.getXmmc());
            dataRow.createCell(1).setCellValue(outExcel.getHydl());
            dataRow.createCell(2).setCellValue(outExcel.getHyzl());
            dataRow.createCell(3).setCellValue(outExcel.getHyxl());
            dataRow.createCell(4).setCellValue(outExcel.getTzfmc());
            dataRow.createCell(5).setCellValue(outExcel.getTzze());
            dataRow.createCell(6).setCellValue(outExcel.getQylx());
            dataRow.createCell(7).setCellValue(outExcel.getXmlydSheng());
            dataRow.createCell(8).setCellValue(outExcel.getXmlydShi());
            dataRow.createCell(9).setCellValue(outExcel.getXmlb());
            dataRow.createCell(10).setCellValue(outExcel.getXmjj());
            dataRow.createCell(11).setCellValue(outExcel.getNldsj());
            dataRow.createCell(12).setCellValue(outExcel.getSfwbq());
            dataRow.createCell(13).setCellValue(outExcel.getSfqyzb());
            dataRow.createCell(14).setCellValue(outExcel.getSfsyxyt());
            dataRow.createCell(15).setCellValue(outExcel.getSfsyrgzn());
            dataRow.createCell(16).setCellValue(outExcel.getSfgnxzx());
            dataRow.createCell(17).setCellValue(outExcel.getGnzxlx());
            dataRow.createCell(18).setCellValue(outExcel.getZsjd());
            dataRow.createCell(19).setCellValue(outExcel.getYdxq());
            dataRow.createCell(20).setCellValue(outExcel.getCkfxq());
            dataRow.createCell(21).setCellValue(outExcel.getBglxq());
            dataRow.createCell(22).setCellValue(outExcel.getZczb());
            dataRow.createCell(23).setCellValue(outExcel.getSfwyqyxm());
            dataRow.createCell(24).setCellValue(outExcel.getQyxmsshd());
            dataRow.createCell(25).setCellValue(outExcel.getSfybxq());
            dataRow.createCell(26).setCellValue(outExcel.getUnitName());
            dataRow.createCell(27).setCellValue(outExcel.getUserName());
            dataRow.createCell(28).setCellValue(outExcel.getUserMobilePhone());
            dataRow.createCell(29).setCellValue(outExcel.getBak1());
            dataRow.createCell(30).setCellValue(outExcel.getBak2());
        }

        return workbook;



    }

    @Override
    public void outputNzxm(OutputStream outputStream, OutSelectReq outSelectReq) {

    }

    @Override
    public void outputYzxm(OutputStream outputStream, OutSelectReq outSelectReq) {

    }


    public void LDout(OutputStream outputStream,OutSelectReq outSelectReq){




        List<Nzxmbd> nzxmbds = nzxmdao.outSelectAll(outSelectReq);

        List<OutQyldExcel> outQyldExcels=new ArrayList<>();
        for (Nzxmbd nzxmbd : nzxmbds) {
            OutQyldExcel excelZtxm = new OutQyldExcel();
            BeanUtils.copyProperties(nzxmbd,excelZtxm);
            outQyldExcels.add(excelZtxm);
        }
        //设置表头颜色
        WriteCellStyle head=new WriteCellStyle();
        head.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(head,new WriteCellStyle());

        EasyExcel.write(outputStream,OutQyldExcel.class).
                registerWriteHandler(horizontalCellStyleStrategy).sheet("sheet 0").doWrite(outQyldExcels);


    }


}
