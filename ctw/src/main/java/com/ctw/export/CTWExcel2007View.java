package com.ctw.export;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ctw com.ctw.export
 *
 * @author: HaiAng
 * @CreateDate: 2016/06/17 23: 58
 * @Version 1.0
 * @explain：............
 */
public class CTWExcel2007View extends AbstractXlsxView {
    /**
     * ModelAndView中传入的data列表的key
     */
    public static final String KEY_DATA = "xlsx_data";

    private static final Logger logger = LoggerFactory.getLogger(CTWExcel2007View.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final TypeReference<HashMap<String, Object>> TYPE_REFERENCE = new TypeReference<HashMap<String, Object>>() {
    };

    private Excel2007Builder builder;
    private String filename;
    private List<String> fields;
    private List<String> headers;
    private List<Integer> columnWidths;

    static {
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    }

    public Excel2007Builder getBuilder() {
        return builder;
    }

    public void setBuilder(Excel2007Builder builder) {
        this.builder = builder;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public List<Integer> getColumnWidths() {
        return columnWidths;
    }

    public void setColumnWidths(List<Integer> columnWidths) {
        this.columnWidths = columnWidths;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置excel文件名
        response.setHeader("Content-Disposition", "attachment; filename=" + buildFilename(request));

        if (builder == null) { // 默认作简单的excel导出
            Object dataObj = model.get(KEY_DATA);
            if (dataObj == null) {
                throw new RuntimeException("data为空, 导出excel失败");
            }
            if (!(dataObj instanceof List)) {
                throw new RuntimeException("data不是列表, 导出excel失败");
            }
            doBuildDocument(workbook, (List<Object>)dataObj);
        } else { // 如果定义了builder, 则使用builder来构造excel
            builder.buildExcelDocument(model, workbook, request, response);
        }
    }

    private void doBuildDocument(Workbook workbook, List<Object> data) throws IOException {
        // 1. 创建工作簿
        Sheet sheet = workbook.createSheet();

        // 2. 如果设置了列属性对应列表, 则生成对应表, 并设置标题
        Map<Integer, String> fieldIndexMap = createHeaders(workbook, sheet);

        // 3. 填充数据
        createData(workbook, sheet, fieldIndexMap, data);

        // 4. 设置列宽度
        adjustColumnWidths(sheet);
    }

    private void adjustColumnWidths(Sheet sheet) {
        if (columnWidths != null && !columnWidths.isEmpty()) {
            int colindex = 0;
            for (Integer colwidth : columnWidths) {
                // Set the width (in units of 1/256th of a character width)
                sheet.setColumnWidth(colindex, colwidth * 256);
                colindex++;
            }
        }
    }

    private void createData(Workbook workbook, Sheet sheet, Map<Integer, String> fieldIndexMap, List<Object> data) throws IOException {
        Map<Integer, String> idmap = fieldIndexMap;
        int rowIndex = 0;
        if (idmap == null) {
            // 没有指定, 获取第一行数据并确定列顺序
            idmap = new HashMap<Integer, String>();
            Map<String, Object> datamap;
            Object data1 = data.get(0);
            if (!(data1 instanceof Map)) {
                // 将pojo转换为map
                datamap = pojo2map(data1);
            } else {
                datamap = (Map<String, Object>)data1;
            }
            int index = 0;
            for (String key : datamap.keySet()) {
                idmap.put(index, key);
                index ++;
            }
        } else {
            rowIndex = 1;
        }
        // 设置表格边框
        Font font = workbook.createFont();
        // 宋体9pt
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 9);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setFont(font);
        // 输出数据
        for (Object rowitem : data) {
            Map<String, Object> rowdata;
            if (!(rowitem instanceof Map)) {
                // 将pojo转换为map
                rowdata = pojo2map(rowitem);
            } else {
                rowdata = (Map<String, Object>)rowitem;
            }
            Row row = sheet.createRow(rowIndex);
            for (int colindex = 0; colindex < idmap.size(); colindex++) {
                Cell cell = row.createCell(colindex);
                Object value = rowdata.get(idmap.get(colindex));
                String cellValue = value == null ? null : value.toString();
                cell.setCellValue(cellValue);
                cell.setCellStyle(cellStyle);
            }
            rowIndex++;
        }
    }

    // 将pojo转换为map
    private Map<String, Object> pojo2map(Object pojo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(pojo);
        return mapper.readValue(json, TYPE_REFERENCE);
    }

    private Map<Integer, String> createHeaders(Workbook workbook, Sheet sheet) {
        if (fields == null) {
            return null;
        }

        // 设置表头单元的样式
        CellStyle style = null;
        if (headers != null) {
            Font titleFont = workbook.createFont();
            titleFont.setFontName("宋体");
            titleFont.setFontHeightInPoints((short) 9);
            titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
            style = workbook.createCellStyle();
            style.setAlignment(CellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            style.setBorderBottom(CellStyle.BORDER_THIN);
            style.setBorderTop(CellStyle.BORDER_THIN);
            style.setBorderRight(CellStyle.BORDER_THIN);
            style.setBorderLeft(CellStyle.BORDER_THIN);
            style.setFont(titleFont);
        }

        // 生成field对应的列顺序
        Map<Integer, String> fieldIndexMap = new HashMap<Integer, String>();
        int columnIndex = 0;
        Row row = null;
        // 如果设置了标题名称, 则创建标题行
        if (headers != null) {
            row = sheet.createRow(0);
        }
        for (String field : fields) {
            if (headers != null) {
                Cell cell = row.createCell(columnIndex);
                cell.setCellValue(headers.get(columnIndex));
                cell.setCellStyle(style);
            }

            fieldIndexMap.put(columnIndex, field);
            columnIndex++;
        }

        return fieldIndexMap;
    }

    private String buildFilename(HttpServletRequest request) {
        if (StringUtils.isBlank(filename)) {
            return DateFormatUtils.format(new Date(), "yyyyMMdd_HHmmss").concat(".xlsx");
        } else {
            return encodeFileName(request, filename).concat(".xlsx");
        }
    }

    /**
     * 根据不同浏览器将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
     *
     * @param request
     * @param sourcename 原文件名
     * @return 重新编码后的文件名
     */
    private String encodeFileName(HttpServletRequest request, final String sourcename) {
        String agent = request.getHeader("User-Agent");
        String result;
        try {
            boolean isFireFox = (agent != null && agent.toLowerCase().indexOf("firefox") != -1);
            if (isFireFox) {
                result = new String(sourcename.getBytes("UTF-8"), "ISO8859-1");
            } else {
                result = URLEncoder.encode(sourcename, "UTF-8");
                if ((agent != null && agent.indexOf("MSIE") != -1)) {
                    // see http://support.microsoft.com/default.aspx?kbid=816868
                    if (result.length() > 150) {
                        // 根据request的locale 得出可能的编码
                        result = new String(result.getBytes("UTF-8"), "ISO8859-1");
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.error(">> encode excel filename[" + sourcename + "] error", e);
            result = sourcename;
        }
        return result;
    }
}
