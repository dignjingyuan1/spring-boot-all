package com.flow.snow.snow.controller;

import com.flow.snow.snow.entity.Car;
import com.flow.snow.snow.entity.RouteOrder;
import com.flow.snow.snow.entity.User;
import com.flow.snow.snow.service.CarService;
import com.flow.snow.snow.service.OrderService;
import com.flow.snow.snow.service.UserService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    CarService carService;

    /**
     * 发布行程
     * @param routeOrder
     */
    @RequestMapping("/createRouteOrder")
    private void createRouteOrder(RouteOrder routeOrder){
        routeOrder.setOrderNo("XLC"+Math.random()*10000000000000000L);
        routeOrder.setDriverId(1L);
        routeOrder.setStatus("0");
        int id = orderService.insertRouteOrder(routeOrder);
        System.out.println(id);
    }

    /**
     * 获取行程详情
     * @param orderId
     * @return
     */
    @RequestMapping("/routeOrderDetail")
    private Map<String,Object> routeOrderDetail(long orderId){
        RouteOrder routeOrder = orderService.findRouteOrderById(orderId);
        User user = userService.findUserById(routeOrder.getDriverId());
        Car car = carService.findCarByCarId(user.getCarId());
        Map<String,Object> result = new HashMap<>();
        result.put("driverInfo",user);
        result.put("orderInfo",routeOrder);
        result.put("carInfo",car);
        return result;
    }

    /**
     * 我要上车
     * @param orderId
     * @param personCount
     */
    @RequestMapping("/getOnCar")
    private void getOnCar(long orderId, int personCount){
        // TODO 获取当前登录人
        long userId = 1L;
        // 获取订单
        RouteOrder routeOrder = orderService.findRouteOrderById(orderId);
        // 把当前用户id拼到坐车人中
        String passengerIds = routeOrder.getPassengerId();
        String passRes = "";
        if(org.springframework.util.StringUtils.isEmpty(passengerIds)){
            passRes = userId+"";
        }else{
            String[] passengerIdArr = passengerIds.split(",");
            List<String> passengerIdList = new ArrayList<String>();
            passengerIdList.addAll(Arrays.asList(passengerIdArr));
            passengerIdList.add(userId + "");
            passRes = StringUtils.join(passengerIdList, ',');
        }
        routeOrder.setPassengerId(passRes);
        // 把订单座位数-1
        int personNum = routeOrder.getMaxPersonNum() - personCount;
        routeOrder.setMaxPersonNum(personNum);
        // 更新订单
        orderService.updateRouteOrder(routeOrder);
        // TODO 本站点人数-1
        // TODO 添加到个人行程历史中
    }
}
