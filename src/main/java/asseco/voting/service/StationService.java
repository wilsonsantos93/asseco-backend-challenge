package asseco.voting.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import asseco.voting.dto.station.AbstractStationDTO;
import asseco.voting.dto.station.StationDTO;
import asseco.voting.dto.station.StationWithTotalVotersDTO;
import asseco.voting.entity.Station;
import asseco.voting.repository.StationRepository;

import org.springframework.beans.BeanUtils;

@Service
public class StationService implements ICrudService<AbstractStationDTO> {
    @Autowired
    private StationRepository repository;


    /* Get all stations */
    public List<AbstractStationDTO> all() {
        List<Station> result = repository.findAll();

        return result.stream()
            .map(station -> {
                AbstractStationDTO stationDTO = new StationWithTotalVotersDTO();
                BeanUtils.copyProperties(station, stationDTO);
                return stationDTO;
            })
            .collect(Collectors.toList());
    }


    /* Create new station */
    public AbstractStationDTO create(AbstractStationDTO stationDTO) {
        String freguesia = stationDTO.getFreguesia();
        String location = stationDTO.getLocation();

        boolean exists = repository.existsByFreguesiaAndLocation(freguesia, location);
        
        if (exists) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Station already exists.");
        }

        Station station = new Station();
        station.setLocation(location);
        station.setFreguesia(freguesia);
        station.setConcelho(stationDTO.getConcelho());
        station.setDistrito(stationDTO.getDistrito());

        station = repository.save(station);

        StationDTO savedStation = new StationDTO();
        BeanUtils.copyProperties(station, savedStation);

        return savedStation;
    }


    /* Get Station by Id */
    public AbstractStationDTO getById(int id) {
        Station station = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Station not found."));

        AbstractStationDTO stationDTO = new StationWithTotalVotersDTO();
        BeanUtils.copyProperties(station, stationDTO);

        return stationDTO;
    }


    /* Get station by parameters */
    public List<AbstractStationDTO> getByParameters(String distrito, String freguesia, String location) {
        List<Station> stations = repository.findByParameters(distrito, freguesia, location);
        return stations.stream()
                .map(station -> {
                    StationWithTotalVotersDTO stationDTO = new StationWithTotalVotersDTO();
                    BeanUtils.copyProperties(station, stationDTO);
                    return stationDTO;
                })
                .collect(Collectors.toList());
    }


    /* Update specific station */
    public AbstractStationDTO update(int id, AbstractStationDTO stationDTO) {
        String freguesia = stationDTO.getFreguesia();
        String distrito = stationDTO.getDistrito();
        String concelho = stationDTO.getConcelho();
        String location = stationDTO.getLocation();

        Station savedStation = repository.findById(id)
            .map(station -> {
                if (location != null) station.setLocation(location);
                if (distrito != null) station.setDistrito(distrito);
                if (concelho != null) station.setConcelho(concelho);
                if (freguesia != null) station.setFreguesia(freguesia);
                
                return repository.save(station);
            })
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Station not found."));

        StationDTO updatedStation = new StationDTO();
        BeanUtils.copyProperties(savedStation, updatedStation);

        return updatedStation;
    }


    /* Delete specific station */
    public void delete(int id) {
       repository.deleteById(id);
    }
}
