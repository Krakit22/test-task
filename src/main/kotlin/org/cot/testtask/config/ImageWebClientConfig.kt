package org.cot.testtask.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.unit.DataSize
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class ImageWebClientConfig(
        @Value("\${app.image.base.url}")
        private val imageUrl: String,
        @Value("\${app.image.max.size}")
        private val imageMaxSize: DataSize
) {
    @Bean
    fun imageWebClient(): WebClient {
        return WebClient
                .builder()
                .baseUrl(imageUrl)
                .codecs{ codecs -> codecs
                        .defaultCodecs()
                        .maxInMemorySize(imageMaxSize.toBytes().toInt()) }
                .build()
    }
}