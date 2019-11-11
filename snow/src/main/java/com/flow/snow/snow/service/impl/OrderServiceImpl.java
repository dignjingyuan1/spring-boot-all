package com.flow.snow.snow.service.impl;

import com.flow.snow.snow.dao.OrderDao;
import com.flow.snow.snow.entity.RouteOrder;
import com.flow.snow.snow.mapper.OrderMapper;
import com.flow.snow.snow.request.data.RouteOrderData;
import com.flow.snow.snow.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderDao orderDao;
    @Override
    public int insertRouteOrder(RouteOrder routeOrder) {
        return orderMapper.insertRouteOrder(routeOrder);
    }

    @Override
    public RouteOrder findRouteOrderById(long id) {
        return orderDao.findById(id);
    }

    @Override
    public void updateRouteOrder(RouteOrder routeOrder) {
        orderMapper.updateRouteOrder(routeOrder);
    }

    @Override
    public List<RouteOrderData> findRouteOrderList() {
        return orderMapper.findRouteOrderList();
    }
}
