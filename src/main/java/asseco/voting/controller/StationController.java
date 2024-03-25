package asseco.voting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import asseco.voting.dto.station.AbstractStationDTO;
import asseco.voting.dto.station.StationRequestDTO;

import asseco.voting.service.StationService;

import io.swagger.v3.oas.annotations.tags.Tag;
import asseco.voting.annotations.CreateStationSwagger;
import asseco.voting.annotations.DeleteStationSwagger;
import asseco.voting.annotations.GetAllStationsSwagger;
import asseco.voting.annotations.GetStationSwagger;
import asseco.voting.annotations.UpdateStationSwagger;

@Tag(name = "Station", description = "Station endpoints")
@RestController
public class StationController {

    @Autowired
    private StationService service;
    
    StationController() {}
        
    // Get all stations
    @GetMapping("/stations")
    @GetAllStationsSwagger
    List<AbstractStationDTO> all(
        @RequestParam(name="distrito", required = false) String distrito, 
        @RequestParam(name="freguesia", required = false) String freguesia,
        @RequestParam(name="location", required = false) String location
    ) {
        return service.getByParameters(distrito, freguesia, location);
    }
    
    // Create station
    @PostMapping("/station")
    @CreateStationSwagger
    AbstractStationDTO create(@RequestBody StationRequestDTO stationDTO) {
        return service.create(stationDTO);
    }
    
    // Get specific station by ID
    @GetMapping("/station/{id}")
    @GetStationSwagger
    AbstractStationDTO get(@PathVariable int id) {
        return service.getById(id);
    }

    // Update specific station
    @PatchMapping("/station/{id}")
    @UpdateStationSwagger
    AbstractStationDTO update(@PathVariable int id, @RequestBody StationRequestDTO stationDTO) {
        return service.update(id, stationDTO);
    }

    // Delete specific station
    @DeleteMapping("/station/{id}")
    @DeleteStationSwagger
    void delete(@PathVariable int id) {
        service.delete(id);
    }
}