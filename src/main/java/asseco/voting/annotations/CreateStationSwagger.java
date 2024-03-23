package asseco.voting.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import asseco.voting.dto.station.StationDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Create station", description = "Creates a new station.")
@ApiResponses(value = {
    @ApiResponse(
        responseCode = "200", 
        description = "Sucessfully created station.", 
        content = { @Content(schema = @Schema(implementation = StationDTO.class)) }
    ),
    @ApiResponse(
        responseCode = "500", 
        description = "Station already exists.",
        content = { @Content(schema = @Schema(implementation = Void.class)) }
    )
})
public @interface CreateStationSwagger {
}
