package cl.tenpo.learning.reactive.tasks.task1;

import cl.tenpo.learning.reactive.utils.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
public class T1Question4 {

    private final CountryService countryService;

    public Flux<String> question4A() {
        return Flux.just("countries"); // TODO: REEMPLAZAR POR RESPUESTA
    }

}
