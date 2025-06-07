package cl.tenpo.learning.reactive.tasks.task1;

import cl.tenpo.learning.reactive.utils.exception.AuthorizationTimeoutException;
import cl.tenpo.learning.reactive.utils.exception.PaymentProcessingException;
import cl.tenpo.learning.reactive.utils.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
@RequiredArgsConstructor
@Slf4j
public class T1Question8 {

    private final TransactionService transactionService;

    public Mono<String> question8(int transactionId) {
        return transactionService.authorizeTransaction(transactionId)
                .doOnSubscribe(sub -> log.info("Intentando autorizar transacción {}", transactionId))
                .doOnSuccess(response -> log.info("Autorización recibida: {}", response))
                .doOnError(error -> log.error("Error en autorización: {}", error.getMessage()))
                .timeout(Duration.ofSeconds(3), Mono.error(new AuthorizationTimeoutException("Timeout excedido")))
                .retryWhen(Retry.fixedDelay(3, Duration.ofMillis(500))
                        .filter(e -> !(e instanceof AuthorizationTimeoutException))
                        .doBeforeRetry(retrySignal -> log.warn("Reintentando autorización ({} intento)", retrySignal.totalRetries())))
                .onErrorMap(e -> e instanceof AuthorizationTimeoutException ? e : new PaymentProcessingException("Error en pago", e))
                .doOnTerminate(() -> log.info("Proceso de autorización completado para transacción {}", transactionId));
    }
}
