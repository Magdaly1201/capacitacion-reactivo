package cl.tenpo.learning.reactive.tasks.task1;

import cl.tenpo.learning.reactive.utils.service.CalculatorService;
import cl.tenpo.learning.reactive.utils.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class T1Question5 {

    private final CalculatorService calculatorService;
    private final UserService userService;

    public Mono<String> question5A() {
        return Mono.just("A"); // TODO: REEMPLAZAR POR RESPUESTA
    }

    public Flux<String> question5B() {
        return Flux.just("A"); // TODO: REEMPLAZAR POR RESPUESTA
    }

}
