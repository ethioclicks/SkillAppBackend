package com.ethioclicks.skilledApp.businesslogic.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Getter
@Setter
@Table(name = "PROJECTS")
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @OneToMany(cascade = CascadeType.ALL )
    @JoinColumn(name = "PROJECT_ID")
    private List<ProjectImages> projectImages;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "TOTAL_COST")
    private Double totalCost;
    @Column(name = "PROJECTS_GIVEN_TO")
    private String projectsGivenTo;
    @Column(name = "TIME_TAKEN")
    private Integer timeTaken;
}
