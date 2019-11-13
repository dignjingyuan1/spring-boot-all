package com.flow.snow.snow.service;

import com.flow.snow.snow.entity.RouteOrder;
import com.flow.snow.snow.request.data.RouteOrderData;

import java.util.List;

public interface OrderService {
    void insertRouteOrder(RouteOrder routeOrder);
    RouteOrder findRouteOrderById(long id);
    void updateRouteOrder(RouteOrder routeOrder);
    List<RouteOrderData> findRouteOrderList();
}
