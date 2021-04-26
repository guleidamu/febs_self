package com.damu.febs.server.system.listener;

import com.damu.febs.server.system.logclean.CleanManager;
import com.damu.febs.server.system.logpool.LogPoolManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @PACKAGE_NAME: com.lyl.listener
 * @ClassName: InitServletContextListener
 * @Description: 项目启动监听器，启动时进行数据初始化、以及启动各模块组件
 **/
@Slf4j
@WebListener
public class InitServletContextListener implements ServletContextListener {

    @Autowired
    private LogPoolManager logPoolManager;

    @Autowired
    private CleanManager cleanManager;

    /**
     * 当Servlet 容器启动Web 应用时调用该方法。在调用完该方法之后，容器再对Filter 初始化，
     * 并且对那些在Web 应用启动时就需要被初始化的Servlet 进行初始化。
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 日志 异步池化处理 启动
        logPoolManager.init();
        log.info(" 日志异步池化处理启动成功. . . . . . .");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 关闭线程池
        logPoolManager.shutdown();
        cleanManager.shutdown();
    }
}
