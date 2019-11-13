package com.flow.snow.snow.config;

import com.flow.snow.snow.entity.Station;
import com.flow.snow.snow.service.StationService;
import com.flow.snow.snow.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StationLoad implements ApplicationRunner {
    @Autowired
    StationService stationService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 加载车站信息到 redis 并创建每个站点儿池
        System.out.println("开始load站点池");
        List<Station> stations = stationService.findAll();
        for (Station station : stations){
            redisTemplate.opsForValue().set(station.getName(),"[]");
        }
    }
}
