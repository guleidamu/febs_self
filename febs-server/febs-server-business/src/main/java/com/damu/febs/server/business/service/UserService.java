package com.damu.febs.server.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
//import com.damu.febs.common.response.Result;
import com.damu.febs.server.business.data.dto.LoginParam;
import com.damu.febs.server.business.data.entity.User;
import com.damu.febs.server.business.result.Result;


public interface UserService extends IService<User> {

    Result<User> login(LoginParam loginParam);
}
