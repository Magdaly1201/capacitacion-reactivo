package cl.tenpo.learning.reactive.tasks.task1;

import cl.tenpo.learning.reactive.utils.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
public class T1Question2 {

    private final CountryService countryService;

    public Flux<String> question2A() {
        return Flux.just("Venezuela"); // TODO: REEMPLAZAR POR RESPUESTA
    }

    public Flux<String> question2B() {
        return Flux.just("Venezuela"); // TODO: REEMPLAZAR POR RESPUESTA
    }

    public Flux<String> question2C() {
        return Flux.just("Venezuela"); // TODO: REEMPLAZAR POR RESPUESTA
    }

}
