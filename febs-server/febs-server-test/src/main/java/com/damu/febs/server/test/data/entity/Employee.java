package com.damu.febs.server.test.data.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Employee implements Serializable{

    private Integer id;

    private String name;

    private Integer age;

    private Date date;

    private Integer sex;

    private String flag;
}
