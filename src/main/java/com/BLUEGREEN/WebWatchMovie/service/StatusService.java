package com.BLUEGREEN.WebWatchMovie.service;

import com.BLUEGREEN.WebWatchMovie.model.Status;
import com.BLUEGREEN.WebWatchMovie.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    public Status getStatusById(int id) {
        return statusRepository.findById(id).orElse(null);
    }

    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }

    public void deleteStatus(int id) {
        statusRepository.deleteById(id);
    }

    public Status updateStatus(int id, Status statusDetails) {
        Status status = statusRepository.findById(id).orElse(null);
        if (status != null) {
            status.setName(statusDetails.getName());
            status.setDescription(statusDetails.getDescription());
            return statusRepository.save(status);
        }
        return null;
    }
}
