package asseco.voting.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import asseco.voting.dto.station.StationWithTotalVotersDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Get all stations", description = "Gets all stations from database.")
@ApiResponses(value = {
    @ApiResponse(
        responseCode = "200", 
        description = "Sucessfully fetched stations.",
        content = { @Content(array = @ArraySchema(schema = @Schema(implementation = StationWithTotalVotersDTO.class))) }
    )
})
public @interface GetAllStationsSwagger {
}
