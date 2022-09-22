package com.ethioclicks.skilledApp.security.service;

import com.ethioclicks.skilledApp.security.entity.EmailSendToken;
import com.ethioclicks.skilledApp.security.entity.User;

import java.time.LocalDateTime;

public interface PasswordResetService {
    String sendFirstRegistrationToken( String email) throws Exception;

    EmailSendToken getTokenByEmail(String token, String email);
    void resetPassword(User user, String newPassword);
    boolean isTokenExpired(final LocalDateTime tokenCreationDate);
    boolean isValidToken(String token, String email);
    void resetUserName(User user, String newEmail);
}
