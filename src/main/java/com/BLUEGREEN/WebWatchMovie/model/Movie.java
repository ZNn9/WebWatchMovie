package com.BLUEGREEN.WebWatchMovie.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name = "idStatus", referencedColumnName = "idStatus")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "idSeason", referencedColumnName = "idSeason")
    private Season season;

    @ManyToOne
    @JoinColumn(name = "idDirector", referencedColumnName = "idDirector")
    private Director director;

    @ManyToOne
    @JoinColumn(name = "idStudio", referencedColumnName = "idStudio")
    private Studio studio;

    @ManyToOne
    @JoinColumn(name = "idOriginMovie", referencedColumnName = "idOriginMovie")
    private OriginMovie originMovie;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Episode> episodes;
}
