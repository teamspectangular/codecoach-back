package com.switchfully.spectangular.controllers;

import com.switchfully.spectangular.domain.session.SessionStatus;
import com.switchfully.spectangular.dtos.CreateSessionDto;
import com.switchfully.spectangular.dtos.SessionDto;
import com.switchfully.spectangular.dtos.SessionStatusDto;
import com.switchfully.spectangular.services.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "sessions")
public class SessionController {

    private final SessionService sessionService;
    private final Logger logger = LoggerFactory.getLogger(SessionController.class);

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PreAuthorize(value = "hasAuthority('CREATE_SESSION')")
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public SessionDto createSession(@RequestBody CreateSessionDto createSessionDto, @RequestHeader (name="Authorization") String token){
        logger.info("Received POST request to create a new session");
        return sessionService.createSession(createSessionDto, token);
    }
    @PreAuthorize(value = "hasAuthority('GET_ALL_COACHING_SESSION')")
    @GetMapping(path = "/coach", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<SessionDto> getAllCoachSessions(@RequestHeader (name="Authorization") String token){
        return sessionService.getAllSessionByCoach(token);
    }

    @PreAuthorize(value = "hasAuthority('GET_ALL_COACHEE_SESSIONS')")
    @GetMapping(path = "/coachee", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<SessionDto> getAllCoacheeSessions(@RequestHeader (name="Authorization") String token){
        return sessionService.getAllSessionByCoachee(token);
    }

    @PreAuthorize(value = "hasAuthority('GET_ALL_COACHEE_SESSIONS')")
    @PostMapping(path = "/{id}/status", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SessionDto updateStatus(@RequestHeader (name="Authorization") String token, @PathVariable int id, @RequestBody SessionStatusDto status){

        return sessionService.updateSessionStatus(id, status.getStatus());
    }
}
