package com.flow.snow.snow.request.data;

import lombok.Data;

import java.util.Date;

@Data
public class RouteOrderData {
    private Long id;
    private String orderNo;
    private String passengerId;
    private Long driverId;
    private String startStationName;
    private String endStationName;
    private String amount;
    private String direction;
    private Date departureTime;
    private int maxPersonNum;
    private String status;
    private Date createTime;

    private Long userId;
    private String userName;
    private String nickName;
    private String headImg;
    private String sex;
    private String contactNumber;
    private String weChat;

    private long carId;
    private String carNo;
    private String carColor;
    private String carType;
    private String carBrand;


}
