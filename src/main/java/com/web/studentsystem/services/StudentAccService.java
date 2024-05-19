package com.web.studentsystem.services;

import com.web.studentsystem.dto.UserUpadateInforRequest;
import com.web.studentsystem.entities.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StudentAccService {
    Student getMyInfor(String msv);
    Student updateMyInfor(String msv, UserUpadateInforRequest userUpadateInfor) throws IOException;
    String uploadImage(String msv, MultipartFile file) throws IOException;
    byte[] downloadImage(String msv);
}
