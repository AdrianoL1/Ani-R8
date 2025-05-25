package com.adrianoL.api.docs;

import com.adrianoL.api.dto.AnimeDTO;
import com.adrianoL.api.dto.GenreDTO;
import com.adrianoL.domain.exception.AnimeNotFoundException;
import com.adrianoL.domain.exception.GenreNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Genre Controller", description = "Endpoints for genre operations")
public interface GenreControllerDocs {

    @Operation(summary = "Get all genre entries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a list with all genres", content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = GenreDTO.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))

    })
    List<GenreDTO> findAll();

    @Operation(summary = "Get a genre by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns specified genre details", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenreDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    GenreDTO getGenreById(@PathVariable Long id);

    @Operation(summary = "Create new genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Returns created genre details", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenreDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    GenreDTO createGenre(@RequestBody GenreDTO genre);

    @Operation(summary = "Update genre details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Returns updated genre details", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = GenreDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    GenreDTO updateGenre(@PathVariable Long id, @RequestBody GenreDTO genre);

    @Operation(summary = "Delete genre by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    void deleteGenre(@PathVariable Long id);
}
