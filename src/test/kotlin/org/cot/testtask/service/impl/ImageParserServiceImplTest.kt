package org.cot.testtask.service.impl

import kotlinx.coroutines.runBlocking
import org.cot.testtask.client.ImageClient
import org.cot.testtask.domain.File
import org.cot.testtask.dto.ImageDto
import org.cot.testtask.repository.FileRepository
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [ImageParserServiceImpl::class])
@ActiveProfiles("test")
class ImageParserServiceImplTest {

    @Autowired
    private lateinit var service: ImageParserServiceImpl

    @MockBean
    private lateinit var client: ImageClient

    @MockBean
    private lateinit var fileRepository: FileRepository

    @Test
    fun start() {
        service.infinity = false
        runBlocking {
            Mockito.`when`(client.getImage())
                    .thenReturn(
                            ImageDto("url",
                                    MediaType.IMAGE_JPEG_VALUE,
                                    "test".toByteArray())
                    )

            Mockito.`when`(fileRepository.save(ArgumentMatchers.any()))
                    .thenReturn(
                            File(1L,
                                    "url",
                                    1,
                                    MediaType.IMAGE_JPEG_VALUE,
                                    "test".toByteArray())
                    )

            service.start()
        }
    }
}