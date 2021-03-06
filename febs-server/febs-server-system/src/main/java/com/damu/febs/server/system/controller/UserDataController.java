package com.damu.febs.server.system.controller;

import com.damu.febs.common.data.dto.UserSearchDto;
import com.damu.febs.common.entity.ExcelFieldModel;
import com.damu.febs.common.entity.FebsResponse;
import com.damu.febs.common.entity.QueryRequest;
import com.damu.febs.common.entity.system.SystemUser;
import com.damu.febs.common.utils.ExportExcelUtil;
import com.damu.febs.common.utils.FebsUtil;
import com.damu.febs.server.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Validated
@RestController
@RequestMapping("userData")
public class UserDataController {


    @Autowired
    private IUserService userService;

    @GetMapping(value = "/userListByPage")
    public FebsResponse userListByPage(@RequestBody UserSearchDto userSearchDto) {
        log.info("进入serverSystem.userList");
        Map<String, Object> dataTable = FebsUtil.getDataTable(userService.queryUserDetail(userSearchDto));
        return new FebsResponse().data(dataTable);
    }

    @GetMapping(value = "/userList")
    public FebsResponse userList(@RequestBody UserSearchDto userSearchDto) {
        log.info("进入serverSystem.userList");
        List<SystemUser> systemUsers = userService.selectUser((userSearchDto));
        return new FebsResponse().data(systemUsers);
    }

    @PostMapping(value = "/downLoadUserList")
    public void downLoadUserList(HttpServletResponse response, @RequestBody UserSearchDto userSearchDto) {
        log.info("进入serverSystem.userList下载");
        ExportExcelUtil exportExcelUtil = new ExportExcelUtil<>();
        List<SystemUser> systemUsers = userService.selectUser((userSearchDto));
        List<Map<String, Object>> dataset = new ArrayList<>();
        for(int i = 0; i< systemUsers.size(); i++){
            SystemUser systemUser = systemUsers.get(i);
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("username", systemUser.getUsername());
            hashMap.put("roleId", systemUser.getRoleId());
            hashMap.put("status", systemUser.getStatus());
            hashMap.put("avatar", systemUser.getAvatar());
            hashMap.put("deptName", systemUser.getDeptName());
            hashMap.put("description", systemUser.getDescription());
            hashMap.put("email", systemUser.getEmail());
            hashMap.put("lastLoginTime", systemUser.getLastLoginTime());
            hashMap.put("sex", systemUser.getSex());
            hashMap.put("mobile", systemUser.getMobile());
            dataset.add(hashMap);
        }
        List<ExcelFieldModel> list = new ArrayList<ExcelFieldModel>();
        list.add(new ExcelFieldModel(0, "username", "用户名", 10));
        list.add(new ExcelFieldModel(1, "roleId", "角色id", 16));
        list.add(new ExcelFieldModel(2, "status", "状态", 10));
        list.add(new ExcelFieldModel(3, "avatar", "avatar", 10));
        list.add(new ExcelFieldModel(4, "deptName", "部门名称", 10));
        list.add(new ExcelFieldModel(5, "description", "客户订单", 10));
        list.add(new ExcelFieldModel(6, "email", "下单日期", 10));
        list.add(new ExcelFieldModel(7, "lastLoginTime", "SKU", 12));
        list.add(new ExcelFieldModel(8, "sex", "品质分类", 10));
        list.add(new ExcelFieldModel(9, "mobile", "品质分类", 10));
        try {
            exportExcelUtil.setExcelFieldModels(list);
            exportExcelUtil.exportExcel(dataset, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @PostMapping("exporeExcel")
    public void exporeExcel(HttpServletResponse response) throws IOException{
        String fileName = "/templates/入库表格.xlsx";
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        InputStream inputStream = classPathResource.getInputStream();
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", String.format("attachment; filename=%s", "入库单模板测试.xlsx"));
        response.addHeader("Access-Control-Expose-headers","Content-Disposition");
        wb.write(outputStream);
        outputStream.close();
        inputStream.close();
    }

    @PostMapping("importExcel")
    public FebsResponse importExcel(MultipartFile multipartFile, HttpServletResponse response) throws IOException{
        return userService.customerImportExcel(multipartFile, response);
//        String fileName = "/templates/入库表格.xlsx";
//        ClassPathResource classPathResource = new ClassPathResource(fileName);
//        InputStream inputStream = classPathResource.getInputStream();
//        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
//        ServletOutputStream outputStream = response.getOutputStream();
//        response.setContentType("application/vnd.ms-excel");
//        response.setHeader("Content-disposition", String.format("attachment; filename=%s", "入库单模板测试.xlsx"));
//        response.addHeader("Access-Control-Expose-headers","Content-Disposition");
//        wb.write(outputStream);
//        outputStream.close();
//        inputStream.close();
    }



}
