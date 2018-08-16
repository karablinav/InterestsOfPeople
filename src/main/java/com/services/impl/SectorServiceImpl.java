package com.services.impl;

import com.dto.SectorDTO;
import com.model.Sector;
import com.repositories.SectorRepository;
import com.services.SectorService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectorServiceImpl implements SectorService {

    private final static String NOT_FOUND_ID = "Sector with id = %d not found";

    private final SectorRepository sectorRepository;

    @Autowired
    public SectorServiceImpl(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    @Transactional
    @Override
    public List<SectorDTO> getAllSectors() {
        return sectorRepository.findAll().stream()
                .map(this::convertSectorToDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public SectorDTO findById(Long id) throws NotFoundException {
        return sectorRepository.findById(id)
                .map(this::convertSectorToDto)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_ID, id)));
    }

    @Transactional
    @Override
    public void save(SectorDTO newSector) {
        Sector sector = convertDtoToSector(newSector);
        sectorRepository.save(sector);
    }

    @Transactional
    @Override
    public void update(Long id, SectorDTO updatedSector) throws NotFoundException {
        Sector sector = sectorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_ID,id)));
        sector.setName(updatedSector.getName());
        sector.setParentId(updatedSector.getParentId());
        sectorRepository.save(sector);
    }

    @Transactional
    @Override
    public void delete(Long id) throws NotFoundException {
        Sector sector = sectorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_ID,id)));
        sectorRepository.delete(sector);
    }

    private SectorDTO convertSectorToDto(Sector sector) {
        return SectorDTO.builder()
                .id(sector.getId())
                .name(sector.getName())
                .parentId(sector.getParentId()).build();
    }

    private Sector convertDtoToSector(SectorDTO sectorDTO) {
        return Sector.builder()
                .id(sectorDTO.getId())
                .name(sectorDTO.getName())
                .parentId(sectorDTO.getParentId())
                .build();
    }
}
