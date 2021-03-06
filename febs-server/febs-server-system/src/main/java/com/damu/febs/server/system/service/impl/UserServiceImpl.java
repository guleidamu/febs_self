package com.damu.febs.server.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.damu.febs.common.data.dto.UserSearchDto;
import com.damu.febs.common.entity.ExcelFieldModel;
import com.damu.febs.common.entity.FebsResponse;
import com.damu.febs.common.entity.QueryRequest;
import com.damu.febs.common.entity.system.SystemUser;
import com.damu.febs.common.entity.system.UserRole;
import com.damu.febs.common.utils.CollectionUtil;
import com.damu.febs.common.utils.ParseExcelUtil;
import com.damu.febs.server.system.mapper.UserMapper;
import com.damu.febs.server.system.service.IUserRoleService;
import com.damu.febs.server.system.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, SystemUser> implements IUserService {

    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public IPage<SystemUser> findUserDetail(SystemUser user, QueryRequest request) {
        Page<SystemUser> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.baseMapper.findUserDetailPage(page, user);
    }

    @Override
    public IPage<SystemUser> queryUserDetail(UserSearchDto userSearchDto) {
        LambdaQueryWrapper<SystemUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isBlank(userSearchDto.getUsername()), SystemUser::getUsername, userSearchDto.getUsername());
        Page<SystemUser> systemUserPage = new Page<>(userSearchDto.getPageNum(), userSearchDto.getPageSize());
        IPage<SystemUser> systemUserIPage = this.baseMapper.selectPage(systemUserPage, queryWrapper);
        return systemUserIPage;
    }

    @Override
    public List<SystemUser> selectUser(UserSearchDto userSearchDto) {
        LambdaQueryWrapper<SystemUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isBlank(userSearchDto.getUsername()), SystemUser::getUsername, userSearchDto.getUsername());
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public FebsResponse importExcelData(MultipartFile file, HttpServletResponse response) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public void createUser(SystemUser user) {
        // 创建用户
        user.setCreateTime(new Date());
        user.setAvatar(SystemUser.DEFAULT_AVATAR);
        user.setPassword(passwordEncoder.encode(SystemUser.DEFAULT_PASSWORD));
        save(user);
        // 保存用户角色
        String[] roles = user.getRoleId().split(StringPool.COMMA);
        setUserRoles(user, roles);
    }

    @Override
    @Transactional
    public void updateUser(SystemUser user) {
        // 更新用户
        user.setPassword(null);
        user.setUsername(null);
        user.setCreateTime(null);
        user.setModifyTime(new Date());
        updateById(user);

        userRoleService.remove(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getUserId()));
        String[] roles = user.getRoleId().split(StringPool.COMMA);
        setUserRoles(user, roles);
    }

    @Override
    @Transactional
    public void deleteUsers(String[] userIds) {
        List<String> list = Arrays.asList(userIds);
        removeByIds(list);
        // 删除用户角色
        this.userRoleService.deleteUserRolesByUserId(userIds);
    }

    @Override
    public FebsResponse customerImportExcel(MultipartFile file, HttpServletResponse response) {
        Workbook workbook = null;
        try {
            workbook = ParseExcelUtil.getWorkbook(file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            FebsResponse febsResponse = new FebsResponse();
            return febsResponse.message("表格解析错误");
        }
        String sheetName = workbook.getSheetAt(0).getSheetName();
        if ("kadaqia".equals(sheetName)) {
            // 读取表格数据
            List<ExcelFieldModel> list = new ArrayList<ExcelFieldModel>();
            list.add(new ExcelFieldModel("orderNo", 0));
            list.add(new ExcelFieldModel("skuCode", 1));
            list.add(new ExcelFieldModel("asnRecQty", 2));
            list.add(new ExcelFieldModel("skuType", 3));
            list.add(new ExcelFieldModel("recSupply", 4));
            list.add(new ExcelFieldModel("recSource", 5));
            ParseExcelUtil.setExcelFieldModels(list);

            //文件名称
            String originalFilename = file.getOriginalFilename();

            //拿到JSON数据
            List<Map<String, Object>> recDataList = new ArrayList<>();
            try {
                recDataList = ParseExcelUtil.readExcel(file.getInputStream(), originalFilename, 2, -1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (CollectionUtil.isNullList(recDataList)) {
                return new FebsResponse().message("不能上传空文件!").put("result", false);
            } else {
                boolean flag = true;
                for(Map<String, Object> recData : recDataList){
                    StringBuffer stringBuffer = new StringBuffer();
                }
            }

        }
        return null;
    }

    private void setUserRoles(SystemUser user, String[] roles) {
        Arrays.stream(roles).forEach(roleId -> {
            UserRole ur = new UserRole();
            ur.setUserId(user.getUserId());
            ur.setRoleId(Long.valueOf(roleId));
            userRoleService.save(ur);
        });
    }
}