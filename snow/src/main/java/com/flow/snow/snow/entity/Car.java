package com.flow.snow.snow.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
@Entity
@Data
public class Car implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String carNo;
    @Column(nullable = false)
    private String carColor;
    @Column(nullable = false)
    private String carType;
    @Column(nullable = false)
    private String carBrand;
}
