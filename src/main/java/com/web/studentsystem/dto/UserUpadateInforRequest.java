package com.web.studentsystem.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class UserUpadateInforRequest {
    //file ảnh sửa sau
//    private String file;
    private MultipartFile demofile;
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
}
