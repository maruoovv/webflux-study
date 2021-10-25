package com.example.webflux.domain

import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler
import reactor.core.scheduler.Schedulers
import java.time.LocalDateTime

@Service
class TestService(
        private val webClient: WebClient
) {
    private val logger = KotlinLogging.logger { }
    val scheduler = Schedulers.immediate()

    fun httpGet(): Mono<String> {
        return webClient
                .get()
                .uri { it.path("/test").build() }
                .retrieve()
                .bodyToMono(String::class.java)
                .publishOn(scheduler)
                .subscribeOn(scheduler)
                .doOnSuccess {
                    logger.info { "result $it" }
                }
                .doOnError { logger.error { "error $it" } }
                .onErrorReturn("ERROR")
    }

}