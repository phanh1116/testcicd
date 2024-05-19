package com.web.studentsystem.repository;

import com.web.studentsystem.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepo extends MongoRepository<Student, String> {
    List<Student> findAll();
    Student findByMSV(String msv);

    void deleteByMSV(String msv);
}
