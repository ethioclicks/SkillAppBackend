package com.ethioclicks.skilledApp.businesslogic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "PAYMENT_TYPE")
public class PaymentType {
        @Id
        @GeneratedValue
        @Column(name = "ID")
        Long id;
        @Column(name = "NAME")
        String name;

    }
