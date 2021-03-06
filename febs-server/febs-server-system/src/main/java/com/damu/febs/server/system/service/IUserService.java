package com.damu.febs.server.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.damu.febs.common.data.dto.UserSearchDto;
import com.damu.febs.common.entity.FebsResponse;
import com.damu.febs.common.entity.QueryRequest;
import com.damu.febs.common.entity.system.SystemUser;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IUserService extends IService<SystemUser> {

    /**
     * 查找用户详细信息
     *
     * @param request request
     * @param user    用户对象，用于传递查询条件
     * @return IPage
     */
    IPage<SystemUser> findUserDetail(SystemUser user, QueryRequest request);


    IPage<SystemUser> queryUserDetail(UserSearchDto userSearchDto);

    List<SystemUser> selectUser(UserSearchDto userSearchDto);

    FebsResponse importExcelData(MultipartFile file, HttpServletResponse response) throws Exception;

    /**
     * 新增用户
     *
     * @param user user
     */
    void createUser(SystemUser user);

    /**
     * 修改用户
     *
     * @param user user
     */
    void updateUser(SystemUser user);

    /**
     * 删除用户
     *
     * @param userIds 用户 id数组
     */
    void deleteUsers(String[] userIds);

    FebsResponse customerImportExcel(MultipartFile file, HttpServletResponse response);
}