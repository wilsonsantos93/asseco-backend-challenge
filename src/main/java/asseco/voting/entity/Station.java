package asseco.voting.entity;

import org.hibernate.annotations.Formula;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Station {
    @Id 
    @GeneratedValue
    @Schema(example= "1", description = "Station ID")
    private int id;
    
    @Schema(example= "Lisboa", description = "Station district")
    private String distrito;
    
    @Schema(example= "Lisboa", description = "Station municipality")
    private String concelho;
    
    @Schema(example= "Parque das Nações", description = "Station parish")
    private String freguesia;
    
    @Schema(example= "Escola Secundária do Parquinho", description = "Station location")
    private String location;

    @Formula(value = "(SELECT COUNT(*) FROM Voter v WHERE v.station_id=id)")
    public int totalVoters;

    //@OneToMany(mappedBy = "station")
    //private List<Voter> voters = new ArrayList<>();

    public Station () {}

    public Station(String distrito, String concelho, String freguesia, String location) {
        this.distrito = distrito;
        this.concelho = concelho;
        this.freguesia = freguesia;
        this.location = location;
    }

    public int getId() {  return this.id; }
    public String getDistrito() { return this.distrito; }
    public String getConcelho() { return this.concelho; }
    public String getFreguesia() { return this.freguesia; }
    public String getLocation() { return this.location; }
    
    public void setId(int id) { this.id = id; }
    public void setDistrito(String distrito) { this.distrito = distrito; }
    public void setConcelho(String concelho) { this.concelho = concelho; }
    public void setFreguesia(String freguesia) { this.freguesia = freguesia; }
    public void setLocation(String location) { this.location = location; }
}
