package com.BLUEGREEN.WebWatchMovie.model;

import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Getter
@Setter
@Table(name = "Status")

public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStatus;

    private String name;
    private String description = null;


}
