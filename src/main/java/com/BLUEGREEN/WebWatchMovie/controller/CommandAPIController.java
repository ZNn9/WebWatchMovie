package com.BLUEGREEN.WebWatchMovie.controller;

import com.BLUEGREEN.WebWatchMovie.model.Command;
import com.BLUEGREEN.WebWatchMovie.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commands")
public class CommandAPIController {

    @Autowired
    private CommandService commandService;

    @GetMapping
    public List<Command> getAllCommands() {
        return commandService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Command> getCommandById(@PathVariable int id) {
        Optional<Command> command = commandService.findById(id);
        return command.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Command createCommand(@RequestBody Command command) {
        return commandService.save(command);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Command> updateCommand(@PathVariable int id, @RequestBody Command updatedCommand) {
        Optional<Command> optionalCommand = commandService.findById(id);

        if (optionalCommand.isPresent()) {
            Command existingCommand = optionalCommand.get();
            existingCommand.setDescription(updatedCommand.getDescription());
            existingCommand.setIdCommandChildren(updatedCommand.getIdCommandChildren());
            existingCommand.setUser(updatedCommand.getUser());
            existingCommand.setMovie(updatedCommand.getMovie());
            existingCommand.setEpisode(updatedCommand.getEpisode());

            commandService.save(existingCommand);
            return ResponseEntity.ok(existingCommand);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommand(@PathVariable int id) {
        if (commandService.findById(id).isPresent()) {
            commandService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
