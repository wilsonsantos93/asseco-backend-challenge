package asseco.voting.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Voter {
    @Id 
    @GeneratedValue
    @Schema(example= "1", description = "Voter ID")
    private int id;

    @Schema(example= "123456789", description = "Voter citizen number")
    private int citizenNumber;

    @Schema(example= "Marcelo Rebelo de Sousa", description = "Voter name")
    private String name;
    
    @Schema(example= "1980-12-04", description = "Voter birthdate")
    private String birth;

    @ManyToOne
    @JoinColumn(name="station_id")
    private Station station;

    public Voter () {}

    public Voter(int cc, String name, String birth) {
        this.citizenNumber = cc;
        this.name = name;
        this.birth = birth;
    }

    public int getId() { return this.id; }
    public int getCitizenNumber() { return this.citizenNumber; }
    public String getName() { return this.name; }
    public String getBirth() { return this.birth; }
    public Station getStation() { return this.station; }
    
    public void setId(int id) { this.id = id;}
    public void setCitizenNumber(int citizenNumber) { this.citizenNumber = citizenNumber; }
    public void setName(String name) { this.name = name; }
    public void setBirth(String birth) { this.birth = birth; }
    public void setStation(Station station) { this.station = station; }
}
