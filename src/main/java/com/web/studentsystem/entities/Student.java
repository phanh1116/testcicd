package com.web.studentsystem.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;

@Data
@Document
public class Student {
    //Những thông tin admin thêm chỉnh sửa
    @Id
    private String id;

    private String MSV;

    private String hoTen;
    private LocalDate ngaySinh;
    private String lop;
    private double diem;
    //tự sinh
    private String email;

    //Những thông tin SV cập nhật thêm
    //file ảnh sửa sau
//    private String file;
    private byte[] demofile;
    private String sdt;
    private String diaChi;

    private String cccd;

    private String baohiem;

    private String hoTenBo;
    private String sdtBo;
    private String namSinhBo;

    private String hoTenMe;
    private String sdtMe;
    private String namSinhMe;

    public Student(){
    }

    public Student(String MSV, String hoTen, LocalDate ngaySinh, String lop, double diem) {
        this.MSV = MSV;
        this.hoTen = hoTen;
        this.lop = lop;
        this.ngaySinh = ngaySinh;
//        this.email = MSV + "@gmail.com";
        this.email = MSV + "@vnu.edu.vn";
        this.diem = diem;
    }
}

