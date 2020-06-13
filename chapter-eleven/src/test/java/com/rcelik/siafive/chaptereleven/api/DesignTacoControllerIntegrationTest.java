package com.rcelik.siafive.chaptereleven.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("fReactive")
public class DesignTacoControllerIntegrationTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void shouldReturnRecentTacos() {
        webTestClient.get().uri("/design/recent").accept(MediaType.APPLICATION_JSON).exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$[0].name").isEqualTo("Carnivore")
                .jsonPath("$[0].id").isEqualTo("1")
                .jsonPath("$[1].name").isEqualTo("Bovine Bounty")
                .jsonPath("$[1].id").isEqualTo("2")
                .jsonPath("$[3]").doesNotExist();
    }
}