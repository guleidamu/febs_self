package com.damu.febs.server.task.helper;

import com.damu.febs.server.task.data.entity.JobLog;
import com.damu.febs.server.task.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

@Slf4j
public class ScheduleCallable implements Callable<Map> {

    private final Object target;
    private final Method method;
    private final String params;

    ScheduleCallable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
        this.target = SpringContextUtil.getBean(beanName);
        this.params = params;

        if (StringUtils.isNotBlank(params)) {
            this.method = target.getClass().getDeclaredMethod(methodName, String.class);
        } else {
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public Map call() {
        Map<String, String> resultMap = new HashMap<>();
        try {
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.isNotBlank(params)) {
                method.invoke(target, params);
            } else {
                method.invoke(target);
            }
            resultMap.put("result", JobLog.JOB_SUCCESS);
            resultMap.put("message", "");
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", JobLog.JOB_FAIL);
            resultMap.put("message", e.toString());
        }
        return resultMap;
    }
}