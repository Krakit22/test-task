package org.cot.testtask.client

import org.cot.testtask.dto.ImageDto
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBodilessEntity
import org.springframework.web.reactive.function.client.awaitBody
import kotlin.random.Random
import kotlin.random.nextInt

@Component
class ImageClient(
        private val client: WebClient
) {
    companion object {
        private val intRange = IntRange(10, 5000)
    }

    suspend fun getImage(): ImageDto {
        val firstResponse = client
                .get()
                .uri("/".plus(Random.nextInt(intRange)).plus("/").plus(Random.nextInt(intRange)))
                .retrieve()
                .awaitBodilessEntity()
        val location = firstResponse.headers[HttpHeaders.LOCATION]!![0]
        val secondResponse = getRedirect(location)
        return ImageDto(
                location,
                firstResponse.headers[HttpHeaders.CONTENT_TYPE]?.first(),
                secondResponse
        )
    }

    private suspend fun getRedirect(location: String): ByteArray {
        return client
                .get()
                .uri(location)
                .retrieve()
                .awaitBody()
    }
}