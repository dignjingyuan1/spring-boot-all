package com.flow.snow.snow.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class TravelHistory implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String orderNo;
    @Column(nullable = false)
    private String travelId;
    @Column(nullable = false)
    private String passengerId;
    @Column(nullable = false)
    private String startStationName;
    @Column(nullable = false)
    private String endStationName;
    @Column(nullable = false)
    private String carNo;
}
