package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    InputStream rs;
    SqlSession session;
    UserDao userDao ;


    @Before
    public void init() throws IOException {

        rs= Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(rs);

        session = factory.openSession();

        userDao = session.getMapper(UserDao.class);


    }

    @After
    public void destory() throws IOException {

        session.commit();

        session.close();
        rs.close();


    }

    @Test
    public void findAll() throws IOException {

        List<User> all = userDao.findAll();
        for (User user : all) {
            System.out.println(user);
        }

    }

    @Test
    public void saveUser(){


        User user = new User();
        user.setUsername("王五");
        user.setAddress("北京");
        user.setSex("男");
        user.setBirthday(new Date());

        userDao.saveUser(user);

    }



}
