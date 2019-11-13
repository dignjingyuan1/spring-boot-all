package com.flow.snow.snow.controller;

import com.flow.snow.snow.entity.Car;
import com.flow.snow.snow.entity.User;
import com.flow.snow.snow.request.param.RegisterParams;
import com.flow.snow.snow.service.CarService;
import com.flow.snow.snow.service.UserService;
import com.flow.snow.snow.util.JwtUtil;
import com.flow.snow.snow.util.UserUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public Object login(String userName, String passWord){
        User user = userService.findUserByNameAndPass(userName,passWord);
        // 生成token
        String token = jwtUtil.createJWT(user.getId().toString(),user.getUserName(),"user");
        UserUtil.setLoginInfo(token,user); // 加入到redis
        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("token", token);
        return  result;
    }
}
