package com.rcelik.siafive.chapterten;

import com.rcelik.siafive.chapterten.model.Player;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@SpringBootTest
class ChapterTenApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public final void crateAFlux_just() {
        // just crating a flux publisher
        Flux<String> fruitPublisher = Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry");

        // to create a flow, need to add subscriber to flux type
        fruitPublisher.subscribe(fruit -> System.out.println("Here is some fruit" + fruit));

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

    @Test
    public final void crateAMono_just() {
        // just crating a Mono publisher
        Mono<String> fruitPublisher = Mono.just("Apple");

        // this means you can only use one element for mono
        fruitPublisher.subscribe(fruit -> System.out.println("Here is some fruit" + fruit));

        // this one does the same thing with above one
        StepVerifier.create(fruitPublisher).expectNext("Apple");
    }

    @Test
    public final void createAFlux_fromArray() {
        String[] fruits = new String[]{"Apple", "Orange", "Grape", "Banana", "Strawberry"};

        Flux<String> fruitFlux = Flux.fromArray(fruits);

        StepVerifier.create(fruitFlux).expectNext("Apple", "Orange", "Grape", "Banana", "Strawberry");
    }

    @Test
    public final void createAFlux_fromIterable() {
        List<String> fruits = new ArrayList<String>();
        fruits.add("Apple");
        fruits.add("Orange");
        fruits.add("Grape");
        fruits.add("Banana");
        fruits.add("Strawberry");

        Flux<String> fruitFlux = Flux.fromIterable(fruits);

        StepVerifier.create(fruitFlux).expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .verifyComplete();
    }

    @Test
    public final void createAFlux_fromStream() {
        Stream<String> fruits = Stream.of("Apple", "Orange", "Grape", "Banana", "Strawberry");
        Flux<String> fruitFlux = Flux.fromStream(fruits);

        StepVerifier.create(fruitFlux).expectNext("Apple")
                .expectNext("Orange")
                .expectNext("Grape")
                .expectNext("Banana")
                .expectNext("Strawberry")
                .verifyComplete();
    }

    @Test
    public final void generateAFlux_range() {
        Flux<Integer> intervalFlux = Flux.range(1, 5);
        StepVerifier.create(intervalFlux).expectNext(1, 2, 3, 4, 5);
    }

