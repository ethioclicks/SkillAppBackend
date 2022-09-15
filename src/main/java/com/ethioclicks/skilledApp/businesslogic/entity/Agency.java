package com.ethioclicks.skilledApp.businesslogic.entity;

import com.ethioclicks.skilledApp.security.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AGENCY")
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "DESCRIPTION")
    private String Description;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STREET")
    private String street;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "EMAIL")
    private String email;
    @OneToOne
    @JoinColumn(name = "OWNER_ID")
    private User owner;
}
