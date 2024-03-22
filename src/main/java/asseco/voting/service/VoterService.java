package asseco.voting.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import asseco.voting.dto.station.AbstractStationDTO;
import asseco.voting.dto.station.StationDTO;
import asseco.voting.dto.voter.AbstractVoterDTO;
import asseco.voting.dto.voter.VoterDTO;
import asseco.voting.dto.voter.VoterRequestDTO;
import asseco.voting.entity.Station;
import asseco.voting.entity.Voter;
import asseco.voting.repository.StationRepository;
import asseco.voting.repository.VoterRepository;

import org.springframework.beans.BeanUtils;

@Service
public class VoterService implements ICrudService<AbstractVoterDTO> {
    @Autowired
    private VoterRepository repository;

    @Autowired
    private StationRepository stationRepository;

    
    /* Get all voters */
    public List<AbstractVoterDTO> all() {
        List<Voter> voters = repository.findAll();
        return voters.stream()
                .map(voter -> {
                    VoterDTO voterDTO = new VoterDTO();
                    BeanUtils.copyProperties(voter, voterDTO);
                    AbstractStationDTO stationDTO = getStationDTO(voter.getStation());
                    voterDTO.setStation(stationDTO);
                    return voterDTO;
                })
                .collect(Collectors.toList());
    }


    /* Create new voter */
    public AbstractVoterDTO create(AbstractVoterDTO voterDTO) {
        Integer citizenNumber = voterDTO.getCitizenNumber();
        if (citizenNumber == null) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Citizen number not provided.");

        boolean voterExists = repository.existsByCitizenNumber(citizenNumber);
        if (voterExists) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Voter already exists.");
        }

        String birth = voterDTO.getBirth();
        if (birth == null) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Birth date not provided.");
        else this.checkLegalAge(birth);

        String name = voterDTO.getName();
        if (name == null) throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Name not provided.");

        Voter voter = new Voter();
        voter.setBirth(birth);
        voter.setCitizenNumber(citizenNumber);
        voter.setName(name);

        VoterRequestDTO newVoterDTO = new VoterRequestDTO();
        BeanUtils.copyProperties(voterDTO, newVoterDTO);

        Integer stationId = newVoterDTO.getStationId();
        Station station = null;
        if (stationId != null) {
            station = stationRepository.findById(stationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Station not found with id: " + stationId));
        }

        if (station != null) voter.setStation(station);

        voter = repository.save(voter);

        AbstractStationDTO stationDTO = getStationDTO(voter.getStation());
        
        VoterDTO newVoter = new VoterDTO();
        BeanUtils.copyProperties(voter, newVoter);
        newVoter.setStation(stationDTO);

        return newVoter;
    }


    /* Get voter by Id */
    public AbstractVoterDTO getById(int id) {
        Voter voter = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Voter not found."));
        AbstractStationDTO stationDTO = getStationDTO(voter.getStation());

        VoterDTO voterDTO = new VoterDTO();
        BeanUtils.copyProperties(voter, voterDTO);
        voterDTO.setStation(stationDTO);

        return voterDTO;
    }


    /* Get voter by parameters */
    public List<AbstractVoterDTO> getByParameters(String name, Integer citizenNumber) {
        List<Voter> voters = repository.findByParameters(name, citizenNumber);
        return voters.stream()
                .map(voter -> {
                    VoterDTO voterDTO = new VoterDTO();
                    BeanUtils.copyProperties(voter, voterDTO);
                    AbstractStationDTO stationDTO = getStationDTO(voter.getStation());
                    voterDTO.setStation(stationDTO);
                    return voterDTO;
                })
                .collect(Collectors.toList());
    }


    /* Update voter */
    public AbstractVoterDTO update(int id, AbstractVoterDTO voterDTO) {
        String birth = voterDTO.getBirth();
        if (birth != null) {
            this.checkLegalAge(birth);
        }

        String name = voterDTO.getName();
        Integer citizenNumber = voterDTO.getCitizenNumber();

        Integer stationId = ((VoterRequestDTO) voterDTO).getStationId();
        Station station = null;
        if (stationId != null) {
            station = stationRepository.findById(stationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Station not found."));
        }

        Voter voter = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Voter not found."));
        if (citizenNumber != null) voter.setCitizenNumber(citizenNumber);
        if (birth != null) voter.setBirth(birth);
        if (name != null) voter.setName(name);
        if (station != null) voter.setStation(station);

        voter = repository.save(voter);

        AbstractStationDTO stationDTO = getStationDTO(voter.getStation());
        
        VoterDTO updatedVoter = new VoterDTO();
        BeanUtils.copyProperties(voter, updatedVoter);
        updatedVoter.setStation(stationDTO);

        return updatedVoter;
    }


    /* Delete voter */
    public void delete(int id) {
       repository.deleteById(id);
    }


    /* Check if voter is legal age */
    private void checkLegalAge(String birth) {    
        if (!birth.matches("\\d{4}-\\d{2}-\\d{2}")) { 
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Date format should be YYYY-MM-DD.");
        }    
        LocalDate date = LocalDate.parse(birth);
        LocalDate currDate = LocalDate.now();  

        if ((date == null) || (currDate == null)) {  
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid date.");
        }  

        int age = Period.between(date, currDate).getYears(); 
        if (age < 18) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Voter must be at least 18 years old.");
        }

        return;
    }


    /* Get Station DTO helper */
    private AbstractStationDTO getStationDTO(Station station) {
        if (station == null) return null;

        AbstractStationDTO stationDTO = new StationDTO();
        BeanUtils.copyProperties(station, stationDTO);

        return stationDTO;
    }
}