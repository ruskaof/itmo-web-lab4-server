package com.ruskaof.itmoweblab4server.service.logic;

import com.ruskaof.itmoweblab4server.dto.AttemptDTO;
import com.ruskaof.itmoweblab4server.model.Attempt;

public class AttemptConvertor {
    private AttemptConvertor() {
        throw new IllegalStateException("Utility class");
    }

    public static AttemptDTO convertToDto(Attempt attempt) {
        return new AttemptDTO(attempt.getX(), attempt.getY(), attempt.getR());
    }

    public static Attempt convertToEntity(AttemptDTO attemptDto, boolean result) {
        return new Attempt(attemptDto.getX(), attemptDto.getY(), attemptDto.getR(), result);
    }
}
