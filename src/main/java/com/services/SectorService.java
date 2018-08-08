package com.services;

import com.dto.SectorDTO;
import com.model.Sector;
import javassist.NotFoundException;

import java.util.List;

public interface SectorService {

    List<Sector> getAllSectors();

    Sector findById(Long id) throws NotFoundException;

    Sector save(SectorDTO newSector);

    Sector update(Long id, SectorDTO updatedSector) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

}
