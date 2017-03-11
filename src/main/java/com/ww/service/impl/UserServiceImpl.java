package com.ww.service.impl;

import com.ww.dao.UserDao;
import com.ww.entity.User;
import com.ww.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
@Log4j
public class UserServiceImpl implements UserService {

    //public List<User> userList = getAllUsers();

    @Resource
    private UserDao userDao;

    public User login(User user) {
        return userDao.login(user);
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }


    public List<User> getAllUsers() {
        log.info("userid is null");
        return userDao.getAllUsers();
    }


    public void addUser(String userName) {
        userDao.addUser(userName);
    }


    public void deleteUser(String userName) {
        userDao.deleteUser(userName);
    }


    public void deleteAll() {
        userDao.deleteAll();
    }

    public void testUser(String userName) {

    }

}
