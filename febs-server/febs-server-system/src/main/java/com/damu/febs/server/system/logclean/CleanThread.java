package com.damu.febs.server.system.logclean;

import com.damu.febs.server.system.data.bean.LogCleanBean;
import com.damu.febs.server.system.service.LogCleanService;

/**
 * @PACKAGE_NAME: com.lyl.logclean
 * @ClassName: CleanThread
 * @Description:  实际进行日志清理时的线程
 **/
public class CleanThread implements Runnable{

    private LogCleanService logCleanService;

    private LogCleanBean logCleanBean;

    public CleanThread(LogCleanService logCleanService, LogCleanBean logCleanBean) {
        this.logCleanService = logCleanService;
        this.logCleanBean = logCleanBean;
    }

    @Override
    public void run() {
        logCleanService.logBatchClean(logCleanBean);
        // hellp GC
        logCleanBean = null;
    }
}
