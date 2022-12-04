package com.ruskaof.itmoweblab4server.controller;

import com.ruskaof.itmoweblab4server.dto.AttemptDTO;
import com.ruskaof.itmoweblab4server.dto.AttemptListWithOffsetDTO;
import com.ruskaof.itmoweblab4server.model.Attempt;
import com.ruskaof.itmoweblab4server.service.AttemptService;
import com.ruskaof.itmoweblab4server.service.logic.AttemptConvertor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AttemptController {
    private final AttemptService attemptService;

    public AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @CrossOrigin
    @PostMapping("/add")
    public Attempt addAttempt(@RequestBody AttemptDTO attemptDto) {
        System.out.println("addAttempt " + attemptDto);
        return attemptService.addAttempt(attemptDto);
    }

    @CrossOrigin
    @GetMapping("/get_all")
    public List<Attempt> getAttempts() {
        System.out.println("getAttempts");
        return attemptService.getAttempts();
    }

    @CrossOrigin
    @DeleteMapping("/delete_all")
    public String deleteAllAttempts() {
        System.out.println("deleteAllAttempts");
        attemptService.removeAll();
        return "OK, all attempts deleted";
    }

    @CrossOrigin
    @GetMapping("/get_with_offset")
    public AttemptListWithOffsetDTO getPartAttempts(@RequestParam int offset, @RequestParam int size) {
        final AttemptListWithOffsetDTO toReturn = new AttemptListWithOffsetDTO(
                attemptService.getPartAttempts(offset, size),
                attemptService.getAttemptsCount(),
                offset
        );
        System.out.println("getPartAttempts " + toReturn);
        System.out.println("offset 10, size 12: " + attemptService.getPartAttempts(10, 12));
        return toReturn;
    }
}
