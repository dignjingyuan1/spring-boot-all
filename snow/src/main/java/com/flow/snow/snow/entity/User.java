package com.flow.snow.snow.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String nickName;
    @Column(nullable = false)
    private String passWord;
    @Column(nullable = true)
    private String headImg;
    @Column(nullable = false)
    private String sex;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String contactNumber;
    @Column(nullable = false)
    private long carId;
    @Column(nullable = false)
    private String weChat;
}
