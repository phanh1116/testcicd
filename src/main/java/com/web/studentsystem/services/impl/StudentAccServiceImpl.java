package com.web.studentsystem.services.impl;

import com.web.studentsystem.Util.ImageUtils;
import com.web.studentsystem.dto.UserUpadateInforRequest;
import com.web.studentsystem.entities.Student;
import com.web.studentsystem.repository.StudentRepo;
import com.web.studentsystem.services.StudentAccService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StudentAccServiceImpl implements StudentAccService {
    private final StudentRepo studentRepo;
    public Student getMyInfor(String msv) {
        return studentRepo.findByMSV(msv);
    }

    @Override
    public Student updateMyInfor(String msv, UserUpadateInforRequest userUpadate) throws IOException {
        Student student = studentRepo.findByMSV(msv);
//        student.setFile(userUpadate.getFile());
        student.setDemofile(userUpadate.getDemofile().getBytes());
        student.setSdt(userUpadate.getSdt());
        student.setDiaChi(userUpadate.getDiaChi());
        student.setCccd(userUpadate.getCccd());
        student.setBaohiem(userUpadate.getBaohiem());

        student.setHoTenBo(userUpadate.getHoTenBo());
        student.setSdtBo(userUpadate.getSdtBo());
        student.setNamSinhBo(userUpadate.getNamSinhBo());

        student.setHoTenMe(userUpadate.getHoTenMe());
        student.setSdtMe(userUpadate.getSdtMe());
        student.setNamSinhMe(userUpadate.getNamSinhMe());

        return studentRepo.save(student);
    }

    public String uploadImage(String msv, MultipartFile file) throws IOException {
        Student student = studentRepo.findByMSV(msv);
        if(student != null) {
//            student.setDemofile(ImageUtils.compressImage(file.getBytes()));
            student.setDemofile(file.getBytes());
            studentRepo.save(student);
        } else return "Ko tìm thấy";
        return "OK";
    }

    public byte[] downloadImage(String msv) {
        Student student = studentRepo.findByMSV(msv);
//        byte[] images = ImageUtils.decompressImage(student.getDemofile());
        return student.getDemofile();
    }
}
