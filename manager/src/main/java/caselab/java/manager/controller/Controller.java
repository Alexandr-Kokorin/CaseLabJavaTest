package caselab.java.manager.controller;

import caselab.java.manager.controller.dto.ErrorResponse;
import caselab.java.manager.controller.dto.FileRequest;
import caselab.java.manager.controller.dto.FileResponse;
import caselab.java.manager.controller.dto.IdResponse;
import caselab.java.manager.controller.dto.ListFilesResponse;
import caselab.java.manager.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@SuppressWarnings({"MultipleStringLiterals", "MagicNumber"})
@AllArgsConstructor
@RestController
public class Controller {

    private FileService fileService;

    @Operation(summary = "Добавить файл")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Файл добавлен",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = IdResponse.class))),
        @ApiResponse(responseCode = "400",
                     description = "Некорректные параметры запроса",
                     content = @Content)
    })
    @PostMapping(path = "/file/add")
    public ResponseEntity<IdResponse> addFile(@Valid @RequestBody FileRequest fileRequest) {
        return ResponseEntity.ok(fileService.add(fileRequest));
    }

    @Operation(summary = "Получить файл")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Файл успешно получен",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = FileResponse.class))),
        @ApiResponse(responseCode = "400",
                     description = "Некорректные параметры запроса",
                     content = @Content),
        @ApiResponse(responseCode = "404",
                     description = "Файл не найден",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(path = "/file/{id}")
    public ResponseEntity<FileResponse> getFile(@Positive @PathVariable long id) {
        var file = fileService.get(id);
        if (Objects.isNull(file)) {
            throw HttpClientErrorException.NotFound.create(HttpStatusCode.valueOf(404),
                "Файл не найден", HttpHeaders.EMPTY, null, null);
        }
        return ResponseEntity.ok(file);
    }

    @Operation(summary = "Получить все файлы")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Файлы успешно получены",
                     content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = ListFilesResponse.class))),
        @ApiResponse(responseCode = "400",
                     description = "Некорректные параметры запроса",
                     content = @Content)
    })
    @GetMapping(path = "/files")
    public ResponseEntity<ListFilesResponse> getFiles() {
        return ResponseEntity.ok(fileService.getList());
    }
}
