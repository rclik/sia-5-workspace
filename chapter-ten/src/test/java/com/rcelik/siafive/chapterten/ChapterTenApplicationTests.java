package com.rcelik.siafive.chapterten;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
class ChapterTenApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public final void crateAFlux_just(){
        // just crating a flux type
        Flux<String> fruitPublisher = Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry");

        // to create a flow, need to add subscriber to flux type
        fruitPublisher.subscribe( fruit -> System.out.println("Here is some fruit" + fruit));

        // but in order to make it test, not print it to the screen, but use StepVerifier for reactor project core types
        // like Flux or Mono

//        StepVerifier.create(fruitFlux).expectNext("Apple")
//                .expectNext("Orange")
//                .expectNext("Grape")
//                .expectNext("Banana")
//                .expectNext("Strawberry")
//                .verifyComplete();

        // this one does the same thing with above one
        StepVerifier.create(fruitPublisher).expectNext("Apple", "Orange", "Grape", "Banana", "Strawberry");
    }

}
