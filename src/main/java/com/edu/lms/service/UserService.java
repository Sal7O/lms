package com.edu.lms.service;

import com.edu.lms.dao.UserDAO;
import com.edu.lms.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public List<User> get_all_users() {
        return userDAO.get_all_users();
    }

    public User find_user(int id) {
        return userDAO.find_by_id(id);
    }

    @Transactional
    public void add_new_user(User user) {
        userDAO.save(user);
    }

    @Transactional
    public void update_user(User user, int id) {
        if(userDAO.find_by_id(id) == null) {
            throw new RuntimeException("User not found!: No such user in database");
        }
        user.setId(id);
        userDAO.save(user);
    }
}
