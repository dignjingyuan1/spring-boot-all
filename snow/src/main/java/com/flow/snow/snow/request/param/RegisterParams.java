package com.flow.snow.snow.request.param;

import com.flow.snow.snow.entity.Car;
import com.flow.snow.snow.entity.User;

public class RegisterParams {
    private User user;
    private Car car;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
