/*
* This is an implementation for CourseDAO interface. It is automatically initialized and injected by spring boot
*/
package com.edu.lms.dao.impl;

import com.edu.lms.dao.CourseDAO;
import com.edu.lms.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDAOImpl implements CourseDAO {

    EntityManager entityManager;

    @Autowired
    public CourseDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course find_by_id(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public void delete(int id) {
        Course removedCourse = entityManager.find(Course.class, id);
        if(removedCourse == null) {
            throw new RuntimeException("Course not found: No such course in database");
        }
        entityManager.remove(removedCourse);
    }

    @Override
    public List<Course> get_all_courses() {
        TypedQuery<Course> query = entityManager.createQuery("From Course", Course.class);
        return query.getResultList();
    }
}
