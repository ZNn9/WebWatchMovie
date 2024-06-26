package com.BLUEGREEN.WebWatchMovie.model;

import jakarta.persistence.*;
import lombok.*;


@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Getter
@Setter
@Table(name = "TagMovieDetails")

public class TagMovieDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idMovie", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "idTag", nullable = false)
    private Tag tag;
}
