package com.github.Alex_Surkov312.dao;


import com.github.Alex_Surkov312.model.User;

import java.util.List;

public interface UserDao {

    void saveUser(String name, String lastName, int age);

    void removeUserById(long id);

    List<User> getAllUsers();

    User getUser(long id);

    void updateUser(long id, String newName, int newAge, String newSurname);
}
