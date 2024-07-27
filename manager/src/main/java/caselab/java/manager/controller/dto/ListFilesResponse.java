package caselab.java.manager.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;

public record ListFilesResponse(
    @PositiveOrZero int count,
    @NotNull List<FileResponse> files
) { }
