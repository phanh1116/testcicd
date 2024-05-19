package com.web.studentsystem.services.impl;

import com.web.studentsystem.dto.AdminCreatStudentRequest;
import com.web.studentsystem.entities.Student;
import com.web.studentsystem.repository.StudentRepo;
import com.web.studentsystem.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final StudentRepo studentRepo;
    public List<Student> getAllStudent(){
        return studentRepo.findAll();
    }
    public Student getStudentByMSV(String msv) {
        return studentRepo.findByMSV(msv);
    }
    public Student createStudent(AdminCreatStudentRequest creatStudentRequest) {
        if(studentRepo.findByMSV(creatStudentRequest.getMSV()) != null) {
            return null;
        }
        Student student = new Student(creatStudentRequest.getMSV(), creatStudentRequest.getHoTen(), creatStudentRequest.getNgaySinh(), creatStudentRequest.getLop(), creatStudentRequest.getDiem());
        return (Student) studentRepo.save(student);
    }
    public Student updateStudent(String msv, AdminCreatStudentRequest creatStudentRequest) {
        Student student = studentRepo.findByMSV(msv);
        student.setHoTen(creatStudentRequest.getHoTen());
        student.setNgaySinh(creatStudentRequest.getNgaySinh());
        student.setLop(creatStudentRequest.getLop());
        student.setDiem(creatStudentRequest.getDiem());
        return studentRepo.save(student);
    }
    public void deleteByMSV(String msv) {
        studentRepo.deleteByMSV(msv);
    }
}
