package com.flow.snow.snow.request.param;

import com.flow.snow.snow.entity.Car;
import com.flow.snow.snow.entity.User;
import lombok.Data;

@Data
public class RegisterParams {
    private User user;
    private Car car;
}
