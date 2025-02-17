/*
* An interface for course's transactions
*/

package com.edu.lms.dao;

import com.edu.lms.entity.Course;

import java.util.List;

public interface CourseDAO {

    public void save(Course course);

    public Course find_by_id(int id);

    public void delete(int id);

    public List<Course> get_all_courses();
}
