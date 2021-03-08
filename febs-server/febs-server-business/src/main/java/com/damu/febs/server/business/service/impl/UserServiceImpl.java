package com.damu.febs.server.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

//import com.damu.febs.common.response.Result;
import com.damu.febs.server.business.data.dto.LoginParam;
import com.damu.febs.server.business.data.entity.User;
import com.damu.febs.server.business.mapper.UserMapper;
import com.damu.febs.server.business.result.CodeMsg;
import com.damu.febs.server.business.result.Result;
import com.damu.febs.server.business.service.UserService;
import com.damu.febs.server.business.util.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Result<User> login(LoginParam loginParam) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone", loginParam.getMobile());
        User user = userMapper.selectOne(userQueryWrapper);

        if (user == null) {
            return Result.error(CodeMsg.MOBILE_NOT_EXIST);
        }
        String dbPwd = user.getPassword();
        String salt = user.getSalt();
        //这边是通过md5校验
//        String calcPass = MD5Util.formPassToDBPass(loginParam.getPassword(), salt);
        String calcPass = MD5Util.inputPassToDbPass(loginParam.getPassword(), salt);
        if (!StringUtils.equals(dbPwd, calcPass)) {
            return Result.error(CodeMsg.PASSWORD_ERROR);
        }
        user.setPassword(StringUtils.EMPTY);
        return Result.success(user);
    }
}
