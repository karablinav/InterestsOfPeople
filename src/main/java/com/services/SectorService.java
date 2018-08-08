package com.services;

import com.dto.SectorDTO;
import com.model.Sector;
import com.repositories.SectorRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectorService {

    private final static String NOT_FOUND_ID = "Sector with id = %d not found";

    private SectorRepository sectorRepository;

    @Autowired
    public void setSectorRepository(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    public List<Sector> getAllSectors() {
        return sectorRepository.findAll();
    }

    public Sector findById(Long id) throws NotFoundException {
        Optional<Sector> sector = sectorRepository.findById(id);
        return sector.orElseThrow(()-> new NotFoundException(String.format(NOT_FOUND_ID, id)));
    }

    public Sector save(SectorDTO newSector) {
        Sector sector = Sector.builder().name(newSector.getName()).parentId(newSector.getParentId()).build();
        return sectorRepository.save(sector);
    }

    public Sector update(Long id, SectorDTO updatedSector) throws NotFoundException {
        Optional<Sector> sector = sectorRepository.findById(id);
        if (sector.isPresent()) {
            Sector upSector = Sector.builder().name(updatedSector.getName()).parentId(updatedSector.getParentId()).build();
            return sectorRepository.save(upSector);
        } else {
            throw new NotFoundException(String.format(NOT_FOUND_ID, id));
        }
    }

    public void delete(Long id) throws NotFoundException {
        Optional<Sector> sector = sectorRepository.findById(id);
        if (sector.isPresent()) {
            sectorRepository.delete(sector.get());
        } else{
            throw new NotFoundException(String.format(NOT_FOUND_ID, id));
        }
    }
}
