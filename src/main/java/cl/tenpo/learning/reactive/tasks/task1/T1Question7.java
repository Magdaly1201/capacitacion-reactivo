package cl.tenpo.learning.reactive.tasks.task1;


import cl.tenpo.learning.reactive.utils.model.UserAccount;
import cl.tenpo.learning.reactive.utils.service.AccountService;
import cl.tenpo.learning.reactive.utils.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class T1Question7 {

    private final UserService userService;
    private final AccountService accountService;

    public Mono<UserAccount> question7(String userId) {
        return userService.getUserById(userId)
                .doOnSubscribe(sub -> log.info("Iniciando búsqueda de usuario con ID: {}", userId))
                .doOnSuccess(user -> log.info("Usuario encontrado: {}", user))
                .zipWith(accountService.getAccountByUserId(userId)
                        .doOnSubscribe(sub -> log.info("Buscando cuenta del usuario con ID: {}", userId))
                        .doOnSuccess(account -> log.info("Cuenta encontrada: {}", account))
                )
                .map(tuple -> {
                    UserAccount userAccount = new UserAccount(tuple.getT1(), tuple.getT2());
                    log.info("UserAccount generado con éxito: {}", userAccount);
                    return userAccount;
                })
                .doOnError(error -> log.error("Error en generación de UserAccount: {}", error.getMessage()))
                .doOnTerminate(() -> log.info("Proceso completado para userId: {}", userId));
    }
}
