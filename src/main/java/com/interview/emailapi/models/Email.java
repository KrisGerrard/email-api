package com.interview.emailapi.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

public class Email {
    private UUID id;
    private String subject;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
    private LocalDateTime sentAt;
    private EmailStatus status;
    private Collection<String> to;
    private Collection<String> cc;
    private Collection<String> bcc;

    public Email() {
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public EmailStatus getStatus() {
        return status;
    }

    public void setStatus(EmailStatus status) {
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void createId() {
        id = UUID.randomUUID();
    }

    public Email(
            UUID id,
            String subject,
            String content,
            LocalDateTime createdAt,
            LocalDateTime lastUpdatedAt,
            LocalDateTime sentAt,
            EmailStatus emailStatus,
            Collection<String> to,
            Collection<String> cc,
            Collection<String> bcc) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
        this.sentAt = sentAt;
        this.status = emailStatus;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
    }

    public Collection<String> getCc() {
        return cc;
    }

    public void setCc(Collection<String> cc) {
        this.cc = cc;
    }

    public Collection<String> getTo() {
        return to;
    }

    public void setTo(Collection<String> to) {
        this.to = to;
    }

    public Collection<String> getBcc() {
        return bcc;
    }

    public void setBcc(Collection<String> bcc) {
        this.bcc = bcc;
    }

    public static Email fromEmailDto(EmailDto emailDto) {
        return new Email(emailDto.id(),
        emailDto.subject(),
        emailDto.content(),
        emailDto.createdAt(),
        emailDto.lastUpdatedAt(),
        emailDto.sentAt(),
        emailDto.status(),
        emailDto.toRecipients(),
        emailDto.ccRecipients(),
        emailDto.bccRecipients());
    }
}

