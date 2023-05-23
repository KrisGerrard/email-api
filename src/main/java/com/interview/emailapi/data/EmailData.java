package com.interview.emailapi.data;

import com.interview.emailapi.models.Email;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class EmailData {

    private final Map<UUID, Email> emailDataHashMap = new HashMap<>();

    public EmailData() {
        SeedEmails.GetEmails()
                .forEach(email -> emailDataHashMap.put(email.getId(), email));
    }

    public Collection<Email> getEmailDataHashMap() {
        return emailDataHashMap.values();
    }

    public Email getEmailByUuid(UUID id) {
        return emailDataHashMap.get(id);
    }

    public Email createEmail(Email email) {
        email.createId(); // Id defined on server, not by client
        email.setCreatedAt(LocalDateTime.now());
        email.setLastUpdatedAt(LocalDateTime.now());

        emailDataHashMap.put(email.getId(), email);

        return emailDataHashMap.get(email.getId());
    }

    public Email updateEmail(UUID id, Email email) {
        var target = emailDataHashMap.get(id);

        if (target != null) {
            // Don't allow Id to be set from client
            //target.setId(((email.getId() != null) ? email : target).getId());

            target.setSubject(((email.getSubject() != null) ? email : target).getSubject());
            target.setContent(((email.getContent() != null) ? email : target).getContent());
            target.setCreatedAt(((email.getCreatedAt() != null) ? email : target).getCreatedAt());
            target.setLastUpdatedAt(((email.getLastUpdatedAt() != null) ? email : target).getLastUpdatedAt());
            target.setSentAt(((email.getSentAt() != null) ? email : target).getSentAt());
            target.setStatus(((email.getStatus() != null) ? email : target).getStatus());
            target.setTo(((email.getTo() != null) ? email : target).getTo());
            target.setCc(((email.getCc() != null) ? email : target).getCc());
            target.setBcc(((email.getBcc() != null) ? email : target).getBcc());
            target.setLastUpdatedAt(LocalDateTime.now());
        }

        return emailDataHashMap.get(email.getId());
    }
}
