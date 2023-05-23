package com.interview.emailapi.data;

import com.interview.emailapi.models.Email;
import com.interview.emailapi.models.EmailStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class SeedEmails {
    public static Collection<Email> GetEmails() {
        var emails = new ArrayList<Email>();
        var now = LocalDateTime.now();

        emails.add(new Email(
                UUID.fromString("d678c0a8-017c-4e45-b811-4de5ad1531eb"),
                "This is static test email 1",
                "Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.",
                now.minusDays(2),
                now.minusDays(1),
                null,
                EmailStatus.DRAFT,
                List.of("test1@email.com"),
                List.of(),
                List.of()
        ));

        emails.add(new Email(
                UUID.fromString("e67316b4-f3b4-4a74-8688-2937991a3805"),
                "This is static test email 2",
                "Ut enim ad minim veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam.",
                now.minusDays(5),
                now.minusDays(3),
                now.minusDays(3),
                EmailStatus.SENT,
                List.of("test2@email.com"),
                List.of("test3@email.com"),
                List.of()
        ));

        emails.add(new Email(
                UUID.fromString("2f2da0ea-58b4-4838-a86f-8c862c8cead3"),
                "This is static test email 3",
                "Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                now.minusDays(4),
                now.minusDays(1),
                now.minusDays(1),
                EmailStatus.FAILED,
                List.of("test4@email.com"),
                List.of(),
                List.of("test5@email.com")
        ));

        return emails;
    }
}
