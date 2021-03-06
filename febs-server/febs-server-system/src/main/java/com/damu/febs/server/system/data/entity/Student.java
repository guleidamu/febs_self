package com.damu.febs.server.system.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("student")
public class Student implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;

    @TableField("sex")
    private String sex;

    @TableField("birthday")
    private Date birthday;

    @TableField("address")
    private String address;

    @TableField("remark")
    private String remark;

    public Student(String name, int age, String sex, Date birthday, String address, String remark) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.birthday = birthday;
        this.address = address;
        this.remark = remark;
    }

    public Student() {
    }

}
