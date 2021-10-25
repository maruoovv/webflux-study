package com.example.webflux.interfaces

import com.example.webflux.domain.TestService
import mu.KotlinLogging
import org.slf4j.MDC
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.util.*

@RestController
@RequestMapping("/test")
class TestController(
        private val testService: TestService
) {
    private val logger = KotlinLogging.logger {  }

    @GetMapping
    fun httpGet(): Mono<String> {
        MDC.put("thread", "${Thread.currentThread().name} + ${LocalDateTime.now()}")
        return testService.httpGet()
    }

}