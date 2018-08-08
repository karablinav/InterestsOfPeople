package com.controllers.rest;

import com.dto.SectorDTO;
import com.model.Sector;
import com.services.impl.SectorServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SectorController {

    private final SectorServiceImpl sectorServiceImpl;

    @Autowired
    public SectorController(SectorServiceImpl sectorServiceImpl) {
        this.sectorServiceImpl = sectorServiceImpl;
    }

    @GetMapping("/sectors")
    public ResponseEntity<List<Sector>> getAllSectors() {
        List<Sector> sectors = sectorServiceImpl.getAllSectors();
        if (!sectors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(sectors);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(sectors);
        }
    }

    @GetMapping("/sectors/{id}")
    public ResponseEntity<Sector> getSectorById(@PathVariable(value = "id") Long id) throws NotFoundException {
        return ResponseEntity.ok(sectorServiceImpl.findById(id));
    }

    @PostMapping("/sectors")
    public ResponseEntity<Sector> saveSector( @Valid @RequestBody SectorDTO newSector) {
        Sector sector = sectorServiceImpl.save(newSector);
        return ResponseEntity.status(HttpStatus.CREATED).body(sector);
    }

    @PutMapping("/sectors/{id}")
    public ResponseEntity<Sector> updateSector(@PathVariable(value = "id") Long id, @Valid @RequestBody SectorDTO updatedSector)
            throws NotFoundException {
        Sector sector = sectorServiceImpl.update(id, updatedSector);
        return ResponseEntity.status(HttpStatus.OK).body(sector);
    }

    @DeleteMapping("/sectors/{id}")
    public ResponseEntity<Sector> deleteSector(@PathVariable(value = "id") Long id) throws NotFoundException {
        sectorServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
