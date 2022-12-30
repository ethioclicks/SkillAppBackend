//package com.ethioclicks.skilledApp.businesslogic.entity;
//
//
//import com.ethioclicks.skilledApp.security.entity.User;
//import lombok.*;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Getter
//@Setter
//@Table(name = "SERVICE_FEEDBACKS")
//public class ServicesFeedback {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID")
//    Long id;
//    @Column(name = "FEEDBACK", length = 2000)
//    String feedback;
//    @OneToOne
//    User user;
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "SERVICES_ID")
//    Services services;
//    @Column(name = "POST_TIME")
//    LocalDateTime currentTime;
//    @Column(name = "TYPE")
//    String type;
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "REPLY_FEEDBACK")
//    ServicesFeedback reply;
//    @Column(name = "IS_REPLY")
//    Boolean isReply = false;
//}
