package com.ethioclicks.skilledApp.businesslogic.entity;
import com.ethioclicks.skilledApp.security.entity.User;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Getter
@Setter
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "RATING")
    private Integer rating;
    @Column(name = "FEEDBACK", length = 2000)
    private String feedback;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @Column(name = "IS_REPLY")
    private Boolean isReply = false;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "REPLAY")
    private Reviews reply;
    @Column(name = "POST_TIME")
    private LocalDateTime currentTime;


}
