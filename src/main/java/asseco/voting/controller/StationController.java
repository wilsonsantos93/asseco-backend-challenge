package asseco.voting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import asseco.voting.ErrorResponse;
import asseco.voting.dto.station.AbstractStationDTO;
import asseco.voting.dto.station.StationRequestDTO;
import asseco.voting.service.StationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Station", description = "Station endpoints")
@RestController
public class StationController {

    @Autowired
    private StationService service;
    
    StationController() {}
        
    // Get all stations
    @Operation(summary = "Get all stations", description = "Gets all stations from database.")
    @GetMapping("/stations")
    List<AbstractStationDTO> all() {
        return service.all();
    }
    
    // Create station
    @Operation(summary = "Create station", description = "Creates a new station.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucessfully created station."),
        @ApiResponse(
            responseCode = "500", description = "Station already exists.", 
            content = { @Content(schema = @Schema(implementation = ErrorResponse.class))  }
        )
    })
    @PostMapping("/station")
    AbstractStationDTO create(@RequestBody StationRequestDTO stationDTO) {
        return service.create(stationDTO);
    }
    
    // Get specific station by ID
    @Operation(summary = "Get station by ID", description = "Gets a specific station by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucessfully fetched station."),
        @ApiResponse(
            responseCode = "404", description = "Station not found.", 
            content = { @Content(schema = @Schema(implementation = ErrorResponse.class))  }
        )
    })
    @GetMapping("/station/{id}")
    AbstractStationDTO get(@PathVariable int id) {
        return service.getById(id);
    }

    // Update specific station
    @Operation(summary = "Update station by ID", description = "Updates a specific station by its ID. Send only the fields that need to be updated.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucessfully updated station."),
        @ApiResponse(
            responseCode = "404", description = "Station not found.", 
            content = { @Content(schema = @Schema(implementation = ErrorResponse.class))  }
        )
    })
    @PatchMapping("/station/{id}")
    AbstractStationDTO update(@PathVariable int id, @RequestBody StationRequestDTO stationDTO) {
        return service.update(id, stationDTO);
    }

    // Delete specific station
    @Operation(summary = "Delete station by ID", description = "Deletes a specific station by its ID.")
    @ApiResponse(responseCode = "200", description = "Sucessfully deleted station.")
    @DeleteMapping("/station/{id}")
    void delete(@PathVariable int id) {
        service.delete(id);
    }
}