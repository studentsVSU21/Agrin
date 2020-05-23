package ru.vsu.cs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.Entities.Region;
import ru.vsu.cs.reposirories.RegionRepository;

import java.util.Collection;

@Service
public class RegionService {

    private Logger LOG = LoggerFactory.getLogger(RegionService.class);

    private RegionRepository regionRepository;

    @Autowired
    public RegionService(
            RegionRepository regionRepository
    ) {
        this.regionRepository = regionRepository;
    }

    public Collection<Region> getRegions() {
        return (Collection<Region>) regionRepository.findAll();
    }
}
