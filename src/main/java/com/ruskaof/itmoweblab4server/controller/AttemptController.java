package com.ruskaof.itmoweblab4server.controller;

import com.ruskaof.itmoweblab4server.dto.AttemptDTO;
import com.ruskaof.itmoweblab4server.model.Attempt;
import com.ruskaof.itmoweblab4server.service.AttemptService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class AttemptController {
    private final AttemptService attemptService;

    public AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @PostMapping("/add")
    public String addAttempt(@RequestBody AttemptDTO attemptDto) {
        System.out.println("addAttempt " + attemptDto);
        attemptService.addAttempt(attemptDto);
        return "OK, attempt added";
    }

    @GetMapping("/get_all")
    public Collection<Attempt> getAttempts() {
        System.out.println("getAttempts");
        return attemptService.getAttempts();
    }
}
