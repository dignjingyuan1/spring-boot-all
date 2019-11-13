package com.flow.snow.snow.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flow.snow.snow.entity.Car;
import com.flow.snow.snow.entity.RouteOrder;
import com.flow.snow.snow.entity.User;
import com.flow.snow.snow.request.data.RouteOrderData;
import com.flow.snow.snow.service.CarService;
import com.flow.snow.snow.service.OrderService;
import com.flow.snow.snow.service.UserService;
import com.flow.snow.snow.util.OrderUtil;
import com.flow.snow.snow.util.UserUtil;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * 发布行程
     * @param routeOrder
     */
    @RequestMapping("/createRouteOrder")
    public Long createRouteOrder(RouteOrder routeOrder){
        routeOrder.setOrderNo(OrderUtil.generateOrderNo());
        routeOrder.setDriverId(1L);
        routeOrder.setStatus("0");
        orderService.insertRouteOrder(routeOrder);
        return routeOrder.getId();
    }

    /**
     * 获取订单列表
     * @return
     */
    @RequestMapping("/routeOrderList")
    public List<RouteOrderData> getRouteOrderList(){
        return orderService.findRouteOrderList();
    }
    /**
     * 获取行程详情
     * @param orderId
     * @return
     */
    @RequestMapping("/routeOrderDetail")
    public Map<String,Object> routeOrderDetail(long orderId){
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
    @RequestMapping(path = "/getOnCar", method = RequestMethod.POST)
    public void getOnCar(long orderId, int personCount){
        // 获取当前登录人
        User user = UserUtil.getLoginInfo();
        long userId = user.getId();
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
        this.removeUserByStation(user);
        // TODO 添加到个人行程历史中
    }

    private void removeUserByStation(User user){
        String stationName = redisTemplate.opsForValue().get(user.getId() + "loc");
        String userListStr = redisTemplate.opsForValue().get(stationName);
        List<User> userList = JSONObject.parseArray(userListStr, User.class);
        userList.remove(user);
        redisTemplate.opsForValue().set(stationName,JSON.toJSONString(userList)); // 把人放到车站池
    }
}
