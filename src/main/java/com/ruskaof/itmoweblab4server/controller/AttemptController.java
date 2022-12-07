package com.ruskaof.itmoweblab4server.controller;

import com.ruskaof.itmoweblab4server.dto.AttemptDTO;
import com.ruskaof.itmoweblab4server.dto.AttemptListWithOffsetDTO;
import com.ruskaof.itmoweblab4server.model.Attempt;
import com.ruskaof.itmoweblab4server.service.AttemptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AttemptController {
    private final AttemptService attemptService;

    public AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @PostMapping("/add")
    public Attempt addAttempt(@RequestBody AttemptDTO attemptDto) {
        System.out.println("addAttempt " + attemptDto);
        return attemptService.addAttempt(attemptDto);
    }

    @GetMapping("/get_all")
    public List<Attempt> getAttempts() {
        System.out.println("getAttempts");
        return attemptService.getAttempts();
    }

    @DeleteMapping("/delete_all")
    public String deleteAllAttempts() {
        System.out.println("deleteAllAttempts");
        attemptService.removeAll();
        return "OK, all attempts deleted";
    }

    /**
     * @param offset - offset from the beginning of the list (can be negative to get the last elements)
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
                                                    @RequestParam(required = false) String username
    ) {
        AttemptListWithOffsetDTO toReturn;
        if (offset < 0) {
            long count = attemptService.getAttemptsCount();
            toReturn = new AttemptListWithOffsetDTO(
                    //attemptService.getPartAttempts((int) (count + offset < 0 ? 0 : count + offset), size, id, x, y, r, result, time, username),
                    attemptService.getPartAttempts((int) (count + offset < 0 ? 0 : count + offset), size),
                    count);
        } else {
            toReturn = new AttemptListWithOffsetDTO(
                    attemptService.getPartAttempts(offset, size),
                    attemptService.getAttemptsCount());
        }
        System.out.println("getPartAttempts " + toReturn);
        return toReturn;
    }

    @GetMapping("/get_count")
    public long getAttemptsCount() {
        System.out.println("getAttemptsCount");
        return attemptService.getAttemptsCount();
    }
}
