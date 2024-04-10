package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.services.PersonService;
import br.com.erudio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

// @CrossOrigin
@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {

        @Autowired
        private PersonService services;

        @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })

        @Operation(summary = "Finds everyone", description = "Finds everyone", tags = { "People" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = {
                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))) }),

                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

        })

        public List<PersonVO> findAll() {
                return services.findAll();
        }

        @CrossOrigin(origins = "http://localhost:8080")
        @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YAML })

        @Operation(summary = "Finds a person", description = "Finds a person", tags = { "People" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),

                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

        })
        public PersonVO findById(@PathVariable(value = "id") Long id) {
                return services.findById(id);
        }

        @CrossOrigin(origins = { "http://localhost:8080", "https://erudio.com.br" })
        @PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YAML }, consumes = { MediaType.APPLICATION_JSON,
                                        MediaType.APPLICATION_XML,
                                        MediaType.APPLICATION_YAML })

        @Operation(summary = "Adds a new Person", description = "Adds a new Person by passing a JSON or YML representation of a Person", tags = {
                        "People" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),

                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

        })
        public PersonVO create(@RequestBody PersonVO personVO) {
                return services.createPerson(personVO);
        }

        @PostMapping(value = "/v2", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YAML }, consumes = { MediaType.APPLICATION_JSON,
                                        MediaType.APPLICATION_XML,
                                        MediaType.APPLICATION_YAML })
        public PersonVOV2 createV2(@RequestBody PersonVOV2 personVOV2) {
                return services.createPersonV2(personVOV2);
        }

        @PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML })

        @Operation(summary = "Updates a Person", description = "Updates a Person by passing a JSON or YML representation of a Person", tags = {
                        "People" }, responses = {
                                        @ApiResponse(description = "Updated", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),

                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

        })
        public PersonVO updatePerson(@RequestBody PersonVO personVO) {
                return services.updatePerson(personVO);
        }

        @DeleteMapping(value = "/{id}")
        @Operation(summary = "Deletes a Person", description = "Deletes a Person by passing a JSON or YML representation of a Person", tags = {
                        "People" }, responses = {
                                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),

                                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

        })
        public ResponseEntity<?> deletePerson(@PathVariable(value = "id") Long id) {
                services.deletePerson(id);
                return ResponseEntity.noContent().build();
        }
}
