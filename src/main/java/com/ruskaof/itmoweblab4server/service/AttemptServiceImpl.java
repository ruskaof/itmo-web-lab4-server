package com.ruskaof.itmoweblab4server.service;

import com.ruskaof.itmoweblab4server.dto.AttemptDTO;
import com.ruskaof.itmoweblab4server.model.Attempt;
import com.ruskaof.itmoweblab4server.repository.AttemptsRepository;
import com.ruskaof.itmoweblab4server.service.logic.AreaChecker;
import com.ruskaof.itmoweblab4server.service.logic.AttemptConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AttemptServiceImpl implements AttemptService {

    private final AttemptsRepository attemptsRepository;

    public AttemptServiceImpl(AttemptsRepository attemptsRepository) {
        this.attemptsRepository = attemptsRepository;
    }

    @Override
    public void addAttempt(AttemptDTO attemptDto) {
        attemptsRepository.save(AttemptConvertor.convertToEntity(attemptDto, AreaChecker.checkArea(attemptDto)));
    }

    @Override
    public Collection<Attempt> getAttempts() {
        return attemptsRepository.findAll();
    }

}
