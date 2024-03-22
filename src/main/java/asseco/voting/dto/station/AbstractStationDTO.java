package asseco.voting.dto.station;

import io.swagger.v3.oas.annotations.media.Schema;

public abstract class AbstractStationDTO {
    @Schema(example= "Lisboa", description = "Station district")
    protected String distrito;

    @Schema(example= "Lisboa", description = "Station municipality")
    protected String concelho;

    @Schema(example= "Parque das Nações", description = "Station parish")
    protected String freguesia;

    @Schema(example= "Escola Secundária do Parquinho", description = "Station location")
    protected String location;

    public AbstractStationDTO () {}

    public String getConcelho() { return this.concelho; }
    public String getDistrito() { return this.distrito; }
    public String getFreguesia() { return this.freguesia; }
    public String getLocation() { return this.location; }

    public void setConcelho(String concelho) { this.concelho = concelho; }
    public void setDistrito(String distrito) { this.distrito = distrito; }
    public void setFreguesia(String freguesia) { this.freguesia = freguesia; }
    public void setLocation(String location) { this.location = location; }
}
