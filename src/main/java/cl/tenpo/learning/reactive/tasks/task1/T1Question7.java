package cl.tenpo.learning.reactive.tasks.task1;


import cl.tenpo.learning.reactive.utils.model.UserAccount;
import cl.tenpo.learning.reactive.utils.service.AccountService;
import cl.tenpo.learning.reactive.utils.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class T1Question7 {

    private final UserService userService;
    private final AccountService accountService;

    public Mono<UserAccount> question7(String userId) {
        return null; //TODO: REEMPLAZAR POR RESPUESTA
    }
}
