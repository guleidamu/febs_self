package com.damu.febs.server.system.data.dto;

import lombok.Data;

@Data
public class StudentDto extends BaseDto{

    private String name;

    private String address;

    private Integer num = 100;
}
