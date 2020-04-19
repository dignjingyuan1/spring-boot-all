package com.flow.snow.snow.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flow.snow.snow.entity.Car;
import com.flow.snow.snow.entity.User;
import com.flow.snow.snow.request.param.RegisterParams;
import com.flow.snow.snow.resultModel.ResultModel;
import com.flow.snow.snow.resultModel.ResultMsgEnum;
import com.flow.snow.snow.service.CarService;
import com.flow.snow.snow.service.UserService;
import com.flow.snow.snow.util.JwtUtil;
import com.flow.snow.snow.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CarService carService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    /**
     * 注册接口
     * @param registerParams
     */
    @RequestMapping(path = "/register",method = RequestMethod.POST)
    @Transactional
    public void register(@RequestBody RegisterParams registerParams){
        Car car = registerParams.getCar();
        User user = registerParams.getUser();
        long carId = carService.insertCar(car);
        user.setCarId(carId);
        userService.insertUser(user);
    }

    /**
     * 登錄接口
     * @param param
     * @return
     */
    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public ResultModel login(@RequestBody Map<String, String> param){
        User user = userService.findUserByNameAndPass(param.get("userName"),param.get("passWord"));
        if (user != null){
            // 生成token
            String token = jwtUtil.createJWT(user.getId().toString(),user.getUserName(),"user");
            UserUtil.setLoginInfo(token,user); // 加入到redis
            Map<String, Object> result = new HashMap<>();
            result.put("user", user);
            result.put("token", token);
            return new ResultModel(ResultMsgEnum.SUCCESS,result);
        }else{
            return new ResultModel(ResultMsgEnum.LOGIN_ERROR,null);
        }

    }

    /**
     * 定位到哪站
     * @param stationId
     */
    @RequestMapping(path = "/location", method = RequestMethod.POST)
    public void location(long stationId){
        User user = UserUtil.getLoginInfo();
        String stationStr = redisTemplate.opsForValue().get("station" + stationId);
        List<User> userList = JSONObject.parseArray(stationStr, User.class);
        userList.add(user);
        stationStr =  JSON.toJSONString(userList);
        redisTemplate.opsForValue().set("station" + stationId,stationStr); // 把人放到车站池
        redisTemplate.opsForValue().set(user.getId() + "loc","station" + stationId); // 表明此人在哪个车站
    }
}
