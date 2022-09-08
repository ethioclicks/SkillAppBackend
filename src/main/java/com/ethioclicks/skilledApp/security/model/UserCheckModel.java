package com.ethioclicks.skilledApp.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCheckModel {

    private String questionsAndAnswers;
    @NotNull(message = "Phone number is required")
    private String phoneNumber;
    private int attempt;

}
