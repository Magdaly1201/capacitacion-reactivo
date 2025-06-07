package cl.tenpo.learning.reactive.tasks.task1;

import cl.tenpo.learning.reactive.utils.service.CalculatorService;
import cl.tenpo.learning.reactive.utils.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class T1Question5 {

    private final CalculatorService calculatorService;
    private final UserService userService;

    public Mono<String> question5A() {
        return Flux.range(100, 901)
                .doOnSubscribe(sub -> log.info("Iniciando procesamiento de números del 100 al 1000..."))
                .map(BigDecimal::valueOf)
                .concatMap(num -> calculatorService.calculate(num)
                        .doOnNext(result -> log.info("Resultado calculado para {}: {}", num, result))
                        .onErrorResume(e -> {
                            log.error("Error en cálculo para {}: {}", num, e.getMessage());
                            return Mono.just(BigDecimal.valueOf(-1));
                        })
                )
                .filter(result -> result.equals(BigDecimal.valueOf(-1)))
                .hasElements()
                .flatMap(hasError -> {
                    if (hasError) {
                        log.warn("Se detectaron errores en los cálculos, retornando 'Chuck Norris'");
                        return Mono.just("Chuck Norris");
                    } else {
                        log.info("Todos los cálculos fueron exitosos, obteniendo nombre...");
                        return userService.findFirstName()
                                .doOnSuccess(name -> log.info("Nombre obtenido: {}", name));
                    }
                })
                .doOnSuccess(finalResult -> log.info("Proceso finalizado con resultado: {}", finalResult));
    }

    public Flux<String> question5B() {
        return Flux.range(100, 901)
                .doOnSubscribe(sub -> log.info("Procesando cálculos desde el 100 al 1000..."))
                .map(BigDecimal::valueOf)
                .concatMap(num -> calculatorService.calculate(num)
                        .doOnNext(result -> log.info("Resultado calculado para {}: {}", num, result))
                        .onErrorResume(e -> {
                            log.error("Error en cálculo para {}: {}", num, e.getMessage());
                            return Mono.just(BigDecimal.valueOf(-1));
                        })
                )
                .filter(result -> result.equals(BigDecimal.valueOf(-1)))
                .hasElements()
                .flatMapMany(hasError -> {
                    if (hasError) {
                        log.warn("Se detectaron errores en los cálculos, flujo vacío retornado.");
                        return Flux.empty();
                    } else {
                        log.info("Todos los cálculos fueron exitosos, obteniendo los primeros 3 nombres...");
                        return userService.findAllNames()
                                .take(3)
                                .doOnNext(name -> log.info("Nombre obtenido: {}", name));
                    }
                })
                .doOnComplete(() -> log.info("Proceso completado."));
    }

}
