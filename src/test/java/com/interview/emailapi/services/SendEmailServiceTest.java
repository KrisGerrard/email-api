package com.interview.emailapi.services;

import com.interview.emailapi.models.Email;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(OutputCaptureExtension.class)
class SendEmailServiceTest {

    private final SendEmailService sendEmailService = new SendEmailService();

    @Test
    void send(CapturedOutput output) {

        var email = new Email();
        email.setId(UUID.randomUUID());

        var statusCode = sendEmailService.send(email);

        assertThat(statusCode).isEqualTo(0);
        assertThat(output).contains("Sending email: " + email.getId());
    }
}
