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
        return countryService.findAllCountries()
                .doOnSubscribe(sub -> log.info("Buscando países..."))
                .doOnNext(country -> log.info("País encontrado: {}", country))
                .distinct()
                .take(5)
                .doOnComplete(() -> log.info("Se han obtenido los primeros 5 países distintos."));
    }

    public Flux<String> question2B() {
        return countryService.findAllCountries()
                .doOnSubscribe(sub -> log.info("Buscando países hasta encontrar Argentina..."))
                .doOnNext(country -> log.info("País recibido: {}", country))
                .takeUntil(country -> country.equals("Argentina"))
                .doOnComplete(() -> log.info("Se ha encontrado Argentina, flujo completado."));
    }

    public Flux<String> question2C() {
        return countryService.findAllCountries()
                .doOnSubscribe(sub -> log.info("Buscando países hasta encontrar Francia..."))
                .doOnNext(country -> log.info("País procesado: {}", country))
                .takeWhile(name -> !name.equalsIgnoreCase("France"))
                .doOnComplete(() -> log.info("Se ha encontrado Francia, deteniendo el flujo."));
    }

}
