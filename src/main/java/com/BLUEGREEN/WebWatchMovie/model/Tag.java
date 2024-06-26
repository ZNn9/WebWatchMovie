package com.BLUEGREEN.WebWatchMovie.model;


import jakarta.persistence.*;
import lombok.*;


@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Getter
@Setter
@Table(name = "Tag")


public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTag;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 255)
    private String description;
}
