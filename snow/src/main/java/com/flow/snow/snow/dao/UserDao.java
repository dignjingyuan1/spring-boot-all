package com.flow.snow.snow.dao;

import com.flow.snow.snow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByUserNameAndPassWord(String userName, String passWord);

    User findUserById(long id);
}
