package org.cot.testtask.repository

import kotlinx.coroutines.runBlocking
import org.cot.testtask.domain.File
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType

class FileRepositoryTest : AbstractTestcontainersTest() {

    @Autowired
    private lateinit var fileRepository: FileRepository

    @Test
    fun testSave() {
        runBlocking {
            val file = fileRepository.save(File(null, "test-url", 10, MediaType.IMAGE_JPEG_VALUE, "test".toByteArray()))
            Assertions.assertNotNull(file)
            Assertions.assertNotNull(file.id)
        }
    }
}