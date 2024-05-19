package com.web.studentsystem.dto;

import lombok.Data;

import javax.swing.*;

@Data
public class ChangPassWordRequest {
    String newPass;
    String repeatPass;
}
