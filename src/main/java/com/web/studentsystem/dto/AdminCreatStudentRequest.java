package com.web.studentsystem.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AdminCreatStudentRequest {
    private String MSV;
    private String hoTen;
    private String lop;
    private LocalDate ngaySinh;
    //tự sinh mail
    //private String email;
//    fix bug điểm lưu lên DB = 0 bị không
    private double diem;
}
