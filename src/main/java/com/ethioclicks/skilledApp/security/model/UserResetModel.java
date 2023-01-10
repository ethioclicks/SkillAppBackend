package com.ethioclicks.skilledApp.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResetModel {

    private String questionsAndAnswers;
    private String userName;
    private String userPassword;
    private String phoneNumber;
    private int attempt;
}
