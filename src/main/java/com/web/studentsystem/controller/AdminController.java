package com.web.studentsystem.controller;

import com.web.studentsystem.dto.AdminCreatStudentRequest;
import com.web.studentsystem.dto.SignUpRequest;
import com.web.studentsystem.entities.Student;
import com.web.studentsystem.entities.User;
import com.web.studentsystem.repository.UserRepo;
import com.web.studentsystem.services.AdminService;
import com.web.studentsystem.services.AuthenticationService;
import com.web.studentsystem.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final AuthenticationService authenticationService;
    private final EmailService emailService;

    private final UserRepo userRepo;
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hi Admin");
    }
    @GetMapping("/role")
    public ResponseEntity<String> getRole(@AuthenticationPrincipal User currentUser){
        return ResponseEntity.ok(currentUser.getRole().toString());
    }
    //READ CONTROLLER
    @GetMapping("/allStudent")
    public ResponseEntity<List<Student>> getAllStudent(){
        return ResponseEntity.ok(adminService.getAllStudent());
    }
    @GetMapping("/student")
    public ResponseEntity<Student> getStudentByMSV(@RequestParam String msv){
        return ResponseEntity.ok(adminService.getStudentByMSV(msv));
    }
//    CREATE CONTROLLER
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody AdminCreatStudentRequest adminCreatStudentRequest){
        //nếu đã có MSV thì trả về null
        Student student = adminService.createStudent(adminCreatStudentRequest);
        if(student != null){
            authenticationService.signup(new SignUpRequest(student.getEmail(),student.getMSV()));
//            emailService.sendChangePasswordMail(student.getEmail(),
//                    "Tài khoản hệ thống quản lý sinh viên ",
//                    "Tài khoản của bạn: USERNAME:"+ student.getEmail() + " PASSWORD: " + student.getMSV() + ".\nHãy truy cập hệ thống để đổi mật khẩu và cập nhật " +
//                            "các thông tin cần thiết");
            return ResponseEntity.ok(student);
            } else return ResponseEntity.notFound().build();
//        return null;
    }
    //UPDATE CONTROLLER
    @PutMapping("/update/{msv}")
    public ResponseEntity<Student> updateStudent(@PathVariable String msv, @RequestBody AdminCreatStudentRequest adminCreatStudentRequest) {
        return ResponseEntity.ok(adminService.updateStudent(msv, adminCreatStudentRequest));
    }

    @PutMapping("/mail/{msv}")
    public ResponseEntity<String> mailStudent(@PathVariable String msv) {
        String mail = msv + "@vnu.edu.vn";
        //gửi mail

//        emailService.sendChangePasswordMail(mail,
//                "Cập nhật thông tin trên hệ thống",
//                "Nhắc nhở sinh viên truy cập vào hệ thống quản lý sinh viên để cập" +
//                        " nhập những thông tin cần thiết."
//                );
        return ResponseEntity.ok("Đã gửi mail nhắc nhở cập nhật thông tin đến " + mail);
    }

    //DELETE CONTROLLER
    @DeleteMapping("/delete")
    public ResponseEntity<Student> deleteStudentByMSV(@RequestParam String msv) {
        adminService.deleteByMSV(msv);
        userRepo.deleteByFirstName(msv);
        return ResponseEntity.ok().build();
    }

}
