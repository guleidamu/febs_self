package com.damu.febs.server.task.controller;


import com.damu.febs.common.entity.StringConstant;
import com.damu.febs.server.task.data.entity.Job;
import com.damu.febs.server.task.service.IJobLogService;
import com.damu.febs.server.task.service.IJobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author MrBird
 */
@Slf4j
@Validated
@RestController
@RequestMapping("taskDemo")
@RequiredArgsConstructor
public class JobController {

    private final IJobService jobService;

    @Resource
    private IJobLogService iJobLogService;

//    @GetMapping
//    @PreAuthorize("hasAuthority('job:view')")
//    public FebsResponse jobList(QueryRequest request, Job job) {
//        Map<String, Object> dataTable = FebsUtil.getDataTable(this.jobService.findJobs(request, job));
//        return new FebsResponse().data(dataTable);
//    }

    @GetMapping("cron/check")
    public boolean checkCron(String cron) {
        try {
            return CronExpression.isValidExpression(cron);
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('job:add')")
    public void addJob(@Valid Job job) {
        this.jobService.createJob(job);
    }

    @DeleteMapping("{jobIds}")
    @PreAuthorize("hasAuthority('job:delete')")
    public void deleteJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        String[] ids = jobIds.split(StringConstant.COMMA);
        this.jobService.deleteJobs(ids);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('job:update')")
    public void updateJob(@Valid Job job) {
        this.jobService.updateJob(job);
    }

    @GetMapping("run/{jobIds}")
    @PreAuthorize("hasAuthority('job:run')")
    public void runJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.run(jobIds);
    }

    @GetMapping("pause/{jobIds}")
    public void pauseJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.pause(jobIds);
    }

    @GetMapping("resume/{jobIds}")
    @PreAuthorize("hasAuthority('job:resume')")
    public void resumeJob(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.resume(jobIds);
    }

    @GetMapping("pauseJobById")
    public void pauseJobById(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        this.jobService.pause(jobIds);
    }

    @PostMapping("po")
    public String po() {
//        this.jobService.pause(jobIds);
        return "poTest";
    }

    @GetMapping("getT")
    public String getT() {
//        this.jobService.pause(jobIds);
        return "poTest";
    }

    @GetMapping("/test2")
    public String test2() {

        return "succe56ss";
    }

//    @PostMapping("excel")
//    @PreAuthorize("hasAuthority('job:export')")
//    public void export(QueryRequest request, Job job, HttpServletResponse response) {
//        List<Job> jobs = this.jobService.findJobs(request, job).getRecords();
//        ExcelKit.$Export(Job.class, response).downXlsx(jobs, false);
//    }
}
