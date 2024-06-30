package com.BLUEGREEN.WebWatchMovie.model;

import jakarta.persistence.*;
import lombok.*;


@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Getter
@Setter
@Table(name = "Season")

public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSeason;

    private String name;

    private String description;

    private int year;

}
