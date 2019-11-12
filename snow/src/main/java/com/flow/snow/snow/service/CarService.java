package com.flow.snow.snow.service;

import com.flow.snow.snow.entity.Car;
import org.springframework.stereotype.Service;

public interface CarService {
    Car findCarByCarId(long carId);
    long insertCar(Car car);
}
