package com.edu.lms.service;

import com.edu.lms.dao.CourseDAO;
import com.edu.lms.entity.Course;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private CourseDAO courseDAO;

    @Autowired
    public CourseService(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public List<Course> get_all_courses() {
        return this.courseDAO.get_all_courses();
    }

    @Transactional
    public void add_course(Course addedCourse) {
        this.courseDAO.save(addedCourse);
    }

    public Course get_course(int id) {
        return this.courseDAO.find_by_id(id);
    }

    @Transactional
    public void update_course(Course course, int id) {
        if(this.courseDAO.find_by_id(id) == null) {
            throw new RuntimeException("User not found!: No such course in database");
        }
        course.setId(id);
        this.courseDAO.save(course);
    }
}
