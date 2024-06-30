package com.BLUEGREEN.WebWatchMovie.model;

import jakarta.persistence.*;
import lombok.*;


@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Getter
@Setter
@Table(name = "Command")

public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCommand;

    @Column(nullable = false)
    private String description;

    @Column(name = "idCommandChildren", nullable = false)
    private int idCommandChildren;  // This could be renamed to something more specific based on the actual use case (e.g., parentId)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMovie")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEpisode")
    private Episode episode;

}
