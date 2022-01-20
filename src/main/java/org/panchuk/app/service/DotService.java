package org.panchuk.app.service;


import org.panchuk.app.entity.Dot;
import org.panchuk.app.repository.DotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DotService {
    private final DotRepository dotRepository;

    @Autowired
    public DotService (DotRepository dotRepository) {
        this.dotRepository = dotRepository;
    }

    @Transactional
    public void save (Dot dot) {
        dotRepository.save(dot);
    }

    public List<Dot> getAll () {
        return dotRepository.findAll();
    }
}
