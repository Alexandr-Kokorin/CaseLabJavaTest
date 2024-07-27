package caselab.java.manager.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@SuppressWarnings("RecordComponentName")
public record FileResponse(
    @NotNull String file,
    @NotEmpty String title,
    @NotNull OffsetDateTime creation_date,
    @NotEmpty String description
) { }
