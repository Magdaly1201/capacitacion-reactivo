package cl.tenpo.learning.reactive.tasks.task1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Component
@RequiredArgsConstructor
@Slf4j
public class T1Question6 {

    public ConnectableFlux<Double> question6() {
        return Flux.interval(Duration.ofMillis(500))
                .doOnSubscribe(sub -> log.info("Iniciando emisión de precios en tiempo real..."))
                .map(i -> {
                    double price = 1 + Math.random() * 499;
                    log.info("Nuevo precio generado: {}", price);
                    return price;
                })
                .publish(); // Mantener publish() para que retorne un ConnectableFlux
    }
}