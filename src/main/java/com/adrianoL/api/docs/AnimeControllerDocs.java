package com.adrianoL.api.docs;

import com.adrianoL.api.dto.AnimeDTO;
import com.adrianoL.api.dto.input.AnimeInput;
import com.adrianoL.domain.exception.AnimeNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Anime Controller", description = "Endpoints for anime operations")
public interface AnimeControllerDocs {

    @Operation(summary = "Get all anime entries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a list with all animes", content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = AnimeDTO.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))

    })
    List<AnimeDTO> getAllAnimes();

    @Operation(summary = "Get an anime by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns specified anime details", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AnimeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    AnimeDTO getAnimeById(@PathVariable Long id);

    @Operation(summary = "Create new anime")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Returns created anime details", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AnimeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "When supplied genre is not found", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    AnimeDTO createAnime(@RequestBody AnimeInput anime);

    @Operation(summary = "Update anime details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Returns updated anime details", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = AnimeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    AnimeDTO updateAnime(@PathVariable Long id, @RequestBody AnimeInput anime);

    @Operation(summary = "Delete anime by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    void deleteAnime(@PathVariable Long id);
}
