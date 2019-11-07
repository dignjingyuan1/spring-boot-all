package com.flow.snow.snow.service;

import com.flow.snow.snow.entity.RouteOrder;

public interface OrderService {
    int insertRouteOrder(RouteOrder routeOrder);
    RouteOrder findRouteOrderById(long id);
    void updateRouteOrder(RouteOrder routeOrder);
}
