package asseco.voting.controller;

import java.util.List;

import asseco.voting.ErrorResponse;
import asseco.voting.dto.voter.AbstractVoterDTO;
import asseco.voting.dto.voter.VoterRequestDTO;
import asseco.voting.service.VoterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Voter", description = "Voter endpoints")
@RestController
public class VoterController {

    @Autowired
    private VoterService service;

    VoterController() {}

    
    // Get all voters
    @Operation(summary = "Get all voters (or filter by parameters)", description = "Gets all voters (filtered or not) from database.")
    @GetMapping("/voters")
    List<AbstractVoterDTO> all(
        @RequestParam(name="name", required = false) String name, 
        @RequestParam(name="citizenNumber", required = false) Integer citizenNumber
    ) {
        return service.getByParameters(name, citizenNumber);
    }


    // Create voter
    @Operation(summary = "Create voter", description = "Creates a new voter.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucessfully created voter."),
        @ApiResponse(
            responseCode = "500", description = "Voter already exists.", 
            content = { @Content(schema = @Schema(implementation = ErrorResponse.class))  }
        )
    })
    @PostMapping("/voter")
    AbstractVoterDTO create(@RequestBody VoterRequestDTO newVoter) {
        return service.create(newVoter);
    }


    // Get specific voter by ID
    @Operation(summary = "Get voter by ID", description = "Gets a specific voter by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucessfully fetched voter."),
        @ApiResponse(
            responseCode = "404", description = "Voter not found.", 
            content = { @Content(schema = @Schema(implementation = ErrorResponse.class))  }
        )
    })
    @GetMapping("/voter/{id}")
    AbstractVoterDTO getById(@PathVariable int id) {
        return service.getById(id);
    }
    

    // Update specific voter
    @Operation(summary = "Update a voter by ID", description = "Updates a voter by its ID. Send only the fields that need to be updated.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucessfully updated voter."),
        @ApiResponse(
            responseCode = "404", description = "Voter not found.", 
            content = { @Content(schema = @Schema(implementation = ErrorResponse.class))  }
        )
    })
    @PatchMapping("/voter/{id}")
    AbstractVoterDTO update(@RequestBody VoterRequestDTO newVoter, @PathVariable int id) {
        return service.update(id, newVoter);
    }


    // Delete specific voter
    @Operation(summary = "Delete voter by ID", description = "Deletes a voter by its ID")
    @ApiResponse(responseCode = "200", description = "Sucessfully deleted voter.")
    @DeleteMapping("/voter/{id}")
    void delete(@PathVariable int id) {
        service.delete(id);
    }

}