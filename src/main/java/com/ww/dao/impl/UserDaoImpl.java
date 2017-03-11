package com.ww.dao.impl;

import com.ww.common.BaseDaoImpl;
import com.ww.dao.UserDao;
import com.ww.entity.User;
import lombok.extern.log4j.Log4j;

import java.util.List;

/**
 * Created by ww on 17/3/11.
 */
@Log4j
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    @Override
    public User login(User user) {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void addUser(String userName) {

    }

    @Override
    public void deleteUser(String userName) {

    }

    @Override
    public void deleteAll() {

    }
}
