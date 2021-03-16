package com.damu.febs.server.business.controller;

import com.damu.febs.common.service.RedisService;
import com.damu.febs.server.business.data.dto.GoodsInfo;
import com.damu.febs.server.business.data.entity.SeckillOrder;
import com.damu.febs.server.business.data.entity.User;
import com.damu.febs.server.business.data.message.SeckillMessage;
import com.damu.febs.server.business.data.vo.GoodsBo;
import com.damu.febs.server.business.mq.MQSender;
import com.damu.febs.server.business.redis.ConstTime;
import com.damu.febs.server.business.redis.GoodsKey;
import com.damu.febs.server.business.result.CodeMsg;
import com.damu.febs.server.business.result.Result;
import com.damu.febs.server.business.service.SeckillGoodsService;
import com.damu.febs.server.business.service.SeckillOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("seckill")
@Slf4j
public class SecKillController implements InitializingBean {

    @Resource
    private SeckillGoodsService seckillGoodsService;

    @Resource
    private SeckillOrderService seckillOrderService;

    @Autowired
    MQSender mqSender;

    @Autowired
    SeckillGoodsService goodsService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

//    @Resource
//    private RedisTemplateService redisTemplateService;

    @Autowired
    RedisService redisService;

    private HashMap<Long, Boolean> localOverMap = new HashMap<Long, Boolean>();

    /**
     * 系统初始化
     * afterPropertiesSet方法，初始化bean的时候执行，可以针对某个具体的bean进行配置。
     * afterPropertiesSet 必须实现 InitializingBean接口。
     * 实现 InitializingBean接口必须实现afterPropertiesSet方法
     */
    public void afterPropertiesSet() throws Exception {
        List<GoodsBo> goodsList = seckillGoodsService.getSeckillGoodsList();
        if (goodsList == null) {
            return;
        }
//        redisTemplate.opsForValue().set("wa", "666");
//        JedisConnectionFactory connectionFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
//        connectionFactory.setDatabase(3);//选择n号数据库
//        redisTemplate.opsForValue().set("c", "ganxiaomei");
//        log.info(JSON.toJSONString(goodsList));
        for (GoodsBo goods : goodsList) {
//            redisTemplate.opsForValue().set(goods.getId() + "", goods.getStockCount(), ConstTime.RedisCacheExtime.GOODS_LIST);
//            localOverMap.put(goods.getId(), false);
//            redisService.set(GoodsKey.getSeckillGoodsStock, "" + goods.getId(), goods.getStockCount(), ConstTime.RedisCacheExtime.GOODS_LIST);
//            redisService.set()
            localOverMap.put(goods.getId(), false);
        }
        log.info("没啥事，就想看看初始化有没有用得到");
    }

