package com.web.studentsystem.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public SignUpRequest(String email, String password) {
        this.email = email;
        this.password = password;
        this.firstName = this.password;
//         MSV
    }
}