    @Test
    public final void generateAFlux_interval() {
        // takes 5 value value in Duration type, in here 1 seconds
        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1)).take(5).log();
        StepVerifier.create(intervalFlux).expectNext(1L, 2L, 3L, 4L, 5L);
    }

    @Test
    public void mergeFluxes() {
        Flux<String> characterFlux = Flux.just("Garfield", "Kojak", "Barbossa")
                .delayElements(Duration.ofMillis(500));
        // in order to emmit new element after 500 milliseconds

        Flux<String> foodFlux = Flux.just("Lasagna", "Lollipops", "Apples")
                .delaySubscription(Duration.ofMillis(250)) // to merge new Flux after 250 milliseconds first is merged
                .delayElements(Duration.ofMillis(500));

        Flux<String> mergedFlux = characterFlux.mergeWith(foodFlux).log();

        StepVerifier.create(mergedFlux).expectNext("Garfield")
                .expectNext("Lasagna")
                .expectNext("Kojak")
                .expectNext("Lollipops")
                .expectNext("Barbossa")
                .expectNext("Apples")
                .verifyComplete();
    }

    @Test
    public final void zipFluxes_creatingNewTupledFlux() {
        Flux<String> characterFlux = Flux.just("Garfield", "Kojak", "Barbossa");
        Flux<String> foodFlux = Flux.just("Lasagna", "Lollipops", "Apples");

        // zipping fluxes in a new flux, that has tuples whose first element is from first flux and second element is from second flux
        Flux<Tuple2<String, String>> zippedFluxes = Flux.zip(characterFlux, foodFlux);

        StepVerifier.create(zippedFluxes)
                .expectNextMatches(predicate ->
                        predicate.getT1().equals("Garfield") && predicate.getT2().equals("Lasagna"))
                .expectNextMatches(predicate ->
                        predicate.getT1().equals("Kojak") && predicate.getT2().equals("Lollipops"))
                .expectNextMatches(predicate ->
                        predicate.getT1().equals("Barbossa") && predicate.getT2().equals("Apples"));

    }

    @Test
    public final void zipFluxes_creatingNewFlux() {
        Flux<String> characterFlux = Flux.just("Garfield", "Kojak", "Barbossa");
        Flux<String> foodFlux = Flux.just("Lasagna", "Lollipops", "Apples");

        // zipping fluxes in a new flux whose elements are the combination of two older Fluxes
        Flux<String> zippedFluxes = Flux.zip(characterFlux, foodFlux, (character, food) -> { return character + " eats " + food; } );

        StepVerifier.create(zippedFluxes)
                .expectNext("Garfield eats Lasagna")
                .expectNext("Kojak eats Lollipops")
                .expectNext("Barbossa eats Apples");
    }

    @Test
    public final void zippingTwoWithDifferentLengthFluxes_creatingNewFlux() {
        Flux<String> characterFlux = Flux.just("Garfield", "Kojak", "Barbossa");
        Flux<String> foodFlux = Flux.just("Lasagna", "Lollipops", "Apples", "peanut");

        // if fluxes length are different then zipped flux is created according to shortest flux size.
        // meaning that to create tuple, one element is required from each fluxes.
        // remaining elements of tallest flux are ignored.
        Flux<String> zippedFluxes = Flux.zip(characterFlux, foodFlux, (character, food) -> { return character + " eats " + food; } );

        StepVerifier.create(zippedFluxes)
                .expectNext("Garfield eats Lasagna")
                .expectNext("Kojak eats Lollipops")
                .expectNext("Barbossa eats Apples");
    }

    @Test
    public void firstFlux() {
        Flux<String> slowFlux = Flux.just("tortoise", "snail", "sloth")
                .delaySubscription(Duration.ofMillis(100)).log();
        Flux<String> fastFlux = Flux.just("hare", "cheetah", "squirrel");

        // it takes the flux whose first element is taken. the finishing of emitting of all elements are not important
        Flux<String> firstFlux = Flux.first(slowFlux, fastFlux).log();

        StepVerifier.create(firstFlux)
                .expectNext("hare")
                .expectNext("cheetah")
                .expectNext("squirrel")
                .verifyComplete();
    }

    @Test
    public void skipAFew() {
        Flux<String> skipFlux = Flux.just("one", "two", "skip a few", "ninety nine", "one hundred")
                .skip(3); // skip first three elements
        StepVerifier.create(skipFlux)
                .expectNext("ninety nine", "one hundred")
                .verifyComplete();
    }

    @Test
    public final void skipForDuration(){
        Flux<String> skipFlux = Flux.just("one", "two", "skip a few", "ninety nine", "one hundred")
                .delayElements(Duration.ofSeconds(1))
                .skip(Duration.ofSeconds(4)).log();

        // in first second one is emitted. then two is emitted.
        StepVerifier.create(skipFlux)
                .expectNext("ninety nine", "one hundred")
                .verifyComplete();
    }

    @Test
    public final void filterWithTakeMethod(){
        Flux<String> nationalParkFlux = Flux.just("Yellowstone", "Yosemite", "Grand Canyon", "Zion", "Grand Teton")
                .take(3).log();

        // in first second one is emitted. then two is emitted.
        StepVerifier.create(nationalParkFlux)
                .expectNext("Yellowstone", "Yosemite", "Grand Canyon")
                .verifyComplete();
    }

    @Test
    public void filterWithTakeMethodByDuration() {
        Flux<String> nationalParkFlux = Flux.just("Yellowstone", "Yosemite", "Grand Canyon", "Zion", "Grand Teton")
                .delayElements(Duration.ofSeconds(1))
                .take(Duration.ofSeconds(4)).log();

        StepVerifier.create(nationalParkFlux)
                .expectNext("Yellowstone", "Yosemite", "Grand Canyon")
                .verifyComplete();
    }

    @Test
    public final void filterMethod(){
        Flux<String> nationalParkFlux = Flux.just("Yellowstone", "Yosemite", "Grand Canyon", "Zion", "Grand Teton")
                .filter(np-> !np.contains(" ")).log();

        StepVerifier.create(nationalParkFlux)
                .expectNext("Yellowstone", "Yosemite", "Zion")
                .verifyComplete();
    }

    @Test
    public final void distinctMethod(){
        Flux<String> animalFlux = Flux.just("dog", "cat", "bird", "dog", "bird", "anteater")
                .distinct().log();

        StepVerifier.create(animalFlux)
                .expectNext("dog", "cat", "bird", "anteater")
                .verifyComplete();
    }

    @Test
    public final void mapMethod() {
        Flux<Player> playersFlux = Flux.just("Michael Jordan", "Scottie Pippen", "Steve Kerr")
                .map(player -> {
                    String[] splitValue = player.split("\\s");
                    Player newPlayer = new Player(splitValue[0], splitValue[1]);
                    System.out.println(newPlayer);

                    return newPlayer;
                }).log();

        StepVerifier.create(playersFlux)
                .expectNext(new Player("Michael", "Jordan"))
                .expectNext(new Player("Scottie", "Pippen"))
                .expectNext(new Player("Steve", "Kerr"))
                .verifyComplete();
    }

    @Test
    public final void flatMapMethod() {
        Flux<Player> playersFlux = Flux.just("Michael Jordan", "Scottie Pippen", "Steve Kerr")
                .flatMap(playerFlux -> Mono.just(playerFlux)
                        .map(player -> {
                            String[] splitValue = player.split("\\s");
                            Player newPlayer = new Player(splitValue[0], splitValue[1]);
                            // System.out.println(newPlayer);

                            return newPlayer;
                        })
                        .subscribeOn(Schedulers.parallel())
                ).log();

        // this list is required since flatMap runs async way so there is no order
        List<Player> playerList = Arrays.asList( new Player("Michael", "Jordan"),
                new Player("Scottie", "Pippen"),
                new Player("Steve", "Kerr"));

        // all players should be included in playerList
        StepVerifier.create(playersFlux)
                .expectNextMatches(player -> playerList.contains(player))
                .expectNextMatches(player -> playerList.contains(player))
                .expectNextMatches(player -> playerList.contains(player))
                .verifyComplete();
    }

    @Test
    public final void bufferMethod() {
        Flux<String> fruitFlux = Flux.just("apple", "orange", "banana", "kiwi", "strawberry");

        Flux<List<String>> bufferedFlux = fruitFlux.buffer(3);

        StepVerifier.create(bufferedFlux)
                .expectNext(Arrays.asList("apple", "orange", "banana"))
                .expectNext(Arrays.asList("kiwi", "strawberry"))
                .verifyComplete();
    }

    @Test
    public final void bufferMethodAsync() {
        Flux<String> fruitFlux = Flux.just("apple", "orange", "banana", "kiwi", "strawberry");

        Flux<String> bufferedFlux = fruitFlux
                .buffer(3)
                .flatMap(thirdlyFruitList -> Flux.fromIterable(thirdlyFruitList)
                        .map(fruitName -> fruitName.toUpperCase())
                        .subscribeOn(Schedulers.parallel())
                        .log()
                );

        List<String> fruitBuffer = Arrays.asList("APPLE", "ORANGE", "BANANA", "KIWI", "STRAWBERRY");

        StepVerifier.create(bufferedFlux)
                .expectNextMatches(fruit -> fruitBuffer.contains(fruit))
                .expectNextMatches(fruit -> fruitBuffer.contains(fruit))
                .expectNextMatches(fruit -> fruitBuffer.contains(fruit))
                .expectNextMatches(fruit -> fruitBuffer.contains(fruit))
                .expectNextMatches(fruit -> fruitBuffer.contains(fruit))
                .verifyComplete();
    }

    @Test
    public final void bufferMethodInAFlux() {
        Flux<String> fruitFlux = Flux.just("apple", "orange", "banana", "kiwi", "strawberry");

        Flux<List<String>> bufferedFlux = fruitFlux.buffer();

        StepVerifier.create(bufferedFlux)
                .expectNext(Arrays.asList("apple", "orange", "banana", "kiwi", "strawberry"))
                .verifyComplete();
    }

    @Test
    public final void collectListMethod() {
        Flux<String> fruitFlux = Flux.just("apple", "orange", "banana", "kiwi", "strawberry");

        Mono<List<String>> bufferedFlux = fruitFlux.collectList();

        StepVerifier.create(bufferedFlux)
                .expectNext(Arrays.asList("apple", "orange", "banana", "kiwi", "strawberry"))
                .verifyComplete();
    }

    @Test
    public final void collectMapMethod() {
        Flux<String> animalFlux = Flux.just("aardvark", "elephant", "koala", "eagle", "kangaroo");

        Mono<Map<Character, String>> animalMapFlux = animalFlux.collectMap(animalName -> animalName.charAt(0));

        StepVerifier.create(animalMapFlux)
                .expectNextMatches(map -> {
                    return map.size() == 3 &&
                            map.get('a') == "aardvark" &&
                            map.get('e') == "eagle" &&
                            map.get('k') == "kangaroo";
                })
                .verifyComplete();
    }

    @Test
    public final void collectMapMethodByUpdatingElement() {
        Flux<String> animalFlux = Flux.just("aardvark", "elephant", "koala", "eagle", "kangaroo");

        Mono<Map<Character, String>> animalMapFlux = animalFlux
                .collectMap(animalName -> animalName.charAt(0), animalName -> animalName.toUpperCase());

        StepVerifier.create(animalMapFlux)
                .expectNextMatches(map -> {
                    return map.size() == 3 &&
                            map.get('a').equals("AARDVARK") &&
                            map.get('e').equals("EAGLE") &&
                            map.get('k').equals("KANGAROO") ;
                })
                .verifyComplete();
    }

    @Test
    public final void allMethod() {
        Flux<String> animalFlux = Flux.just("aardvark", "elephant", "koala", "eagle", "kangaroo");

        Mono<Boolean> hasAMono = animalFlux.all(animalName -> animalName.contains("a"));

        StepVerifier.create(hasAMono).expectNext(Boolean.TRUE).verifyComplete();


        Mono<Boolean> hasKMono = animalFlux.all(animalName -> animalName.contains("k"));

        StepVerifier.create(hasKMono).expectNext(false).verifyComplete();
    }

    @Test
    public final void anyMethod() {
        Flux<String> animalFlux = Flux.just("aardvark", "elephant", "koala", "eagle", "kangaroo");

        Mono<Boolean> hasOMono = animalFlux.any(animalName -> animalName.contains("o"));

        StepVerifier.create(hasOMono).expectNext(Boolean.TRUE).verifyComplete();


        Mono<Boolean> hasZMono = animalFlux.any(animalName -> animalName.contains("z"));

        StepVerifier.create(hasZMono).expectNext(false).verifyComplete();
    }




}
