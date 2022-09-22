package com.ethioclicks.skilledApp.security.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailSendModel {
    private String userName;
    private String passwordToken;
    private String subject;
    private String message;
}
