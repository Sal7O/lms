/*
* An interface for user's transactions
*/

package com.edu.lms.dao;

import com.edu.lms.entity.User;

import java.util.List;

public interface UserDAO {

    public void save(User user);

    public User find_by_id(int id);

    public void delete(int id);

    public List<User> get_all_users();
}
