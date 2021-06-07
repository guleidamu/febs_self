package com.damu.febs.server.test.controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestMain {

    public static void main(String[] args) {
        Integer id = 0;
        if("0".equals(id)){
            log.info("you");
        } else {
            log.info("not equals");
        }
    }
}
