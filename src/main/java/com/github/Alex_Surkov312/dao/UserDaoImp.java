package com.github.Alex_Surkov312.dao;

import com.github.Alex_Surkov312.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public void saveUser(String name, String lastName, int age) {
        manager.persist(new User(name, lastName, age));
    }

    @Override
    public void removeUserById(long id) {
        TypedQuery<User> typedQuery = manager.createQuery("select u from User u where u.id = :id", User.class);
        typedQuery.setParameter("id", id);
        manager.remove(typedQuery.getResultList().stream().findAny().orElse(null));
    }

    @Override
    public List<User> getAllUsers() {
        return manager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUser(long id) {
        TypedQuery<User> typedQuery = manager.createQuery("select u from User u where u.id = :id", User.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();

    }

    @Override
    public void updateUser(long id, String newName, int newAge, String newSurname) {
        TypedQuery<User> typedQuery = manager.createQuery("select u from User u where u.id = :id", User.class);
        User user = typedQuery.setParameter("id", id).getSingleResult();
        user.setName(newName);
        user.setLastName(newSurname);
        user.setAge(newAge);
    }

}
