package cl.tenpo.learning.reactive.tasks.task1;

import cl.tenpo.learning.reactive.utils.exception.ResourceNotFoundException;
import cl.tenpo.learning.reactive.utils.exception.UserServiceException;
import cl.tenpo.learning.reactive.utils.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class T1Question1 {

    private final UserService userService;

    public Mono<Integer> question1A() {
        return userService.findFirstName()
                .doOnSubscribe(sub -> log.info("Buscando el primer nombre..."))
                .filter(name -> name.startsWith("A"))
                .doOnNext(name -> log.info("Nombre encontrado: {}", name))
                .map(String::length)
                .switchIfEmpty(Mono.just(-1).doOnNext(v -> log.warn("No se encontró ningún nombre que comience con 'A'")))
                .doOnSuccess(result -> log.info("Finalizado con resultado: {}", result));
    }


    public Mono<String> question1B() {
        return userService.findFirstName()
                .doOnSubscribe(sub -> log.info("Obteniendo primer nombre..."))
                .flatMap(name -> userService.existByName(name)
                        .doOnNext(exists -> log.info("¿Existe el usuario? {}", exists))
                        .flatMap(exists -> exists
                                ? userService.update(name).doOnSuccess(updated -> log.info("Usuario actualizado: {}", updated))
                                : userService.insert(name).doOnSuccess(inserted -> log.info("Usuario insertado: {}", inserted))
                        ))
                .doOnSuccess(finalResult -> log.info("Finalizado con resultado: {}", finalResult));
    }

    public Mono<String> question1C(String name) {
        return userService.findFirstByName(name)
                .doOnSubscribe(sub -> log.info("Buscando usuario con nombre: {}", name))
                .doOnSuccess(user -> log.info("Usuario encontrado: {}", user))
                .onErrorMap(ex -> {
                    log.error("Error al buscar usuario: {}", name, ex);
                    return new UserServiceException();
                })
                .switchIfEmpty(Mono.error(new ResourceNotFoundException()))
                .doOnError(ex -> log.warn("Usuario no encontrado: {}", name));
    }

}