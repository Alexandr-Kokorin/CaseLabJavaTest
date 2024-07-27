package caselab.java.manager.controller.dto;

public record ErrorResponse(
    int statusCode,
    String massage
) { }
