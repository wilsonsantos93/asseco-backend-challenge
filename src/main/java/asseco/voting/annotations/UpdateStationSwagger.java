package asseco.voting.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import asseco.voting.dto.station.StationDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Update station by ID", description = "Updates a specific station by its ID. Send only the fields that need to be updated.")
@ApiResponses(value = {
    @ApiResponse(
        responseCode = "200", 
        description = "Sucessfully updated station.",
        content = { @Content(schema = @Schema(implementation = StationDTO.class)) }
    ),
    @ApiResponse(
        responseCode = "404", 
        description = "Station not found.", 
        content = { @Content(schema = @Schema(implementation = Void.class)) }
    )
})
public @interface UpdateStationSwagger { 
}