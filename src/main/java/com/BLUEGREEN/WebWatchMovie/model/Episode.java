package com.BLUEGREEN.WebWatchMovie.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Getter
@Setter
@Table(name = "Episode")

public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEpisode;

    @NotNull
    @Column(nullable = false)
    private String episodeAdress;

    @NotNull
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private int numberEpisode;


    @Column(nullable = false)
    private int quantityView;

    @Column(nullable = false)
    private boolean isWatch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMovie")
    @JsonBackReference
    private Movie movie;
}
