package com.ctw.export;

import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * ctw com.ctw.export
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/17 23: 58
 * @Version 1.0
 * @explain：............
 */
public interface Excel2007Builder {

    /**
     * @see org.springframework.web.servlet.view.document.AbstractXlsxStreamingView#buildExcelDocument(Map, Workbook, HttpServletRequest, HttpServletResponse)
     * @param model
     * @param workbook
     * @param request
     * @param response
     * @throws Exception
     */
    void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
