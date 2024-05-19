package com.web.studentsystem.services;

import com.web.studentsystem.dto.AdminCreatStudentRequest;
import com.web.studentsystem.entities.Student;

import java.util.List;

public interface AdminService {
    List<Student> getAllStudent();
    Student getStudentByMSV(String msv);
    Student createStudent(AdminCreatStudentRequest creatStudentRequest);
    Student updateStudent(String msv, AdminCreatStudentRequest creatStudentRequest);
    public void deleteByMSV(String msv);
}
