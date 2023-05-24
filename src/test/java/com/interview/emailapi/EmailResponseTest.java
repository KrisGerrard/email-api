package com.interview.emailapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.interview.emailapi.data.SeedEmails;
import com.interview.emailapi.models.EmailDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.security.InvalidKeyException;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

//TODO: mockWebMvcTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailResponseTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    // Integration test to validate end-to-end behaviour.
    @Test
    public void shouldPassIfEmailByIdEquals() throws JsonProcessingException, InvalidKeyException {

        var testId = "d678c0a8-017c-4e45-b811-4de5ad1531eb";
        var expectedEmail = SeedEmails.GetEmails()
                .stream()
                .filter(email -> Objects.equals(email.getId().toString(), testId))
                .findFirst()
                .orElse(null); // Allow the restTemplate to expose http failure codes, ie 404

        var objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();

        var json = restTemplate.getForObject("http://localhost:" + port + "/v1/emails/" + testId, String.class);
        var email = objectMapper.readValue(json, EmailDto.class);

        assertThat(email.id()).isEqualTo(expectedEmail.getId());
        assertThat(email.content()).isEqualTo(expectedEmail.getContent());
        assertThat(email.subject()).isEqualTo(expectedEmail.getSubject());
        assertThat(email.status()).isEqualTo(expectedEmail.getStatus());
        assertThat(email.createdAt()).isEqualTo(expectedEmail.getCreatedAt());
        assertThat(email.lastUpdatedAt()).isEqualTo(expectedEmail.getLastUpdatedAt());
        assertThat(email.sentAt()).isEqualTo(expectedEmail.getSentAt());
        assertThat(email.toRecipients()).isEqualTo(expectedEmail.getTo());
        assertThat(email.ccRecipients()).isEqualTo(expectedEmail.getCc());
        assertThat(email.bccRecipients()).isEqualTo(expectedEmail.getBcc());
    }
}
