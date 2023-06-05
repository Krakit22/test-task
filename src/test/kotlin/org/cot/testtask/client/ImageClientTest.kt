package org.cot.testtask.client

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.Options
import kotlinx.coroutines.runBlocking
import org.cot.testtask.config.ImageWebClientConfig
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [ImageWebClientConfig::class, ImageClient::class])
@ActiveProfiles("test")
class ImageClientTest {

    protected val server: WireMockServer = WireMockServer(Options.DEFAULT_PORT)

    @Autowired
    private lateinit var client: ImageClient

    @Test
    fun getImageTest() {
        val testUrl = "/test-url"
        server.start()
        server.stubFor(WireMock.any(WireMock.urlEqualTo(testUrl))
                .willReturn(WireMock.aResponse()
                        .withBody("test".toByteArray())
                )
        )
        server.stubFor(WireMock.any(WireMock.urlPathMatching("/[0-9]*/[0-9]*"))
                .willReturn(WireMock.aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                        .withHeader(HttpHeaders.LOCATION, testUrl)
                        .withBody("test".toByteArray())
                )
        )

        runBlocking {
            val response = client.getImage()
            Assertions.assertNotNull(response)
            Assertions.assertEquals(testUrl, response.url)
            Assertions.assertNotNull(response.content)
            Assertions.assertEquals(MediaType.IMAGE_JPEG_VALUE, response.contentType)
        }
    }
}