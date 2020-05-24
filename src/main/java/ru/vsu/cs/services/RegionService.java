package ru.vsu.cs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.CustomExceptions.NotFoundById;
import ru.vsu.cs.Entities.Region;
import ru.vsu.cs.reposirories.RegionRepository;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

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
        Collection<Region> regions = new LinkedList<>();
        for (Region region : regionRepository.findAll()) {
            regions.add(region);
            LOG.debug("region : {}", region);
        }
        return regions;
    }

    public Region findRegionByID(Long id) throws NotFoundById {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        if ( !optionalRegion.isPresent() ) {
            throw new NotFoundById("Region Not found");
        }
        return optionalRegion.get();
    }
}
