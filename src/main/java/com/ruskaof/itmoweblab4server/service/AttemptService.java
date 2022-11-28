package com.ruskaof.itmoweblab4server.service;

import com.ruskaof.itmoweblab4server.dto.AttemptDTO;
import com.ruskaof.itmoweblab4server.model.Attempt;

import java.util.Collection;

public interface AttemptService {
    void addAttempt(AttemptDTO attemptDto);
    Collection<Attempt> getAttempts();
}

