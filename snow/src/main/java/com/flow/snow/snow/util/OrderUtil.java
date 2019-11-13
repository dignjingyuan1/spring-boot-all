package com.flow.snow.snow.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class OrderUtil {

    private static RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        OrderUtil.redisTemplate = redisTemplate;
    }

    public static String generateOrderNo() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddkkmmss"); // 时分秒
        String orderHead = df.format(new Date());
        String orderIndex = redisTemplate.opsForValue().get("orderIndex"); // 获取序号
        if (StringUtils.isEmpty(orderIndex)) { // 没有序号就从1 开始
            orderIndex = "1";
        }
        String result = "XLC" + orderHead + "-" + orderIndex;
        int i = Integer.parseInt(orderIndex);
        String index = i + 1 + "";
        redisTemplate.opsForValue().set("orderIndex", index);
        return result;
    }

    @Scheduled(cron="0 0 0 * * ?")
    private void process(){
        System.out.println("重置订单序号：" + redisTemplate.opsForValue().get("orderIndex"));
        redisTemplate.opsForValue().set("orderIndex", "1");
    }
}
