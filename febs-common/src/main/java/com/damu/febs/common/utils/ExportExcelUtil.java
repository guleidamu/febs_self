package com.damu.febs.common.utils;

import com.damu.febs.common.entity.ExcelFieldModel;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportExcelUtil<T> {


    // 2007 版本以上 最大支持1048576行
    public final static String EXCEl_FILE_2007 = "2007";
    // 2003 版本 最大支持65536 行
    public final static String EXCEL_FILE_2003 = "2003";
    // 表格标题名默认sheet1,可自行设置
    private static String sheetName = "sheet1";

    // 时间数据，设定输出格式。默认为"yyyy-MM-dd hh:mm:ss"
    private static String dateFormat = "yyyy-MM-dd HH:mm:ss";

    private static List<ExcelFieldModel> excelFieldModels;



    public static String getSheetName() {
        return sheetName;
    }

    public static String setSheetName(String sheetName) {
        return ExportExcelUtil.sheetName = sheetName;
    }

    public static String getDateFormat() {
        return dateFormat;
    }

    public static void setDateFormat(String dateFormat) {
        ExportExcelUtil.dateFormat = dateFormat;
    }

    public static List<ExcelFieldModel> getExcelFieldModels() {
        return ExportExcelUtil.excelFieldModels;
    }

    public static void setExcelFieldModels(List<ExcelFieldModel> excelFieldModels) {
        ExportExcelUtil.excelFieldModels = excelFieldModels;
    }
    /**
     * 通过版本类判断是
     *
     * @param sheetName 表格标题名
     * @param headers   表格头部标题集合
     * @param dataset   数据集合
     * @param out       输出流
     * @param version   指定生成Excel文件的版本
     */
    public void exportExcel(String sheetName, String[] headers, Collection<T> dataset, OutputStream out, String version) {
        Workbook workbook = null;
        if (StringUtils.isBlank(version) || EXCEL_FILE_2003.equals(version.trim())) {
            workbook = new HSSFWorkbook();
        } else {
            workbook = new XSSFWorkbook();
        }
        exportExcel(workbook, sheetName, headers, dataset, out, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 通用Excel导出方法,利用反射机制遍历对象的所有字段，将数据写入Excel文件中 <br>
     * 此版本生成2007以上版本的文件 (文件后缀：xlsx)
     *
     * @param sheetName 表格标题名
     * @param headers   表格头部标题集合
     * @param dataset   需要显示的数据集合,集合中一定要放置符合JavaBean风格的类的对象。此方法支持的
     *                  JavaBean属性的数据类型有基本数据类型及String,Date
     * @param out       与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern   如果有时间数据，设定输出格式。默认为"yyyy-MM-dd hh:mm:ss"
     */
    public void exportExcel(Workbook workbook, String sheetName, String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
        // 生成一个表格
        Sheet sheet = workbook.createSheet(sheetName);
        // 设置表格默认列宽度为15个字节
        //设置宽度
        //sheet.setDefaultColumnWidth(20);
        sheet.setDefaultColumnWidth(18);
        //sheet.trackAllColumnsForAutoSizing();
//        sheet.autoSizeColumn(0);
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//内容居中
        // 生成一个字体
        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setBold(true);//加粗
        font.setFontHeightInPoints((short) 11);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        CellStyle style2 = workbook.createCellStyle();
        style2.setAlignment(HorizontalAlignment.CENTER);
        // 生成另一个字体
        Font font2 = workbook.createFont();
        // font2.setBold(true);//加粗
        // 把字体应用到当前的样式
        style2.setFont(font2);
        // 产生表格标题行
        Row row = sheet.createRow(0);
        Cell cellHeader;
        for (int i = 0; i < headers.length; i++) {
            cellHeader = row.createCell(i);
            cellHeader.setCellStyle(style);
            cellHeader.setCellValue(headers[i]);
        }

        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        T t;
        Field[] fields;
        Field field;
        //HSSFRichTextString richString;
        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
        Matcher matcher;
        String fieldName;
        String getMethodName;
        Cell cell;
        Class tCls;
        Method getMethod;
        Object value;
        String textValue;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            t = (T) it.next();
            // 利用反射，根据JavaBean属性的先后顺序，动态调用getXxx()方法得到属性值
            fields = t.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(style2);
                field = fields[i];
                fieldName = field.getName();
                getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                try {
                    tCls = t.getClass();
                    getMethod = tCls.getMethod(getMethodName, new Class[]{});
                    value = getMethod.invoke(t, new Object[]{});
                    // 判断值的类型后进行强制类型转换
                    textValue = null;
                    if (value instanceof Integer) {
                        cell.setCellValue((Integer) value);
                    } else if (value instanceof Float) {
                        textValue = String.valueOf((Float) value);
                        cell.setCellValue(textValue);
                    } else if (value instanceof Double) {
                        textValue = String.valueOf((Double) value);
                        cell.setCellValue(textValue);
                    } else if (value instanceof Long) {
                        cell.setCellValue((Long) value);
                    }
                    if (value instanceof Boolean) {
                        textValue = "是";
                        if (!(Boolean) value) {
                            textValue = "否";
                        }
                    } else if (value instanceof Date) {
                        textValue = sdf.format((Date) value);
                    } else {
                        // 其它数据类型都当作字符串简单处理
                        if (value != null) {
                            textValue = value.toString();
                        }
                    }
                    if (textValue != null) {
                        matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            // 是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        } else {
                            // richString = new HSSFRichTextString(textValue);
                            cell.setCellValue(textValue);
                        }
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭资源
                workbook.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过版本类判断是
     *
     * @param dataset   数据集合
     * @param out       输出流
     */
    public static void exportExcel(List<Map<String,Object>> dataset, OutputStream out){
        Workbook workbook = new XSSFWorkbook();
        // 生成一个表格
        Sheet sheet = workbook.createSheet(sheetName);
        // 设置宽度
        for (ExcelFieldModel fieldModel : excelFieldModels) {
            sheet.setColumnWidth(fieldModel.getColumn(),fieldModel.getWidth()*256+148);
        }

        // 生成第一行所需样式
        CellStyle style = workbook.createCellStyle();
        // 第一行标题行设置背景色为灰色
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        style.setAlignment(HorizontalAlignment.CENTER); // 内容居中
        // 生成一个字体
        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setBold(true); // 加粗
        font.setFontHeightInPoints((short) 11);
        // 把字体应用到当前样式
        style.setFont(font);

        // 生成第一行外内容样式
        CellStyle style2 = workbook.createCellStyle();
        style2.setAlignment(HorizontalAlignment.CENTER);
        // 生成另一个字体
        Font font2 = workbook.createFont();
        // 把字体应用到当前样式
        style2.setFont(font2);
        // 产生表格标题行
        Row row = sheet.createRow(0);
        Cell cellHeader;
        for (int i = 0; i < excelFieldModels.size(); i++) {
            cellHeader = row.createCell(i);
            cellHeader.setCellStyle(style);
            cellHeader.setCellValue(excelFieldModels.get(i).getTitle());
        }
        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
        Matcher matcher;
        Cell cell;
        Object value;
        String textValue;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        //遍历数据集合, 产生数据行
        for (int i = 0; i < dataset.size(); i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < excelFieldModels.size(); j++) {
                cell = row.createCell(excelFieldModels.get(j).getColumn());
                cell.setCellStyle(style2);
                value = dataset.get(i).get(excelFieldModels.get(j).getFiled());
                // 判断值的类型后进行强制类型转换
                textValue = null;
                if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                } else if (value instanceof Float) {
                    textValue = String.valueOf((Float) value);
                    cell.setCellValue(textValue);
                } else if (value instanceof Double) {
                    textValue = String.valueOf((Double) value);
                    cell.setCellValue(textValue);
                } else if (value instanceof Long) {
                    cell.setCellValue((Long) value);
                }
                if (value instanceof Boolean) {
                    textValue = "是";
                    if (!(Boolean) value) {
                        textValue = "否";
                    }
                } else if (value instanceof Date) {
                    textValue = sdf.format((Date) value);
                } else {
                    // 其它数据类型都当作字符串简单处理
                    if (value != null) {
                        textValue = value.toString();
                    }
                }
                if (textValue != null) {
                    matcher = p.matcher(textValue);
                    if (matcher.matches()) {
                        // 是数字当作double处理
                        cell.setCellValue(Double.parseDouble(textValue));
                    } else {
                        // richString = new HSSFRichTextString(textValue);
                        cell.setCellValue(textValue);
                    }
                }
            }
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭资源
                workbook.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * 导出带有头部标题行的Excel响应到页面
     * 时间格式默认：yyyy-MM-dd hh:mm:ss
     *生成2007版本,2003暂时未做开发.
     *
     * @param fileName 生成的Excel文件名字(加后缀)
     * @param dataset  数据集合
     * @param response
     */
    public static void exportExcelResponse(List<Map<String,Object>> dataset, HttpServletResponse response, String fileName) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", String.format("attachment; filename=%s", fileName));
        response.addHeader("Access-Control-Expose-headers","Content-Disposition");
        exportExcel(dataset, response.getOutputStream());
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("E:\\111111.xlsx");
        FileOutputStream out = new FileOutputStream(file);
        ExportExcelUtil exportExcelUtil = new ExportExcelUtil();
        List<Map<String,Object>> dataset = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("skuCode",i+1000);
            hashMap.put("asnRecQty","表格"+i);
            if (i!=3){
                hashMap.put("unti",i+1000);
            }
            hashMap.put("skuType",i+10.5);
            hashMap.put("createDate",new Date());
            dataset.add(hashMap);
        }
        // 读取detail数据
        List<ExcelFieldModel> list = new ArrayList<ExcelFieldModel>();
        list.add(new ExcelFieldModel(0,"skuCode", "skuCode",9)); // SKU -- detail
        list.add(new ExcelFieldModel(1,"asnRecQty", "asnRecQty",14)); // 预入库数量(ASN入库数量)
        list.add(new ExcelFieldModel(2,"unti", "unti",6)); // 入库单位
        list.add(new ExcelFieldModel(3,"skuType", "skuType",9)); // 品质分类(类型)
        list.add(new ExcelFieldModel(4,"createDate", "createDate",17)); // 品质分类(类型)
        exportExcelUtil.setExcelFieldModels(list);

        exportExcelUtil.exportExcel(dataset,out);
    }
}
