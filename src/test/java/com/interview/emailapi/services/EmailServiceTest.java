package com.interview.emailapi.services;

import com.interview.emailapi.data.EmailData;
import com.interview.emailapi.data.SeedEmails;
import com.interview.emailapi.models.EmailDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    EmailData emailData;

    @Mock
    SendEmailService sendEmailService;

    EmailService emailService;

    @BeforeEach
    void setUp() {
        when(emailData.getEmailDataHashMap()).thenReturn(SeedEmails.GetEmails());

        emailService = new EmailService(emailData, sendEmailService);
    }

    @Test
    void getEmails() {
        var result = emailService.getEmails(0, 10).toArray();

        assertEquals(3, result.length);
        assertEquals("d678c0a8-017c-4e45-b811-4de5ad1531eb", ((EmailDto)result[0]).id().toString());
        assertEquals("e67316b4-f3b4-4a74-8688-2937991a3805", ((EmailDto)result[1]).id().toString());
        assertEquals("2f2da0ea-58b4-4838-a86f-8c862c8cead3", ((EmailDto)result[2]).id().toString());
    }

//    @Test
//    void getEmailByUuid() {
//    }
//
//    @Test
//    void createEmail() {
//    }
//
//    @Test
//    void updateEmail() {
//    }
//
//    @Test
//    void sendEmail() {
//    }
}