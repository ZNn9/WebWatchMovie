package com.BLUEGREEN.WebWatchMovie.repository;

import com.BLUEGREEN.WebWatchMovie.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Integer> {
}
