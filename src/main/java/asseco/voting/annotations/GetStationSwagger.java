package asseco.voting.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import asseco.voting.dto.station.StationWithTotalVotersDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Get station by ID", description = "Gets a specific station by its ID.")
@ApiResponses(value = {
    @ApiResponse(
        responseCode = "200", 
        description = "Sucessfully fetched station.",
        content = { @Content(schema = @Schema(implementation = StationWithTotalVotersDTO.class)) }
    ),
    @ApiResponse(
        responseCode = "404", 
        description = "Station not found.", 
        content = { @Content(schema = @Schema(implementation = Void.class)) }
    )
})
public @interface GetStationSwagger {
}