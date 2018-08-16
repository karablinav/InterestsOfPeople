package com.services;

import com.dto.SectorDTO;
import javassist.NotFoundException;

import java.util.List;

public interface SectorService {

    List<SectorDTO> getAllSectors();

    SectorDTO findById(Long id) throws NotFoundException;

    void save(SectorDTO newSector);

    void update(Long id, SectorDTO updatedSector) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

}
