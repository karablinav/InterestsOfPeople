package com.controllers.rest;

import com.dto.SectorDTO;
import com.model.Sector;
import com.services.SectorService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SectorController {

    private final SectorService sectorService;

    @Autowired
    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping("/sectors")
    public ResponseEntity<List<Sector>> getAllSectors() {
        List<Sector> sectors = sectorService.getAllSectors();
        if (!sectors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(sectors);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(sectors);
        }
    }

    @GetMapping("/sectors/{id}")
    public ResponseEntity<Sector> getSectorById(@PathVariable(value = "id") Long id) throws NotFoundException {
        return ResponseEntity.ok(sectorService.findById(id));
    }

    @PostMapping("/sectors")
    public ResponseEntity<Sector> saveSector( @Valid @RequestBody SectorDTO newSector) {
        Sector sector = sectorService.save(newSector);
        return ResponseEntity.status(HttpStatus.CREATED).body(sector);
    }

    @PutMapping("/sectors/{id}")
    public ResponseEntity<Sector> updateSector(@PathVariable(value = "id") Long id, @Valid @RequestBody SectorDTO updatedSector)
            throws NotFoundException {
        Sector sector = sectorService.update(id, updatedSector);
        return ResponseEntity.status(HttpStatus.OK).body(sector);
    }

    @DeleteMapping("/sectors/{id}")
    public ResponseEntity<Sector> deleteSector(@PathVariable(value = "id") Long id) throws NotFoundException {
        sectorService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
