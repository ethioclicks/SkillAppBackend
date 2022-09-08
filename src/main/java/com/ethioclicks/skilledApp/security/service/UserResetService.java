package com.ethioclicks.skilledApp.security.service;

import com.ethioclicks.skilledApp.security.entity.User;
import com.ethioclicks.skilledApp.security.model.UserCheckModel;
import com.ethioclicks.skilledApp.security.model.UserResetModel;
import com.ethioclicks.skilledApp.security.entity.Questions;

import java.util.List;

public interface UserResetService {

    User userNameReset(UserResetModel userResetModel);
    User userPasswordReset(UserResetModel userResetModel);
    UserCheckModel checkQuestionsAndAnswers(UserCheckModel userCheckModel);
    List<Questions> getQuestions();
}
