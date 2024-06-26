package com.BLUEGREEN.WebWatchMovie.model;

import jakarta.persistence.*;
import lombok.*;


@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Getter
@Setter
@Table(name = "Actor")


public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idActor;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 255)
    private String description;
}
