package com.ethioclicks.skilledApp.businesslogic.entity;

import com.ethioclicks.skilledApp.businesslogic.enums.DAY_OF_WEEK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AVAILABILITY_HOUR")
public class AvailabilityHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name="DAY")
    @Enumerated(EnumType.ORDINAL)
    private DAY_OF_WEEK day_of_week;
    @Column(name = "STARTING_HOUR")
    String startingHour;
    @Column(name = "ENDING_HOUR")
    String endingHour;

}
