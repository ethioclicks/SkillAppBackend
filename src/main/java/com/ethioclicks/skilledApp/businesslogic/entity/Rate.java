package com.ethioclicks.skilledApp.businesslogic.entity;


import com.ethioclicks.skilledApp.security.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "RATE")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;
    @Column(name = "RATE")
    double rate;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    User user;
    @ManyToOne
    @JoinColumn(name = "SHOP_ID")
    Services services;

}
