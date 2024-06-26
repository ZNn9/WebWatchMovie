package com.BLUEGREEN.WebWatchMovie.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    private String name = ("Xuân, Hạ, Thu, Đông");

    private String description;

    private int year;

}
