package com.github.fabriciolfj.resilience.entrypoint;

import com.github.fabriciolfj.resilience.service.PersonService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CircuitBreakerSimulacao {

    public static void main(String[] args) throws Exception {
        var service = new PersonService();
        var circuitBreaker = CircuitBreaker.ofDefaults("problemas");

        var result = CircuitBreaker.
                decorateCallable(circuitBreaker, service::execute);


        log.info("Resultado: {}", result.call());

    }
}
