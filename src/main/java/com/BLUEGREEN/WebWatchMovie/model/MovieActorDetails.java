package com.BLUEGREEN.WebWatchMovie.model;

import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Getter
@Setter
@Table(name = "MovieActorDetails")

public class MovieActorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idMovie", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "idActor", nullable = false)
    private Actor actor;
}
