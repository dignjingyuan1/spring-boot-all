package com.flow.snow.snow.service.impl;

import com.flow.snow.snow.dao.CarDao;
import com.flow.snow.snow.entity.Car;
import com.flow.snow.snow.mapper.CarMapper;
import com.flow.snow.snow.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarDao carDao;
    @Autowired
    CarMapper carMapper;


    @Override
    public Car findCarByCarId(long carId) {
        return carDao.findCarById(carId);
    }

    @Override
    public long insertCar(Car car) {
        return carMapper.insertCar(car);
    }
}
