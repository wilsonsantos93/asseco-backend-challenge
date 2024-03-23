package asseco.voting.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Delete voter by ID", description = "Deletes a voter by its ID")
@ApiResponse(responseCode = "200", description = "Sucessfully deleted voter.")
public @interface DeleteVoterSwagger {
}
