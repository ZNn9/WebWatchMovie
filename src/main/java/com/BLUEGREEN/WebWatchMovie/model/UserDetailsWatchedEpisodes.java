package com.BLUEGREEN.WebWatchMovie.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Getter
@Setter
@Table(name = "UserDetailsWatchedEpisodes")


public class UserDetailsWatchedEpisodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "idEpisode", nullable = false)
    private Episode episode;

    @Column(name = "timeEndWatch")
    @Temporal(TemporalType.TIME)  // Assuming end watch time only stores time (HH:MM:SS)
    private Date timeEndWatch;

    @Column(name = "timeActive")
    @Temporal(TemporalType.TIMESTAMP)  // Assuming timeActive stores both date and time
    private Date timeActive;
}
