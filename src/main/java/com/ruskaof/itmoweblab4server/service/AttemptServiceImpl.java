package com.ruskaof.itmoweblab4server.service;

import com.ruskaof.itmoweblab4server.dto.AttemptDTO;
import com.ruskaof.itmoweblab4server.model.Attempt;
import com.ruskaof.itmoweblab4server.repository.AttemptsRepository;
import com.ruskaof.itmoweblab4server.service.logic.AreaChecker;
import com.ruskaof.itmoweblab4server.service.logic.AttemptConvertor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class AttemptServiceImpl implements AttemptService {

    private final AttemptsRepository attemptsRepository;

    public AttemptServiceImpl(AttemptsRepository attemptsRepository) {
        this.attemptsRepository = attemptsRepository;
    }

    @Override
    public Attempt addAttempt(AttemptDTO attemptDto) {
        final long startProcessingTime = System.nanoTime();
        final Attempt attempt = AttemptConvertor.convertToEntity(attemptDto, AreaChecker.checkArea(attemptDto));
        attempt.setAttemptTime(LocalDateTime.now());
        attempt.setProcessingTimeNanos(System.nanoTime() - startProcessingTime);
        // save the attempt and return the newly added entity
        return attemptsRepository.save(attempt);
    }

    @Override
    public List<Attempt> getAttempts() {
        return attemptsRepository.findAll();
    }

    @Override
    public void removeAll() {
        attemptsRepository.deleteAll();
    }

    @Override
    public List<Attempt> getPartAttempts(int offset, int size) {
        final Pageable pageable = new OffsetBasedPageRequest(offset, size);
        return attemptsRepository.findAll(pageable).getContent();
    }

    @Override
    public long getAttemptsCount() {
        return attemptsRepository.count();
    }


}

