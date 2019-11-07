package com.flow.snow.snow.service;

import com.flow.snow.snow.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    void insertUser(User user);
    User findUserByNameAndPass(String username, String password);
    User findUserById(long id);
}
