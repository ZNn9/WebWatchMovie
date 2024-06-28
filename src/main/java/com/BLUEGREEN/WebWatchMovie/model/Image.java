package com.BLUEGREEN.WebWatchMovie.model;

import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Getter
@Setter
@Table(name = "Image")

public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idImage;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private String imageAddress;

    @Column(nullable = true)
    private Boolean isTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMovie")
    private Movie movie;

}
