package com.BLUEGREEN.WebWatchMovie.model;

import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Getter
@Setter
@Table(name = "Movie")

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMovie;
    private String movieAddress;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;
    private int totalQuantityView = 0;
    private int quantityRating = 0;
    private float qualityRating = 0.0f;
    private int totalQuantityEpisodes = 0;
    private int quantityEpisodesNow = 0;
    private int quantityFollowers = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idStatus")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSeason")
    private Season season;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDirector")
    private Director director;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idStudio")
    private Studio studio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOriginMovie")
    private OriginMovie originMovie;
}
