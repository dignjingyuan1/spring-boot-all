package com.flow.snow.snow.service;

import com.flow.snow.snow.entity.RouteOrder;
import com.flow.snow.snow.request.data.RouteOrderData;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {
    int insertRouteOrder(RouteOrder routeOrder);
    RouteOrder findRouteOrderById(long id);
    void updateRouteOrder(RouteOrder routeOrder);
    List<RouteOrderData> findRouteOrderList();
}
