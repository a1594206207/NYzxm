package com.nzxmmp.nzxm.Service.ServiceImpl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.nzxmmp.nzxm.DAO.YzxmDAO;
import com.nzxmmp.nzxm.DAO.nzxmqxDAO;
import com.nzxmmp.nzxm.Service.OutSelectService;
import com.nzxmmp.nzxm.entity.*;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 内外资通过excel导出项目数据实现类
 *
 */
@Service("outSelectServiceImpl2")
public class OutSelectServiceImpl2 implements OutSelectService {

    @Autowired
    @Qualifier("nzxmqxDAOImpl3")
    private nzxmqxDAO nzxmdao;

    @Autowired
    @Qualifier("yzxmDAOImpl")
    private YzxmDAO yzxmdao;


    @Override
    public Workbook outputSelect(OutSelectReq outSelectReq) {
        return null;
    }

    /**
     * 内资数据导出
     * @param outputStream
     * @param outSelectReq
     */
    @Override
    public void outputNzxm(OutputStream outputStream, OutSelectReq outSelectReq) {
        List<Nzxmbd> nzxmbds = nzxmdao.outSelectAll(outSelectReq);

        if(outSelectReq.getOutTempleta().equals("天津高新区内资签约落地项目明细表")){
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

        }else {
            List<OutExcel> outExcels=new ArrayList<>();
            for (Nzxmbd nzxmbd : nzxmbds) {
                OutExcel excelZtxm = new OutExcel();
                BeanUtils.copyProperties(nzxmbd,excelZtxm);
                outExcels.add(excelZtxm);
            }
            //设置表头颜色
            WriteCellStyle head=new WriteCellStyle();
            head.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(head,new WriteCellStyle());

            EasyExcel.write(outputStream,OutExcel.class).
                    registerWriteHandler(horizontalCellStyleStrategy).sheet("sheet 0").doWrite(outExcels);


        }


    }

    /**
     * 外资数据导出
     * @param outputStream
     * @param outSelectReq 导出条件实体类对象
     */
    @Override
    public void outputYzxm(OutputStream outputStream, OutSelectReq outSelectReq) {
        List<Yzxmbd> yzxmbds = yzxmdao.outSelectAll(outSelectReq);

        if(outSelectReq.getOutTempleta().equals("天津高新区外资签约落地项目明细表")){
            List<YzOutQyldExcel> yzoutQyldExcels=new ArrayList<>();
            for (Yzxmbd yzxmbd : yzxmbds) {
                YzOutQyldExcel yzexcelZtxm = new YzOutQyldExcel();
                BeanUtils.copyProperties(yzxmbd,yzexcelZtxm);
                yzoutQyldExcels.add(yzexcelZtxm);
            }
            //设置表头颜色
            WriteCellStyle head=new WriteCellStyle();
            head.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(head,new WriteCellStyle());

            EasyExcel.write(outputStream,YzOutQyldExcel.class).
                    registerWriteHandler(horizontalCellStyleStrategy).sheet("sheet 0").doWrite(yzoutQyldExcels);

        }else {
            List<YzOutZBExcel> outExcels = new ArrayList<>();
            for (Yzxmbd yzxmbd : yzxmbds) {
                YzOutZBExcel excel = new YzOutZBExcel();
                BeanUtils.copyProperties(yzxmbd, excel);
                outExcels.add(excel);
            }
            //设置表头颜色
            WriteCellStyle head = new WriteCellStyle();
            head.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(head, new WriteCellStyle());

            EasyExcel.write(outputStream, YzOutZBExcel.class).
                    registerWriteHandler(horizontalCellStyleStrategy).sheet("sheet 0").doWrite(outExcels);

        }
    }
}
