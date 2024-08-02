package com.github.AlexSurkov312.service;


import com.github.AlexSurkov312.dao.UserDao;
import com.github.AlexSurkov312.model.User;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {


    private final UserDao dao;

    @Autowired
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    @Transactional
    @Override
    public void saveUser(String name, String lastName, int age) throws HibernateException {
        dao.saveUser(name, lastName, age);
    }

    @Transactional
    @Override
    public void removeUserById(long id) throws HibernateException {
        dao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() throws HibernateException {
        return dao.getAllUsers();
    }

    @Override
    public User getUser(long id) {
        return dao.getUser(id);
    }

    @Transactional
    @Override
    public void updateUser(long id, String newName, int newAge, String newSurname) {
        dao.updateUser(id, newName, newAge, newSurname);
    }
}
