package com.rcelik.siafive.chaptereleven.api;


import com.rcelik.siafive.chaptereleven.domain.Ingredient;
import com.rcelik.siafive.chaptereleven.domain.Taco;
import com.rcelik.siafive.chaptereleven.service.TacoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.StreamUtils;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class RouterFunctionConfigTest {

    @Test
    public final void test_shouldReturnRecentTacos(){
        Taco[] tacos = {
                testTaco(1L), testTaco(2L),
                testTaco(3L), testTaco(4L),
                testTaco(5L), testTaco(6L),
                testTaco(7L), testTaco(8L),
                testTaco(9L), testTaco(10L),
                testTaco(11L), testTaco(12L),
                testTaco(13L), testTaco(14L),
                testTaco(15L), testTaco(16L)};

        Flux<Taco> tacoFlux = Flux.fromArray(tacos);

        TacoService tacoService = Mockito.mock(TacoService.class);

        Mockito.when(tacoService.getTacos()).thenReturn(tacoFlux);

        WebTestClient testClient = WebTestClient.bindToRouterFunction(
                new RouterFunctionConfig(tacoService).tacoRouterFunction()
        ).build();

        testClient.get().uri("/design/recent")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$[0].name").isEqualTo("Taco 1")
                .jsonPath("$[0].id").isEqualTo(tacos[0].getId().toString())
                .jsonPath("$[1].name").isEqualTo("Taco 2")
                .jsonPath("$[1].id").isEqualTo(tacos[1].getId().toString())
                .jsonPath("$[2].name").isEqualTo(tacos[2].getName())
                .jsonPath("$[2].id").isEqualTo(tacos[2].getId().toString())
                .jsonPath("$[17]").doesNotExist();
    }

    private Taco testTaco(Long number) {
        Taco taco = new Taco(); taco.setId(number);
        taco.setName("Taco " + number);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(
                new Ingredient("INGA", "Ingredient A", Ingredient.Type.WRAP)); ingredients.add(
                new Ingredient("INGB", "Ingredient B", Ingredient.Type.PROTEIN)); taco.setIngredients(ingredients);
        return taco;
    }

    @Test
    public final void test_shouldReturnRecentTacos_checkWithJsonFile() throws IOException {
        Taco[] tacos = {
                testTaco(1L), testTaco(2L),
                testTaco(3L), testTaco(4L),
                testTaco(5L), testTaco(6L),
                testTaco(7L), testTaco(8L),
                testTaco(9L), testTaco(10L),
                testTaco(11L), testTaco(12L),
                testTaco(13L), testTaco(14L),
                testTaco(15L), testTaco(16L)};

        Flux<Taco> tacoFlux = Flux.fromArray(tacos);
        TacoService tacoService = Mockito.mock(TacoService.class);
        Mockito.when(tacoService.getTacos()).thenReturn(tacoFlux);

        WebTestClient testClient = WebTestClient.bindToRouterFunction(
                new RouterFunctionConfig(tacoService).tacoRouterFunction()
        ).build();

        ClassPathResource recentTacosJsonResource = new ClassPathResource("taco/recent-tacos.json");
        String recentTacosJson = StreamUtils.copyToString(
                recentTacosJsonResource.getInputStream(),
                Charset.defaultCharset());

        testClient.get().uri("/design/recent")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .json(recentTacosJson);
    }

    @Test
    public final void test_getRecentTacos_shouldContainATaco(){
        Taco[] tacos = {
                testTaco(1L), testTaco(2L),
                testTaco(3L), testTaco(4L),
                testTaco(5L), testTaco(6L),
                testTaco(7L), testTaco(8L),
                testTaco(9L), testTaco(10L),
                testTaco(11L), testTaco(12L),
                testTaco(13L), testTaco(14L),
                testTaco(15L), testTaco(16L)};

        Flux<Taco> tacoFlux = Flux.fromArray(tacos);
        TacoService tacoService = Mockito.mock(TacoService.class);
        Mockito.when(tacoService.getTacos()).thenReturn(tacoFlux);

        WebTestClient testClient = WebTestClient.bindToRouterFunction(
                new RouterFunctionConfig(tacoService).tacoRouterFunction()
        ).build();

        testClient.get().uri("/design/recent")
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Taco.class)
                .contains(Arrays.copyOf(tacos, 16));
    }

}