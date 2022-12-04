package com.ruskaof.itmoweblab4server.service;

import com.ruskaof.itmoweblab4server.dto.AttemptDTO;
import com.ruskaof.itmoweblab4server.model.Attempt;

import java.util.Collection;
import java.util.List;

public interface AttemptService {
    Attempt addAttempt(AttemptDTO attemptDto);
    List<Attempt> getAttempts();

    void removeAll();

    List<Attempt> getPartAttempts(int offset, int size);
    long getAttemptsCount();
}

