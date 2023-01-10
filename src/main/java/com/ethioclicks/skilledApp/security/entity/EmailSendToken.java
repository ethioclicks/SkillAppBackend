package com.ethioclicks.skilledApp.security.entity;



import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="EMAIL_SEND_TOKEN")
public class EmailSendToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="TOKEN_ID")
    private Integer id;
    @Column(name="TOKEN")
    private String token;
    @Column(name="EMAIL")
    private String email;

    @Column(name="CREATED_DATE")
    private LocalDateTime createdDate;


    public EmailSendToken() {

        createdDate = LocalDateTime.now();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
