package cl.tenpo.learning.reactive.tasks.task1;

import cl.tenpo.learning.reactive.utils.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class T1Question1 {

    private final UserService userService;

    public Mono<Integer> question1A() {
        return Mono.just(1337); // TODO: REEMPLAZAR POR RESPUESTA
    }

    public Mono<String> question1B() {
        return Mono.just("string"); // TODO: REEMPLAZAR POR RESPUESTA
    }

    public Mono<String> question1C(String name) {
        return Mono.just("string"); //TODO: REEMPLAZAR POR RESPUESTA
    }

}
