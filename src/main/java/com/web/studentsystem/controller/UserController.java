package com.web.studentsystem.controller;

import com.web.studentsystem.dto.ChangPassWordRequest;
import com.web.studentsystem.dto.UserUpadateInforRequest;
import com.web.studentsystem.entities.Role;
import com.web.studentsystem.entities.Student;
import com.web.studentsystem.entities.User;
import com.web.studentsystem.repository.UserRepo;
import com.web.studentsystem.services.StudentAccService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final StudentAccService studentAccService;
    private final UserRepo userRepo;
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hi User");
    }
    @GetMapping("/role")
    public ResponseEntity<String> getRole(@AuthenticationPrincipal User currentUser){
        return ResponseEntity.ok(currentUser.getRole().toString());
    }

    @GetMapping("/myinfor")
    public ResponseEntity<Student> getStudentByMSV(@AuthenticationPrincipal User currentUser){
        return ResponseEntity.ok(studentAccService.getMyInfor(currentUser.getFirstName()));
    }
    @PutMapping("/myinfor")
    public ResponseEntity<Student> updateStudent(@AuthenticationPrincipal User currentUser, @ModelAttribute UserUpadateInforRequest userUpadate) throws IOException {
        return ResponseEntity.ok(studentAccService.updateMyInfor(currentUser.getFirstName(), userUpadate));
    }

    @PostMapping("/changepassword")
    public ResponseEntity<String> changePasswordHandler(@AuthenticationPrincipal User currentUser, @RequestBody ChangPassWordRequest changePassword) {
        if (!changePassword.getNewPass().equals(changePassword.getRepeatPass())) {
            return new ResponseEntity<>("Please enter the password again!", HttpStatus.EXPECTATION_FAILED);
        }
        currentUser.setPassword(new BCryptPasswordEncoder().encode(changePassword.getNewPass()));
        userRepo.save(currentUser);
        return ResponseEntity.ok("Password has been changed!");
    }
    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@AuthenticationPrincipal User currentUser, @RequestParam MultipartFile file) throws IOException {
        String uploadImage = studentAccService.uploadImage(currentUser.getFirstName(), file);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/image")
    public ResponseEntity<?> downloadImageFromFileSystem(@AuthenticationPrincipal User currentUser) throws IOException {
        byte[] imageData = studentAccService.downloadImage(currentUser.getFirstName());
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }


    //
//    @GetMapping("/me")
//    public ResponseEntity<String> getUserInfo(@AuthenticationPrincipal User currentUser) {
//        System.out.println(currentUser.getId());
//        System.out.println(currentUser.getEmail());
//        System.out.println(currentUser.getAuthorities());
//        System.out.println(currentUser.getFirstName());
//        System.out.println(currentUser.getPassword());
//
//        return ResponseEntity.ok("end");
//    }

}
