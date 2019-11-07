package com.flow.snow.snow.controller;

import com.flow.snow.snow.entity.Car;
import com.flow.snow.snow.entity.User;
import com.flow.snow.snow.request.param.RegisterParams;
import com.flow.snow.snow.service.CarService;
import com.flow.snow.snow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CarService carService;

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
    private User register(String userName, String passWord){
        return userService.findUserByNameAndPass(userName, passWord);
    }


}
