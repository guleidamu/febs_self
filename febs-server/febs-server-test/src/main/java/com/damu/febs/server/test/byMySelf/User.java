package com.damu.febs.server.test.byMySelf;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private String userName;

    private transient String password;
}
