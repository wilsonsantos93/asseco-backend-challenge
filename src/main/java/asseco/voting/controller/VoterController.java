package asseco.voting.controller;

import java.util.List;

import asseco.voting.annotations.CreateVoterSwagger;
import asseco.voting.annotations.DeleteVoterSwagger;
import asseco.voting.annotations.GetAllVotersSwagger;
import asseco.voting.annotations.GetVoterSwagger;
import asseco.voting.annotations.UpdateVoterSwagger;
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

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Voter", description = "Voter endpoints")
@RestController
public class VoterController {

    @Autowired
    private VoterService service;

    VoterController() {}

    
    // Get all voters
    @GetMapping("/voters")
    @GetAllVotersSwagger
    List<AbstractVoterDTO> all(
        @RequestParam(name="name", required = false) String name, 
        @RequestParam(name="citizenNumber", required = false) Integer citizenNumber
    ) {
        return service.getByParameters(name, citizenNumber);
    }


    // Create voter
    @PostMapping("/voter")
    @CreateVoterSwagger
    AbstractVoterDTO create(@RequestBody VoterRequestDTO newVoter) {
        return service.create(newVoter);
    }


    // Get specific voter by ID
    @GetMapping("/voter/{id}")
    @GetVoterSwagger
    AbstractVoterDTO getById(@PathVariable int id) {
        return service.getById(id);
    }
    

    // Update specific voter
    @PatchMapping("/voter/{id}")
    @UpdateVoterSwagger
    AbstractVoterDTO update(@RequestBody VoterRequestDTO newVoter, @PathVariable int id) {
        return service.update(id, newVoter);
    }


    // Delete specific voter
    @DeleteMapping("/voter/{id}")
    @DeleteVoterSwagger
    void delete(@PathVariable int id) {
        service.delete(id);
    }

}