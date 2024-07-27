package caselab.java.manager.controller.dto;

import jakarta.validation.constraints.PositiveOrZero;

public record IdResponse(
    @PositiveOrZero long id
) { }
