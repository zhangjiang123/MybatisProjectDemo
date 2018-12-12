package com.itheima.test;


import com.itheima.dao.UserDao;
import com.itheima.domain.QueryVo;
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


    InputStream   is = null;

    SqlSession session =null;

    UserDao userDao = null;

    @Before
    public void init() throws Exception{


        is= Resources.getResourceAsStream("SqlMapConfig.xml");

        SqlSessionFactory factory= new SqlSessionFactoryBuilder().build(is);

        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);



    }

    @After
    public void destory() throws IOException {


        session.commit();
        session.close();
        is.close();

    }


    @Test
    public void findAllTest()  {

        List<User> users = userDao.findAll();

        for (User user : users) {
            System.out.println(user);
        }

    }


    @Test
    public void saveUserTest(){

        User user = new User();
        user.setUserAddress("温州");
        user.setUserBirthday(new Date());
        user.setUserName("张三");
        user.setUserSex("男");

        System.out.println(user);

        userDao.saveUser(user);

        System.out.println(user);


    }

    @Test
    public void updateTest(){

        User user = new User();
        user.setUserId(50);
        user.setUserAddress("温州");
        user.setUserBirthday(new Date());
        user.setUserName("李四");
        user.setUserSex("男");



        userDao.updateUser(user);

        System.out.println(user);



    }

    @Test
    public void delectTest(){

        userDao.delectUser(51);


    }

    @Test
    public void findByIdTest(){


        User user = userDao.findById(46);
        System.out.println(user);

    }
    @Test
    public void findByNameTest(){

        List<User> users = userDao.findByName("王");
        for (User user : users) {
            System.out.println(user);
        }

    }
    @Test
    public void findByVoTest(){

        QueryVo vo = new QueryVo();
        User user = new User();

        user.setUserName("%王%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for (User u : users) {
            System.out.println(u);
        }

    }



}
