package asseco.voting.dto.voter;

import io.swagger.v3.oas.annotations.media.Schema;

public class VoterRequestDTO extends AbstractVoterDTO {
    @Schema(example= "1", description = "The station ID")
    private Integer stationId;

    public VoterRequestDTO() {}

    public Integer getStationId() { return stationId; }
    public void setStationId(Integer stationId) { this.stationId = stationId; }
}
