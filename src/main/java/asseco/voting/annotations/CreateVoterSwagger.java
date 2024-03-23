package asseco.voting.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import asseco.voting.dto.voter.VoterDTO;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Create voter", description = "Creates a new voter.")
@ApiResponses(value = {
    @ApiResponse(
        responseCode = "200", 
        description = "Sucessfully created voter.",
        content = { @Content(schema = @Schema(implementation = VoterDTO.class)) }
    ),
    @ApiResponse(
        responseCode = "500", 
        description = "Voter already exists.", 
        content = { @Content(schema = @Schema(implementation = Void.class)) }
    )
})
public @interface CreateVoterSwagger { 
}