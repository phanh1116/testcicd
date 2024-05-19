package com.web.studentsystem.services;

public interface EmailService {
    public void sendChangePasswordMail(String toEmail, String subject, String body);
}
