package com.damu.febs.common.data.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserSearchDto extends BaseDto implements Serializable {

    private String username;

    private String password;

}
