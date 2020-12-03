package com.nzxmmp.nzxm.Service;

import com.nzxmmp.nzxm.entity.OutSelectReq;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * 导出excel   导出内外资项目
 */
public interface OutSelectService {

    @Deprecated
    public Workbook outputSelect(OutSelectReq outSelectReq);

    //导出内资项目数据
    public void outputNzxm(OutputStream outputStream,OutSelectReq outSelectReq);

    //导出外资项目数据
    public void outputYzxm(OutputStream outputStream,OutSelectReq outSelectReq);
}
