package com.controllers.rest;

import com.dto.SectorDTO;
import com.services.SectorService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
public class SectorController {

    private final SectorService sectorService;

    @Autowired
    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping("/sectors")
    public ResponseEntity<List<SectorDTO>> getAllSectors() {
        List<SectorDTO> sectors = sectorService.getAllSectors();
        return !sectors.isEmpty()
                ? ResponseEntity.status(OK).body(sectors)
                : ResponseEntity.status(NO_CONTENT).body(sectors);

    }

    @GetMapping("/sectors/{id}")
    public ResponseEntity<SectorDTO> getSectorById(@PathVariable(value = "id") Long id) throws NotFoundException {
        return ResponseEntity.ok(sectorService.findById(id));
    }

    @PostMapping("/sectors")
    public ResponseEntity saveSector(@Valid @RequestBody SectorDTO newSector) {
        sectorService.save(newSector);
        return ResponseEntity.status(CREATED).build();
    }

    @PutMapping("/sectors/{id}")
    public ResponseEntity updateSector(@PathVariable(value = "id") Long id, @Valid @RequestBody SectorDTO updatedSector)
            throws NotFoundException {
        sectorService.update(id, updatedSector);
        return ResponseEntity.status(OK).build();
    }

    @DeleteMapping("/sectors/{id}")
    public ResponseEntity deleteSector(@PathVariable(value = "id") Long id) throws NotFoundException {
        sectorService.delete(id);
        return ResponseEntity.status(OK).build();
    }
}
