package com.nzxmmp.nzxm.Service.ServiceImpl;

import com.alibaba.excel.EasyExcel;
import com.nzxmmp.nzxm.DAO.YzxmDAO;
import com.nzxmmp.nzxm.Service.ExcelServic;
import com.nzxmmp.nzxm.Service.ServiceImpl.before.NzxmService;
import com.nzxmmp.nzxm.Service.YzxmService;
import com.nzxmmp.nzxm.entity.ExcelZtxm;
import com.nzxmmp.nzxm.entity.Nzxmbd;
import com.nzxmmp.nzxm.entity.YzZtxmExecl;
import com.nzxmmp.nzxm.entity.Yzxmbd;
import com.nzxmmp.nzxm.entity.before.EventListener;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 外资半年有望导出模板，导入数据
 *
 */
@Service("YzxmExcelServiceBnywImpl")
public class YzxmExcelServiceBnywImpl implements ExcelServic {

    @Autowired
    @Qualifier("YzxmServiceImpl")
    private YzxmService yzxmService;


    /**
     * 作用是为了使用查询项目名称方法判断execl类是否存在数据库存在的项目名称
     */
    @Autowired
    @Qualifier("yzxmDAOImpl")
    private YzxmDAO yzxmdao;


    /**
     * 导出模板
     * @return
     */

    @Override
    public Workbook outTemplate() {
        ClassPathResource classPathResource = new ClassPathResource("ExcxelTemplate/外资半年有望模板.xlsx");
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

    @Override
    public Workbook outAll() {
        return null;
    }


    /**
     * 导入mysql
     *
     */
    @Override
    public String excelAddMysql(MultipartFile file) {
        String reason=null;//异常原因
        try {

            List<YzZtxmExecl> list = new ArrayList<>();

            try {
//

                list= EasyExcel.read(file.getInputStream(), YzZtxmExecl.class,new EventListener()).sheet().headRowNumber(4).doReadSync();

                if(list.isEmpty()){
                    reason="未读取到数据";
                    throw new RuntimeException();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            //判断是否有空属性
            for (int i = 0; i < list.size(); i++) {
                YzZtxmExecl excelModel = list.get(i);

                Class<?> clazz = YzZtxmExecl.class;  //得到类对象！
                Field[] fs = clazz.getDeclaredFields(); //得到属性集合
                Map<Integer,String> map = new HashMap<Integer, String>();//装载属性集合
                int xcall=0;   //记录未填写的是第几行

                for (int r=0;r<fs.length;r++) {            //遍历属性
                    fs[r].setAccessible(true); //设置属性是可以访问的（私有的也可以）
                    try {
                        if (fs[r].get(excelModel) == null || fs[r].get(excelModel) == "") {//通过对象判断有哪些空属性并且打印出来
                            String name = (String) fs[r].getName();
                            xcall=r+1;
                            map.put(xcall,name);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    if (!map.isEmpty()) {
                        if(excelModel.getXmmc()==null){
                            int row=i+5;   //判断未填写的是第几行
                            reason="存在第"+row+"行的项目名称未填写";
                            throw new RuntimeException();
                        }

                        reason=excelModel.getXmmc() + "项目存在未赋值在第"+map.keySet().toString()+"列";//显示第几个单元格未填写
                        throw new RuntimeException();
                    }
                }
            }


            //判断是否有与数据库重复的项目名


            ArrayList<String> Xmmcs = new ArrayList<>();//搜集导入所有的项目名称集合
            for (YzZtxmExecl excelZtxm : list) {
                Xmmcs.add(excelZtxm.getXmmc());
            }
            long count = Xmmcs.stream().distinct().count();
            if(Xmmcs.size()!=count){
                reason="导入表内存在相同的项目名称，请重新检查";
                throw new RuntimeException();
            }

            ArrayList<String> stringXmmc = new ArrayList<>();//搜集数据库存在的项目名称
            for (YzZtxmExecl excelZtxm : list) {
                Yzxmbd one = yzxmdao.selectToName(excelZtxm.getXmmc());
                if(one!=null){
                    stringXmmc.add(excelZtxm.getXmmc());
                }
            }
            if (!stringXmmc.isEmpty()){
                reason=stringXmmc.toString()+"项目已存在";
                throw new RuntimeException();
            }





            //导入
            for (int j = 0; j < list.size(); j++) {
                YzZtxmExecl excelModel = list.get(j);
                Yzxmbd yzxmbd = new Yzxmbd();

                BeanUtils.copyProperties(excelModel,yzxmbd);
                yzxmbd.setState("半年有望");

                //添加方法
                String insert = yzxmService.insert(yzxmbd);
                if(!(insert.equals("添加成功"))){
                    reason=insert;
                    throw new RuntimeException();
                }

            }

        }catch (Exception e){

//            e.printStackTrace();
            return "添加失败："+reason;
        }
        return "添加成功";
    }
}
