package com.flow.snow.snow.dao;

import com.flow.snow.snow.entity.Car;
import com.flow.snow.snow.entity.RouteOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDao extends JpaRepository<Car, Long> {
    Car findCarById(long carId);
}
