package com.BLUEGREEN.WebWatchMovie.repository;

import com.BLUEGREEN.WebWatchMovie.model.User;
import com.BLUEGREEN.WebWatchMovie.model.UserDetailsWatchedEpisodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsWatchedEpisodesRepository extends JpaRepository<UserDetailsWatchedEpisodes, Integer> {

    // Find all episodes watched by a specific user
    List<UserDetailsWatchedEpisodes> findByUser(User user);

}
