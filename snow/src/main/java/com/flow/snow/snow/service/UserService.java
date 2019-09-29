package com.flow.snow.snow.service;

import com.flow.snow.snow.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User findByUserName(String userName);
    List<User> findUser(Pageable pageable);
    List<User> getAll();
}
