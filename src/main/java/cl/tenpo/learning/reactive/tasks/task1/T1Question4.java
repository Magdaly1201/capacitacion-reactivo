package cl.tenpo.learning.reactive.tasks.task1;

import cl.tenpo.learning.reactive.utils.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class T1Question4 {

    private final CountryService countryService;

    public Flux<String> question4A() {
        return countryService.findAllCountries()
                .doOnSubscribe(sub -> log.info("Obteniendo lista de países..."))
                .take(200)
                .collectList()
                .doOnNext(countries -> {
                    Map<String, Long> countryCount = countries.stream()
                            .collect(Collectors.groupingBy(country -> country, Collectors.counting()));
                    log.info("Repeticiones por país: {}", countryCount);
                })
                .flatMapMany(countries -> Flux.fromIterable(
                        countries.stream()
                                .distinct()
                                .sorted()
                                .collect(Collectors.toList())
                ))
                .doOnComplete(() -> log.info("Flujo de países completado."));
    }

}
