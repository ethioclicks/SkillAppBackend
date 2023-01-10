package com.ethioclicks.skilledApp.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCheckModel {

//    private String questionsAndAnswers;
//    @NotNull(message = "Phone number is required")
//    private String phoneNumber;
//    private int attempt;
    private String questions;
    private String answers;
    boolean isCorrect;
    boolean hasNext;
    int index;


}
