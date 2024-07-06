package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.Actor;
import com.BLUEGREEN.WebWatchMovie.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Optional<Actor> getActorById(int id) {
        return actorRepository.findById(id);
    }

    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public void deleteActor(int id) {
        actorRepository.deleteById(id);
    }

    public Actor updateActor(int id, Actor actorDetails) {
        Optional<Actor> optionalActor = actorRepository.findById(id);
        if (optionalActor.isPresent()) {
            Actor actor = optionalActor.get();
            actor.setName(actorDetails.getName());
            actor.setDescription(actorDetails.getDescription());
            return actorRepository.save(actor);
        } else {
            // Handle the case where the actor is not found
            return null;
        }
    }
}
