package asseco.voting.dto.station;

import io.swagger.v3.oas.annotations.media.Schema;

public class StationWithTotalVotersDTO extends StationDTO {
    @Schema(example= "250", description = "Total registered voters")
    private int totalVoters;

    public StationWithTotalVotersDTO() { super(); }

    public int getTotalVoters() { return totalVoters; }
    public void setTotalVoters(int totalVoters) { this.totalVoters = totalVoters; }

}
