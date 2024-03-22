package asseco.voting.dto.voter;

import io.swagger.v3.oas.annotations.media.Schema;

public abstract class AbstractVoterDTO {
    @Schema(example= "123456789", description = "Voter citizen number")
    private Integer citizenNumber;
    
    @Schema(example= "Marcelo Rebelo de Sousa", description = "Voter name")
    private String name;

    @Schema(example= "1970-04-12", description = "Voter birthdate")
    private String birth;

    public AbstractVoterDTO() {}

    public String getBirth() { return birth; }
    public Integer getCitizenNumber() { return citizenNumber; }
    public String getName() { return name; }

    public void setBirth(String birth) { this.birth = birth; }
    public void setCitizenNumber(Integer citizenNumber) { this.citizenNumber = citizenNumber; }
    public void setName(String name) { this.name = name; }
}
