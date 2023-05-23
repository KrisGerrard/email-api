package com.interview.emailapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailResponseTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldPassIfStringMatches() {
        var id = "0db4747a-1997-4ccf-b86b-9331a156d426";
        var now = LocalDateTime.now().withNano(0).format(DateTimeFormatter.ISO_DATE_TIME);

        //TODO: mockWebMvcTest

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/emails/" + id,
                String.class))
                .isEqualTo("{\"id\":\"" + id +
                        "\",\"subject\":\"Its an email subject\",\"content\":\"This is the content\",\"createdAt\":\"" + now +
                        "\",\"lastUpdatedAt\":\"" + now +
                        "\",\"sentAt\":\"" + now +
                        "\",\"emailStatus\":\"DRAFT\"}");
    }
}
