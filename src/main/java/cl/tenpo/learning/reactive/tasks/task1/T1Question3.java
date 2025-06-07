package cl.tenpo.learning.reactive.tasks.task1;

import cl.tenpo.learning.reactive.utils.model.Page;
import cl.tenpo.learning.reactive.utils.service.CountryService;
import cl.tenpo.learning.reactive.utils.service.TranslatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class T1Question3 {

    private final CountryService countryService;
    private final TranslatorService translatorService;

    public Flux<String> question3A(Page<String> page) {
        return Mono.justOrEmpty(page)
                .doOnSubscribe(sub -> log.info("Procesando página de elementos..."))
                .flatMapMany(p -> Flux.fromIterable(p.items()))
                .doOnNext(item -> log.info("Elemento procesado: {}", item))
                .doOnComplete(() -> log.info("Procesamiento de página completado."));
    }

    public Flux<String> question3B(String country) {
        return countryService.findCurrenciesByCountry(country)
                .doOnSubscribe(sub -> log.info("Buscando monedas para el país: {}", country))
                .doOnNext(currency -> log.info("Moneda encontrada: {}", currency))
                .doOnComplete(() -> log.info("Búsqueda de monedas completada."));
    }

    public Flux<String> question3C() {
        return countryService.findAllCountries()
                .doOnSubscribe(sub -> log.info("Buscando países y traduciendo los primeros 3..."))
                .take(3)
                .flatMap(country -> Mono.justOrEmpty(translatorService.translate(country))
                        .doOnNext(translated -> log.info("País traducido: {}", translated))
                        .doOnError(error -> log.error("Error al traducir país: {}", country, error)))
                .doOnComplete(() -> log.info("Traducción de países completada."));
    }

}
