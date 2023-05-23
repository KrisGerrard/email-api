package com.interview.emailapi.services;

import com.interview.emailapi.data.EmailData;
import com.interview.emailapi.models.Email;
import com.interview.emailapi.models.EmailDto;
import com.interview.emailapi.models.EmailStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class EmailService {

    private final EmailData emailData;
    private final SendEmailService sendEmail;

    public EmailService(EmailData emailData, SendEmailService sendEmail) {
        this.emailData = emailData;
        this.sendEmail = sendEmail;
    }

    public Stream<EmailDto> getEmails(long offset, long limit) {
        return emailData
                .getEmailDataHashMap()
                .stream()
                .skip(offset)
                .limit(limit)
                .map(EmailDto::fromEmail);
    }

    public Optional<EmailDto> getEmailByUuid(UUID id) {
        var email = Optional.ofNullable(emailData.getEmailByUuid(id));
        return email.map(EmailDto::fromEmail);
    }

    public EmailDto createEmail(EmailDto emailDto) {
        var email = Email.fromEmailDto(emailDto);

        return EmailDto.fromEmail(emailData.createEmail(email));
    }

    public EmailDto updateEmail(UUID id, EmailDto emailDto) {
        var email = Email.fromEmailDto(emailDto);

        return EmailDto.fromEmail(emailData.updateEmail(id, email));
    }

    public EmailDto sendEmail(UUID id) {
        var email = emailData.getEmailByUuid(id);
        var statusCode = sendEmail.send(email);

        email.setStatus(statusCode == 0 ? EmailStatus.SENDING : EmailStatus.FAILED);
        email.setSentAt(LocalDateTime.now());

        if (statusCode != 0) {
            throw new RuntimeException("Sending email failed");
        }

        return EmailDto.fromEmail(emailData.updateEmail(id, email));
    }
}
