package com.flow.snow.snow.dao;

import com.flow.snow.snow.entity.RouteOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<RouteOrder, Long> {
    RouteOrder findById(long id);
}
