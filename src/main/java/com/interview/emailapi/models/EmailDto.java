package com.interview.emailapi.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

public record EmailDto(
    UUID id,
    String subject,
    String content,
    LocalDateTime createdAt,
    LocalDateTime lastUpdatedAt,
    LocalDateTime sentAt,
    EmailStatus status,
    Collection<String> toRecipients,
    Collection<String> ccRecipients,
    Collection<String> bccRecipients
)
{
    public static EmailDto fromEmail(Email email)
    {
       return new EmailDto(
                email.getId(),
                email.getSubject(),
                email.getContent(),
                email.getCreatedAt(),
                email.getLastUpdatedAt(),
                email.getSentAt(),
                email.getStatus(),
                email.getTo(),
                email.getCc(),
                email.getBcc()
        );
    }
}
