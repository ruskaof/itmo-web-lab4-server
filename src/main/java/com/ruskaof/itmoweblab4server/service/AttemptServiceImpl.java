package com.ruskaof.itmoweblab4server.service;

import com.ruskaof.itmoweblab4server.dto.AttemptDTO;
import com.ruskaof.itmoweblab4server.model.Attempt;
import com.ruskaof.itmoweblab4server.repository.AttemptsRepository;
import com.ruskaof.itmoweblab4server.service.logic.AreaChecker;
import com.ruskaof.itmoweblab4server.service.logic.AttemptConvertor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public void removeAll() {
        attemptsRepository.deleteAll();
    }

//    @Override
//    public List<Attempt> getPartAttempts(int offset, int size) {
//        final Pageable pageable = new OffsetBasedPageRequest(offset, size);
//        return attemptsRepository.findAll(pageable).getContent();
//    }

    @Override
    public List<Attempt> getPartAttempts(Integer offset, Integer size, String id, String x, String y, String r, String result, String time, String processingTime) {
        System.out.println("AttemptServiceImpl.getPartAttempts");
        try {
            List<Attempt> searchResult = attemptsRepository.getPartAttempts(offset, size, id, x, y, r, result, time, processingTime);
            System.out.println("Search result: " + searchResult);
            return searchResult;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return List.of();
        }
    }

    @Override
    public long getAttemptsCount() {
        return attemptsRepository.count();
    }


}

