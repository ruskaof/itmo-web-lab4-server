package com.ruskaof.itmoweblab4server.controller;

import com.ruskaof.itmoweblab4server.dto.AddAttemptRequest;
import com.ruskaof.itmoweblab4server.dto.AddAttemptResponse;
import com.ruskaof.itmoweblab4server.dto.AttemptListWithOffsetResponse;
import com.ruskaof.itmoweblab4server.service.attempt.AttemptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api")
public class AttemptController {
    private final AttemptService attemptService;

    public AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @PostMapping("/add")
    public ResponseEntity<AddAttemptResponse> addAttempt(@RequestBody AddAttemptRequest addAttemptRequest) {
        log.info("Adding attempt with x = " + addAttemptRequest.x() + " y = " + addAttemptRequest.y() + " r = " + addAttemptRequest.r());
        final var addedAttempt = attemptService.addAttempt(addAttemptRequest);
        final var responseDto = new AddAttemptResponse(
                addedAttempt.getId(),
                addedAttempt.getAttemptTime(),
                addedAttempt.getProcessingTimeNanos(),
                addedAttempt.getX(), addedAttempt.getY(),
                addedAttempt.getR(), addedAttempt.isResult()
        );
        return ResponseEntity.ok(responseDto);
    }


    @SuppressWarnings("rawtypes") // it is convenient to return ResponseEntity without generic type for empty 200.
    @DeleteMapping("/delete_all")
    public ResponseEntity deleteAllAttempts() {
        attemptService.removeAll();
        return ResponseEntity.ok().build();
    }

    /**
     * @param offset - offset from the beginning of the list
     * @param size   - number of elements to return
     * @return - list of attempts with offset
     */
    @GetMapping("/get_with_offset")
    public ResponseEntity<AttemptListWithOffsetResponse> getPartAttempts(@RequestParam int offset,
                                                                         @RequestParam int size,
                                                                         @RequestParam(required = false) String id,
                                                                         @RequestParam(required = false) String x,
                                                                         @RequestParam(required = false) String y,
                                                                         @RequestParam(required = false) String r,
                                                                         @RequestParam(required = false) String result,
                                                                         @RequestParam(required = false) String time,
                                                                         @RequestParam(required = false) String processingTime
    ) {
        log.info("Getting part of attempts with offset = " + offset + " and size = " + size);
        final var data = attemptService
                .getPartAttempts(offset, size, id, x, y, r, result, time, processingTime);
        final var itemAfter = attemptService
                .getPartAttempts(offset + size, 1, id, x, y, r, result, time, processingTime);
        return ResponseEntity.ok(new AttemptListWithOffsetResponse(data, !itemAfter.isEmpty()));
    }

}
