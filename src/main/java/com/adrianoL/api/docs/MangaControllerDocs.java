package com.adrianoL.api.docs;

import com.adrianoL.api.dto.AnimeDTO;
import com.adrianoL.api.dto.MangaDTO;
import com.adrianoL.api.dto.input.MangaInput;
import com.adrianoL.domain.exception.AnimeNotFoundException;
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

@Tag(name = "Manga Controller", description = "Endpoints for manga operations")
public interface MangaControllerDocs {

    @Operation(summary = "Get all manga entries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a list with all mangas", content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = MangaDTO.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))

    })
    List<MangaDTO> findAll();

    @Operation(summary = "Get a manga by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns specified manga details", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = MangaDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    MangaDTO findMangaById(@PathVariable Long id);

    @Operation(summary = "Create new manga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Returns created manga details", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = MangaDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "When supplied genre is not found", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    MangaDTO createManga(@RequestBody MangaInput manga);

    @Operation(summary = "Update manga details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Returns updated manga details", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = MangaDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    MangaDTO updateManga(@PathVariable Long id, @RequestBody MangaInput manga);

    @Operation(summary = "Delete manga by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProblemDetail.class)))
    })
    void deleteMangaById(@PathVariable Long id);
}
