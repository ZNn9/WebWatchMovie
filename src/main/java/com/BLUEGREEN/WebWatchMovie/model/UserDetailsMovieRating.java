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
@Table(name = "UserDetailsMovieRating")

public class UserDetailsMovieRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "idMovie", nullable = false)
    private Movie movie;

    private float ratingPoint;

    @Column(name = "timeActive")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeActive;
}
