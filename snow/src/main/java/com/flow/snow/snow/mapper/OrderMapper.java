package com.flow.snow.snow.mapper;

import com.flow.snow.snow.entity.RouteOrder;
import com.flow.snow.snow.request.data.RouteOrderData;

import java.util.List;

public interface OrderMapper {
    int insertRouteOrder(RouteOrder routeOrder);
    void updateRouteOrder(RouteOrder routeOrder);
    List<RouteOrderData> findRouteOrderList();
}
