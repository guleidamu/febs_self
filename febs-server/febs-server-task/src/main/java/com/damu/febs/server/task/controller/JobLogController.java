package com.damu.febs.server.task.controller;


import com.damu.febs.common.entity.FebsResponse;
import com.damu.febs.common.entity.QueryRequest;
import com.damu.febs.common.entity.StringConstant;
import com.damu.febs.common.utils.FebsUtil;
import com.damu.febs.server.task.data.entity.JobLog;
import com.damu.febs.server.task.service.IJobLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author MrBird
 */
@Slf4j
@Validated
@RestController
@RequestMapping("log")
@RequiredArgsConstructor
public class JobLogController {

    private final IJobLogService jobLogService;

    @GetMapping
    @PreAuthorize("hasAuthority('job:log:view')")
    public FebsResponse jobLogList(QueryRequest request, JobLog log) {
        Map<String, Object> dataTable = FebsUtil.getDataTable(this.jobLogService.findJobLogs(request, log));
        return new FebsResponse().data(dataTable);
    }

    @DeleteMapping("{jobIds}")
    @PreAuthorize("hasAuthority('job:log:delete')")
    public void deleteJobLog(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        String[] ids = jobIds.split(StringConstant.COMMA);
        this.jobLogService.deleteJobLogs(ids);
    }

//    @GetMapping("excel")
//    @PreAuthorize("hasAuthority('job:log:export')")
//    public void export(QueryRequest request, JobLog jobLog, HttpServletResponse response) {
//        List<JobLog> jobLogs = this.jobLogService.findJobLogs(request, jobLog).getRecords();
//        ExcelKit.$Export(JobLog.class, response).downXlsx(jobLogs, false);
//    }
}
