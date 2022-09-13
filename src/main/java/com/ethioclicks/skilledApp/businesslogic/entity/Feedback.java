package com.ethioclicks.skilledApp.businesslogic.entity;


import com.ethioclicks.skilledApp.security.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "FEEDBACKS")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;
    @Column(name = "FEEDBACK", length = 2000)
    String feedback;
    @OneToOne
    User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "RESTAURANT_ID")
    Services services;
    @Column(name = "POST_TIME")
    LocalDateTime currentTime;
    @Column(name = "TYPE")
    String type;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "REPLY_FEEDBACK")
    Feedback reply;
    @Column(name = "IS_REPLY")
    Boolean isReply = false;
}
