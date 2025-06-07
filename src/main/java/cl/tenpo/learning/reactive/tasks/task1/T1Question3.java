package cl.tenpo.learning.reactive.tasks.task1;

import cl.tenpo.learning.reactive.utils.model.Page;
import cl.tenpo.learning.reactive.utils.service.CountryService;
import cl.tenpo.learning.reactive.utils.service.TranslatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
public class T1Question3 {

    private final CountryService countryService;
    private final TranslatorService translatorService;

    public Flux<String> question3A(Page<String> page) {
        return Flux.just("page element"); // TODO: REEMPLAZAR POR RESPUESTA
    }

    public Flux<String> question3B(String country) {
        return Flux.just("money"); // TODO: REEMPLAZAR POR RESPUESTA
    }

    public Flux<String> question3C() {
        return Flux.just("traduccion"); // TODO: REEMPLAZAR POR RESPUESTA
    }

}
