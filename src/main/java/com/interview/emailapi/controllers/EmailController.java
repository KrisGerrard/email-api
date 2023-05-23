package com.interview.emailapi.controllers;

import com.interview.emailapi.models.EmailDto;
import com.interview.emailapi.services.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

//TODO: add HATEOAS Spring library?

@RestController
@RequestMapping(value = "v1/emails")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping()
    @GetMapping(params = { "limit", "offset" })
    public Collection<EmailDto> get(@RequestParam(value = "limit", defaultValue = "50") Long limit,
                                    @RequestParam(value = "offset", defaultValue = "0") Long offset) {

        return emailService
                .getEmails(offset, limit)
                .collect(Collectors.toList());
    }

    @RequestMapping("/{id}")
    public EmailDto getEmailById(@PathVariable UUID id){

        return emailService
                .getEmailByUuid(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public EmailDto createEmail(@RequestBody EmailDto newEmail) {

        return emailService.createEmail(newEmail);
    }

    @PostMapping("/{id}/send")
    public EmailDto sendEmail(@PathVariable UUID id) {

        return emailService.sendEmail(id);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<EmailDto> updateEmail(
            @RequestBody EmailDto updatedEmail,
            @PathVariable("id") UUID id) {

        return ResponseEntity.ok(emailService.updateEmail(id, updatedEmail));
    }
}
