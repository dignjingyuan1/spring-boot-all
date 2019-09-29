package com.flow.snow.snow.service.impl;

import com.flow.snow.snow.dao.UserDao;
import com.flow.snow.snow.entity.User;
import com.flow.snow.snow.mapper.UserMapper;
import com.flow.snow.snow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    private UserMapper userMapper;
    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public List<User> findUser(Pageable pageable) {
        return userDao.findAll();
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }
}
