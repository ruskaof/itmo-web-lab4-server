package com.ruskaof.itmoweblab4server.controller;

import com.ruskaof.itmoweblab4server.dto.AttemptDTO;
import com.ruskaof.itmoweblab4server.dto.AttemptListWithOffsetDTO;
import com.ruskaof.itmoweblab4server.model.Attempt;
import com.ruskaof.itmoweblab4server.service.AttemptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AttemptController {
    private static final Logger log = LoggerFactory.getLogger(AttemptController.class);
    private final AttemptService attemptService;

    public AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @PostMapping("/add")
    public Attempt addAttempt(@RequestBody AttemptDTO attemptDto) {
        log.info("Adding attempt with x = " + attemptDto.getX() + " y = " + attemptDto.getY() + " r = " + attemptDto.getR());
        return attemptService.addAttempt(attemptDto);
    }


    @DeleteMapping("/delete_all")
    public String deleteAllAttempts() {
        log.info("Deleting all attempts");
        attemptService.removeAll();
        return "OK, all attempts deleted";
    }

    /**
     * @param offset - offset from the beginning of the list
     * @param size   - number of elements to return
     * @return - list of attempts with offset
     */
    @CrossOrigin
    @GetMapping("/get_with_offset")
    public AttemptListWithOffsetDTO getPartAttempts(@RequestParam int offset,
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
        final var data = attemptService.getPartAttempts(offset, size, id, x, y, r, result, time, processingTime);
        final var itemAfter = attemptService.getPartAttempts(offset + size, 1, id, x, y, r, result, time, processingTime);
        return new AttemptListWithOffsetDTO(data, !itemAfter.isEmpty());
    }

    @GetMapping("/get_count")
    public long getAttemptsCount() {
        log.info("Getting attempts count");
        return attemptService.getAttemptsCount();
    }
}
