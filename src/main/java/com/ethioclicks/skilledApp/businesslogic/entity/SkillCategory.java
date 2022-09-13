package com.ethioclicks.skilledApp.businesslogic.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "SKILL_CATEGORY")
@Data
public class SkillCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name="CTEGORY_NAME")
    private String catgoryName;
    @Column(name ="SUB_CATEGORY" )
    private String subCategory;
    @Column(name = "DESCRIPTION")
    private String description;

}
