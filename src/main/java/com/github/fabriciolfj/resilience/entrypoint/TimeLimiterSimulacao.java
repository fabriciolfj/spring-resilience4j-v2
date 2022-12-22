package com.github.fabriciolfj.resilience.entrypoint;

import com.github.fabriciolfj.resilience.service.PersonService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.timelimiter.TimeLimiter;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Slf4j
public class TimeLimiterSimulacao {

    public static void main(String[] args) throws Exception {
        var service = new PersonService();

        TimeLimiter timeLimiter = TimeLimiter.of(Duration.ofSeconds(10));

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(80);

        var result = timeLimiter.executeCompletionStage(scheduledExecutorService, () -> CompletableFuture.supplyAsync(() -> service.executeTime()));

        result.whenComplete((r, e) -> {
            if (e != null) {
                log.error("Falha {}", e.getMessage());
                return;
            }

            log.info("Total persons: {}", r.size());
        });
    }
}
