package asseco.voting.dto.station;

import io.swagger.v3.oas.annotations.media.Schema;

public class StationDTO extends AbstractStationDTO {
    @Schema(example= "1", description = "Station ID")
    protected int id;

    public StationDTO() { super(); }

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }
}
