package com.damu.febs.server.business.controller;


import com.damu.febs.common.service.RedisService;
import com.damu.febs.server.business.config.RedisJedisService;
import com.damu.febs.server.business.data.dto.LoginParam;
import com.damu.febs.server.business.data.entity.User;
import com.damu.febs.server.business.redis.ConstTime;
import com.damu.febs.server.business.redis.UserKey;
import com.damu.febs.server.business.result.Result;
import com.damu.febs.server.business.service.UserService;
import com.damu.febs.server.business.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    RedisJedisService redisJedisService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public Result<User> doLogin(HttpServletResponse response, HttpSession session , @RequestBody LoginParam loginParam) {
        Result<User> login = userService.login(loginParam);
        if (login.isSuccess()){
            CookieUtil.writeLoginToken(response,session.getId());
            redisJedisService.set(UserKey.getByName , session.getId() ,login.getData(), ConstTime.RedisCacheExtime.REDIS_SESSION_EXTIME );
        }
        return login;
    }
}
