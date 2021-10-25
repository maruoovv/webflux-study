package com.example.webflux.infrastructure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider


@Configuration
class TestConfiguration {

    @Bean
    fun webClient() : WebClient {
        return WebClient.builder()
                .baseUrl("http://localhost:8090")
                .clientConnector(ReactorClientHttpConnector(HttpClient.create(ConnectionProvider.builder("local")
                        .build())))
                .build()
    }

}