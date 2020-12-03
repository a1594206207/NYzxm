package com.nzxmmp.nzxm.Service.ServiceImpl.before;

import com.alibaba.excel.EasyExcel;

import com.nzxmmp.nzxm.DAO.DAOImpl.before.nzxmDAO;
import com.nzxmmp.nzxm.Mapper.NzxmbdMapping;
import com.nzxmmp.nzxm.Service.ExcelServic;
import com.nzxmmp.nzxm.Service.ServiceImpl.before.NzxmService;
import com.nzxmmp.nzxm.entity.before.EventListener;
import com.nzxmmp.nzxm.entity.before.ExcelModel;
import com.nzxmmp.nzxm.entity.Nzxmbd;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("ExcelSericeImpl")
public class ExcelServiceImpl implements ExcelServic {

    @Autowired
    @Qualifier("nzxmDAOImpl2")
    private nzxmDAO nzxmDAO;

    @Autowired
    private NzxmbdMapping nzxmbdMapping;
    @Autowired
    @Qualifier("NzxmServiceImpl2")
    private NzxmService nzxmService;
    //导出模板
    public Workbook outTemplate(){
        ClassPathResource classPathResource = new ClassPathResource("ExcxelTemplate/内资项目模板测试.xlsx");
        InputStream inputStream = null;
        try {
            inputStream = classPathResource.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            return  workbook;
        } catch (IOException e) {
            e.printStackTrace();
        }
       return null;


    }


    //导出全部数据
    public Workbook outAll(){
        ClassPathResource classPathResource = new ClassPathResource("ExcxelTemplate/内资项目模板测试.xlsx");
        InputStream inputStream = null;
        try {
            inputStream = classPathResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheet("Sheet1");


        Font titleFont = workbook.createFont();
        titleFont.setFontName("simsun");
        titleFont.setBold(true);
        titleFont.setColor(IndexedColors.BLACK.index);


        List<Nzxmbd> dataList=nzxmbdMapping.selectList(null);



        //创建数据行并写入值
        for (int j = 0; j < dataList.size(); j++) {
            Nzxmbd nzxmbdExcelModel = dataList.get(j);

            Row dataRow = sheet.createRow(j+ 1);
            dataRow.createCell(0).setCellValue(nzxmbdExcelModel.getXmmc());
            dataRow.createCell(1).setCellValue(nzxmbdExcelModel.getTzfmc());
            dataRow.createCell(2).setCellValue(nzxmbdExcelModel.getTzfszqy());
            dataRow.createCell(3).setCellValue(nzxmbdExcelModel.getTzfjj());
            dataRow.createCell(4).setCellValue(nzxmbdExcelModel.getXgsmc());
            dataRow.createCell(5).setCellValue(nzxmbdExcelModel.getTyxydm());
            dataRow.createCell(6).setCellValue(nzxmbdExcelModel.getQylx());
            dataRow.createCell(7).setCellValue(nzxmbdExcelModel.getLdsj());
            dataRow.createCell(8).setCellValue(nzxmbdExcelModel.getXmjj());
            dataRow.createCell(9).setCellValue(nzxmbdExcelModel.getHydl());
            dataRow.createCell(10).setCellValue(nzxmbdExcelModel.getHyzl());
            dataRow.createCell(11).setCellValue(nzxmbdExcelModel.getHyxl());

        }

        return workbook;
    }

    //将导入的表加入数据库
    @Transactional(rollbackFor = Exception.class)
    public String excelAddMysql(MultipartFile file) {


        String reason=null;//异常原因
        try {

            List<ExcelModel> list = new ArrayList<>();

            try {
//

                //
               EasyExcel.read(file.getInputStream(),new EventListener()).sheet().doRead();
                //

                list=EasyExcel.read(file.getInputStream(),ExcelModel.class,new EventListener()).sheet().doReadSync();
                //测试
                System.out.println("===========================");
                list.forEach(System.out::println);
                System.out.println("===========================");
            } catch (IOException e) {
                e.printStackTrace();
            }

            //判断是否有空属性
            for (int i = 0; i < list.size(); i++) {
                ExcelModel excelModel = list.get(i);

                Class<?> clazz = ExcelModel.class;  //得到类对象
                Field[] fs = clazz.getDeclaredFields(); //得到属性集合
                List<String> list2 = new ArrayList<String>();
                for (Field field : fs) {            //遍历属性
                    field.setAccessible(true); //设置属性是可以访问的（私有的也可以）
                    try {
                        if (field.get(excelModel) == null || field.get(excelModel) == "") {//通过对象判断有哪些空属性并且打印出来
                            String name = (String) field.getName();
                            list2.add(name);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    if (!list2.isEmpty()) {
                        if(excelModel.getXmmc()==null){
                            int row=i+2;   //判断未填写的是第几行
                            reason="存在第"+row+"行的项目名称未填写";
                            throw new RuntimeException();
                        }

                        reason=excelModel.getXmmc() + "项目存在未赋值的单元格";
                        throw new RuntimeException();
                    }
                }
            }

            for (int j = 0; j < list.size(); j++) {
                ExcelModel excelModel = list.get(j);
                Nzxmbd nzxmbd = new Nzxmbd();

                nzxmbd.setXmmc(excelModel.getXmmc());
                nzxmbd.setTzfmc(excelModel.getTzfmc());
                nzxmbd.setTzfszqy(excelModel.getTzfszqy());
                nzxmbd.setTzfjj(excelModel.getTzfjj());
                nzxmbd.setXgsmc(excelModel.getXgsmc());
                nzxmbd.setTyxydm(excelModel.getTyxydm());
                nzxmbd.setQylx(excelModel.getQylx());
                nzxmbd.setLdsj(excelModel.getLdsj());
                nzxmbd.setXmjj(excelModel.getXmjj());
                nzxmbd.setHydl(excelModel.getHydl());
                nzxmbd.setHyzl(excelModel.getHyzl());
                nzxmbd.setHyxl(excelModel.getHyxl());





                //添加方法
                Nzxmbd one = nzxmDAO.selectToName(nzxmbd.getXmmc());
                if(one!=null){

                    reason="“ "+nzxmbd.getXmmc()+" ”项目名已存在";
                    throw new Exception();

                }
                //初始化创建时间和删除状态
                nzxmbd.setCreattime(new Date());
                nzxmbd.setDeletestate("0");

                Integer result = nzxmDAO.insert(nzxmbd);

                if (!(result>0)){
                    throw new RuntimeException();
                }



                }


        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            e.printStackTrace();
            return "添加失败："+reason;
        }

        return "添加成功";
    }

}
