package com.golf.two_for_tom_open.service;

import com.golf.two_for_tom_open.model.Hole;
import com.golf.two_for_tom_open.repository.HoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoleService {

    private HoleRepository holeRepository;

    public HoleService(HoleRepository holeRepository) {
        this.holeRepository = holeRepository;
    }

    public List<Hole> getAll() {
        return holeRepository.findAll();
    }

    public Hole save(Hole hole) {
        return holeRepository.save(hole);
    }

}
