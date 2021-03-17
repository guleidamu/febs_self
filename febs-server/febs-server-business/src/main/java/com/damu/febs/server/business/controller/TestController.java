package com.damu.febs.server.business.controller;


import com.damu.febs.server.business.Thread.ThreadClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/test")
@Slf4j
public class TestController implements InitializingBean {

    @Resource
    private ThreadClient threadClient;

    @Value("${server.port}")
    private int port;

    /**
     * 系统初始化
     * afterPropertiesSet方法，初始化bean的时候执行，可以针对某个具体的bean进行配置。
     * afterPropertiesSet 必须实现 InitializingBean接口。
     * 实现 InitializingBean接口必须实现afterPropertiesSet方法
     */
    public void afterPropertiesSet() throws Exception {
//        List<GoodsBo> goodsList = seckillGoodsService.getSeckillGoodsList();
//        if (goodsList == null) {
//            return;
//        }
//        for (GoodsBo goods : goodsList) {
//            redisService.set(GoodsKey.getSeckillGoodsStock, "" + goods.getId(), goods.getStockCount(), Const.RedisCacheExtime.GOODS_LIST);
//            localOverMap.put(goods.getId(), false);
//        }
        log.info("没啥事，就想看看初始化有没有用得到");
    }

    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi "+name+",i am from port:" + port ;
    }

    @GetMapping("/test")
    public String test() {
        return "hi i am test" ;
    }


    @GetMapping("/threadClient")
    public void threadClient(){
        threadClient.crawlRedisQueue();
    }
}
