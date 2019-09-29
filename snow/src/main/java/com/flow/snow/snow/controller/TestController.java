package com.flow.snow.snow.controller;

import com.flow.snow.snow.config.ApplicationConfig;
import com.flow.snow.snow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Set;
import java.util.UUID;


@RestController
public class TestController {
    @Autowired
    ApplicationConfig applicationConfig;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping("/hello")
    public Object index() {
        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add("set1","22");
        set.add("set1","33");
        set.add("set1","44");
        Set<String> resultSet =redisTemplate.opsForSet().members("set1");
        System.out.println("resultSet:"+resultSet);

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(1,10, sort);
        return userService.findUser(pageable);
    }

    @RequestMapping("/getRedis")
    public Object getRedis(){
        Object obj = redisTemplate.opsForValue().get("userAll");
        return obj;
    }

    @RequestMapping("/getAll")
    public Object getAll(){
        return userService.getAll();
    }

    @RequestMapping("/uid")
    public String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }
}
