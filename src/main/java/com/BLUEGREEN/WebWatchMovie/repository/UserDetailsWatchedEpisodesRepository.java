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

    // Find the latest watched episode by a specific user (assuming entries are ordered by timeActive)
    void UserDetailsWatchedEpisodesfindFirstByUserOrderByTimeActiveDesc(User user);

    // You can add other custom methods here (optional)

}
