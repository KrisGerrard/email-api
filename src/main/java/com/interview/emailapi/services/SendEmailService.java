package com.interview.emailapi.services;

import com.interview.emailapi.models.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SendEmailService
{
    private static final Logger logger = LoggerFactory.getLogger(SendEmailService.class);

    public int send(Email email)
    {
        logger.info("Sending email: " + email.getId());

        return 0; // Clean exit/no errors
    }
}
