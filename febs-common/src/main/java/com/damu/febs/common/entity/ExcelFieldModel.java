package com.damu.febs.common.entity;

import lombok.Data;

@Data
public class ExcelFieldModel {
    private int width = 15; // 列宽
    private String title;// 头部标题 写
    private String filed;// 字段 读写
    private String format;// 格式 读写
    private int column;// 列 读写
    private int row;// 行 读写

    private Object cellValue;//  写

    private boolean notEmpty = false;// 是否允许空 读

    public ExcelFieldModel(String filed, int column) {
        this.filed = filed;
        this.column = column;
    }

    public ExcelFieldModel(String filed, int column, int row) {
        this.filed = filed;
        this.column = column;
        this.row = row;
    }

    public ExcelFieldModel(int column, int row, Object cellValue) {
        this.cellValue = cellValue;
        this.column = column;
        this.row = row;
    }

    public ExcelFieldModel(int column, int row, Object cellValue, String format) {
        this.cellValue = cellValue;
        this.column = column;
        this.row = row;
        this.format = format;
    }

    public ExcelFieldModel(int column, int row, Object cellValue, int width) {
        this.cellValue = cellValue;
        this.column = column;
        this.row = row;
        this.width = width;
    }

    public ExcelFieldModel(String filed, int column, String format) {
        this.filed = filed;
        this.column = column;
        this.format = format;
    }

    public ExcelFieldModel(String filed, String title, String format) {
        this.filed = filed;
        this.title = title;
        this.format = format;
    }

    public ExcelFieldModel(String filed, int column, String format,
                           boolean notEmpty) {
        this.filed = filed;
        this.column = column;
        this.format = format;
        this.notEmpty = notEmpty;
    }

    public ExcelFieldModel(String filed, String title, String format,
                           boolean notEmpty) {
        this.filed = filed;
        this.title = title;
        this.format = format;
        this.notEmpty = notEmpty;
    }

    public ExcelFieldModel(int column, String filed, String title) {
        this.filed = filed;
        this.column = column;
        this.title = title;
    }

    public ExcelFieldModel(int column, String filed, String title, String format) {
        this.filed = filed;
        this.column = column;
        this.title = title;
        this.format = format;
    }

    public ExcelFieldModel(int column, String filed, String title, int width,
                           String format) {
        this.filed = filed;
        this.column = column;
        this.title = title;
        this.format = format;
        this.width = width;
    }

    public ExcelFieldModel(int column, String filed, String title, int width) {
        this.filed = filed;
        this.column = column;
        this.title = title;
        this.width = width;
    }
}
