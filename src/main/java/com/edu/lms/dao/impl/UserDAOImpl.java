/*
* This is an implementation for UserDAO interface. It is automatically initialized and injected by spring boot
*/

package com.edu.lms.dao.impl;

import com.edu.lms.dao.UserDAO;
import com.edu.lms.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    // Inject the implementation object created by spring
    @Autowired
    public  UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // This method can be used for both creating a new user or updating the data of an existing one.
    // It depends on the id of the user
    @Override
    public void save(User user) {
        entityManager.merge(user);
    }


    @Override
    public User find_by_id(int id) {
        return entityManager.find(User.class, id);
    }

    // To delete some user given their id, search for them and then remove if exist
    @Override
    public void delete(int id) {
        User removedUser = entityManager.find(User.class, id);
        if(removedUser == null) {
            throw new RuntimeException("User not found: No such user in database");
        }

        entityManager.remove(removedUser);
    }

    // query all users in database
    @Override
    public List<User> get_all_users() {
        TypedQuery<User> query = entityManager.createQuery("From User", User.class);
        return query.getResultList();
    }

}
