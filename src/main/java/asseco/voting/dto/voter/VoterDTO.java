package asseco.voting.dto.voter;

import asseco.voting.dto.station.AbstractStationDTO;
import io.swagger.v3.oas.annotations.media.Schema;

public class VoterDTO extends AbstractVoterDTO {
    @Schema(example= "1", description = "Voter id")
    private Integer id;

    @Schema(description = "The station where voter is assigned")
    private AbstractStationDTO station;

    public VoterDTO() { super(); }

    public Integer getId() { return this.id; }
    public AbstractStationDTO getStation() { return station; }
    
    public void setId(Integer id) { this.id = id; }
    public void setStation(AbstractStationDTO station) { this.station = station; }
}
