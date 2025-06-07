package cl.tenpo.learning.reactive.tasks.task1;

import cl.tenpo.learning.reactive.utils.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class T1Question8 {

    private final TransactionService transactionService;

    public Mono<String> question8(int transactionId) {
        return null; //TODO: REEMPLAZAR POR RESPUESTA
    }
}
