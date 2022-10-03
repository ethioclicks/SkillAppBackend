package com.ethioclicks.skilledApp.businesslogic.model;

import com.ethioclicks.skilledApp.security.entity.User;
import lombok.Data;

import java.util.Date;


@Data
public class AgencyModel {
    private Long id;
    private String agencyPublicId;
    private String Description;
    private String city;
    private String street;
    private String phone;
    private String email;
    private User owner;
    private Date postDate;
}
