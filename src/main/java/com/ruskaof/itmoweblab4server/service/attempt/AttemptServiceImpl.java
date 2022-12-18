package com.ruskaof.itmoweblab4server.service.attempt;

import com.ruskaof.itmoweblab4server.dto.AddAttemptRequest;
import com.ruskaof.itmoweblab4server.model.Attempt;
import com.ruskaof.itmoweblab4server.repository.AttemptsRepository;
import com.ruskaof.itmoweblab4server.service.logic.AreaChecker;
import com.ruskaof.itmoweblab4server.service.logic.AttemptConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttemptServiceImpl implements AttemptService {
    private static final Logger log = LoggerFactory.getLogger(AttemptServiceImpl.class);
    private final AttemptsRepository attemptsRepository;

    public AttemptServiceImpl(AttemptsRepository attemptsRepository) {
        this.attemptsRepository = attemptsRepository;
    }

    @Override
    public Attempt addAttempt(AddAttemptRequest addAttemptRequest) {
        final long startProcessingTime = System.nanoTime();
        final Attempt attempt = AttemptConvertor.convertToEntity(addAttemptRequest, AreaChecker.checkArea(addAttemptRequest));
        attempt.setAttemptTime(LocalDateTime.now());
        attempt.setProcessingTimeNanos(System.nanoTime() - startProcessingTime);
        // save the attempt and return the newly added entity
        return attemptsRepository.save(attempt);
    }

    @Override
    public void removeAll() {
        attemptsRepository.deleteAll();
    }

    @Override
    public List<Attempt> getPartAttempts(Integer offset, Integer size, String id, String x, String y, String r, String result, String time, String processingTime) {
        try {
            return attemptsRepository.getPartAttempts(offset, size, id, x, y, r, result, time, processingTime);
        } catch (Exception e) {
            log.error("Error while getting part of attempts", e);
            return List.of();
        }
    }
}

