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
@Table(name = "Studio")

public class Studio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStudio;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "idCountry")
    private Country country;

}