    @PostMapping("/goods")
    @ResponseBody
    public Result doLogin(@RequestBody GoodsInfo goodsInfo) {
        //到时候每个接口做一个拦截token信息
        User userByKey = redisService.getByKey(goodsInfo.getUserKey(), User.class);
        if (userByKey == null) {
            return Result.error(CodeMsg.USER_NO_LOGIN);
        }
        //内存标记，减少redis访问
        Long goodsId = goodsInfo.getGoodsId();
        boolean over = false;
        try {
            over = localOverMap.get(goodsId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (over) {
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }/**/
        //预减库存
        long stock = redisService.decr(GoodsKey.getSeckillGoodsStock, "" + goodsInfo.getGoodsId());
        if (stock < 1) {
            localOverMap.put(goodsInfo.getGoodsId(), true);
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        //判断是否秒杀到
        SeckillOrder order = seckillOrderService.getSeckillOrderByUserIdGoodsId(userByKey.getId(), goodsInfo.getGoodsId());
        if (order != null) {
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
        }
        //
        SeckillMessage mm = new SeckillMessage();
        mm.setUser(userByKey);
        mm.setGoodsId(goodsInfo.getGoodsId());
        mqSender.sendSeckillMessage(mm);
        return Result.success(0);//排队中
    }


    /**
     * 不校验用户，不拦截数量
     *
     * @param goodsInfo
     * @return
     */
    @PostMapping("/secKillNotCheckUserNotMQ")
    @ResponseBody
    public Result secKillNotCheckUserNotMQ(@RequestBody GoodsInfo goodsInfo) {
        //到时候接口做一个拦截token信息
        User userByKey = redisService.getByKey(goodsInfo.getUserKey(), User.class);
        if (userByKey == null) {
            return Result.error(CodeMsg.USER_NO_LOGIN);
        }
        SeckillMessage seckillMessage = new SeckillMessage();
        seckillMessage.setUser(userByKey);
        seckillMessage.setGoodsId(goodsInfo.getGoodsId());
        User user = seckillMessage.getUser();
        long goodsId = seckillMessage.getGoodsId();
        GoodsBo goodsBo = goodsService.getseckillGoodsBoByGoodsId(goodsId);
        Integer stockCount = goodsBo.getStockCount();
        if(0 >= stockCount){
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        //减库存，下订单，写入秒杀订单
        seckillOrderService.insert(user,goodsBo);
        return Result.success(0);//排队中
    }

    /**
     * 不拦截数量,走redis库存
     *
     * @param goodsInfo
     * @return
     */
    @PostMapping("/secKillWithRedis")
    @ResponseBody
    public Result secKillWithRedis(@RequestBody GoodsInfo goodsInfo) {
        //到时候接口做一个拦截token信息
        User userByKey = redisService.getByKey(goodsInfo.getUserKey(), User.class);
        if (userByKey == null) {
            return Result.error(CodeMsg.USER_NO_LOGIN);
        }
        //预减库存,直接减也会出错(redis库存出错，数据库库存没错)，
        long stock = redisService.decr(GoodsKey.getSeckillGoodsStock, "" + goodsInfo.getGoodsId());
        if (stock < 1) {
            localOverMap.put(goodsInfo.getGoodsId(), true);
            return Result.error(CodeMsg.MIAO_SHA_OVER_REDIS);
        }
        SeckillMessage seckillMessage = new SeckillMessage();
        seckillMessage.setUser(userByKey);
        seckillMessage.setGoodsId(goodsInfo.getGoodsId());
        User user = seckillMessage.getUser();
        long goodsId = seckillMessage.getGoodsId();
        GoodsBo goodsBo = goodsService.getseckillGoodsBoByGoodsId(goodsId);
        Integer stockCount = goodsBo.getStockCount();
        if(0 >= stockCount){
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        //减库存，下订单，写入秒杀订单
        seckillOrderService.insert(user,goodsBo);

        return Result.success(0);//排队中
    }

    /**
     * 不拦截数量,不走redis库存，走mq
     *
     * @param goodsInfo
     * @return
     */
    @PostMapping("/secKillWithMq")
    @ResponseBody
    public Result secKillWithMq(@RequestBody GoodsInfo goodsInfo) {
        //到时候接口做一个拦截token信息
        User userByKey = redisService.getByKey(goodsInfo.getUserKey(), User.class);
        if (userByKey == null) {
            return Result.error(CodeMsg.USER_NO_LOGIN);
        }
        //预减库存,直接减也会出错，
//        long stock = redisService.decr(GoodsKey.getSeckillGoodsStock, "" + goodsInfo.getGoodsId());
//        if (stock < 1) {
//            localOverMap.put(goodsInfo.getGoodsId(), true);
//            return Result.error(CodeMsg.MIAO_SHA_OVER_REDIS);
//        }
//        SeckillMessage seckillMessage = new SeckillMessage();
//        seckillMessage.setUser(userByKey);
//        seckillMessage.setGoodsId(goodsInfo.getGoodsId());
//        User user = seckillMessage.getUser();
//        long goodsId = seckillMessage.getGoodsId();
//        GoodsBo goodsBo = goodsService.getseckillGoodsBoByGoodsId(goodsId);
//        Integer stockCount = goodsBo.getStockCount();
//        if(0 >= stockCount){
//            return Result.error(CodeMsg.MIAO_SHA_OVER);
//        }
//        //减库存，下订单，写入秒杀订单
//        seckillOrderService.insert(user,goodsBo);
        SeckillMessage mm = new SeckillMessage();
        mm.setUser(userByKey);
        mm.setGoodsId(goodsInfo.getGoodsId());
        mqSender.sendSeckillMessageOnlyMQ(mm);
        return Result.success(0);//排队中
    }
}