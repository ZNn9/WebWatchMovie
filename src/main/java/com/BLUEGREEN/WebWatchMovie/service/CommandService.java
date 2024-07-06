package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.Command;
import com.BLUEGREEN.WebWatchMovie.repository.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandService {

    @Autowired
    private CommandRepository commandRepository;

    public List<Command> findAll() {
        return commandRepository.findAll();
    }

    public Optional<Command> findById(int id) {
        return commandRepository.findById(id);
    }

    public Command save(Command command) {
        return commandRepository.save(command);
    }

    public void deleteById(int id) {
        commandRepository.deleteById(id);
    }
}
