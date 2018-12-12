package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

import java.util.List;

public interface UserDao {

     List<User> findAll();

     void saveUser(User user);

     void updateUser(User user);

     void delectUser(Integer id);

     User findById(Integer id);

     List<User> findByName(String name);

     List<User> findUserByVo(QueryVo vo);
}
