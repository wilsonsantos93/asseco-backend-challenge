package asseco.voting.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import asseco.voting.dto.voter.VoterDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Update a voter by ID", description = "Updates a voter by its ID. Send only the fields that need to be updated.")
@ApiResponses(value = {
    @ApiResponse(
        responseCode = "200", 
        description = "Sucessfully updated voter.", 
        content = { @Content(schema = @Schema(implementation = VoterDTO.class)) }
    ),
    @ApiResponse(
        responseCode = "404", 
        description = "Voter not found.", 
        content = { @Content(schema = @Schema(implementation = Void.class)) }
    )
})
public @interface UpdateVoterSwagger {
}
