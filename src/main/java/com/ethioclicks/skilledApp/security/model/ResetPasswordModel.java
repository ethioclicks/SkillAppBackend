package com.ethioclicks.skilledApp.security.model;

import lombok.Data;

@Data
public class ResetPasswordModel {
    private String email;
    private String token;
    private String password;
}
