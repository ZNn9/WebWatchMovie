package com.BLUEGREEN.WebWatchMovie.model;

import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Getter
@Setter
@Table(name = "OriginMovie")

public class OriginMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOriginMovie;

    private String name;

    private String description;
}
