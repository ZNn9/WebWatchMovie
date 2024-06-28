package com.BLUEGREEN.WebWatchMovie.model;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int numberEpisode;


    @Column(nullable = false)
    private int quantityView;

    @Column(nullable = false)
    private boolean isWatch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMovie")
    private Movie movie;
}
