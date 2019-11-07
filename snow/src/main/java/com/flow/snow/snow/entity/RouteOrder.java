package com.flow.snow.snow.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class RouteOrder implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String orderNo;
    @Column(nullable = true)
    private String passengerId;
    @Column(nullable = false)
    private Long driverId;
    @Column(nullable = false)
    private String startStationName;
    @Column(nullable = false)
    private String endStationName;
    @Column(nullable = false)
    private String amount;
    @Column(nullable = false)
    private String direction;
    @Column(nullable = true)
    private Date departureTime;
    @Column(nullable = false)
    private int maxPersonNum;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private Date createTime;
}
