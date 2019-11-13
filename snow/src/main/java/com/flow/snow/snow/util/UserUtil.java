package com.flow.snow.snow.util;

import com.alibaba.fastjson.JSONObject;
import com.flow.snow.snow.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserUtil {

    private static RedisTemplate<String, String> redisTemplate;

        @Autowired
        public void setRedisTemplate(RedisTemplate redisTemplate) {
            UserUtil.redisTemplate = redisTemplate;
        }

        /**
         * 获取当前登录人
         * @return
         */
    public static User getLoginInfo(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String tokenStr = request.getHeader("accessToken");
        String userStr = redisTemplate.opsForValue().get(tokenStr);
        User user = JSONObject.parseObject(userStr,User.class);
        return user;
    }

    /**
     * 设置当前登录人
     * @param token
     * @param user
     */
    public static void setLoginInfo(String token, User user){
        String userStr = JSONObject.toJSONString(user);
        redisTemplate.opsForValue().set(token, userStr);
    }
}
