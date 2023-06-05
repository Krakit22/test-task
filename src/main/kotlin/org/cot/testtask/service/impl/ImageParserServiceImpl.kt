package org.cot.testtask.service.impl

import kotlinx.coroutines.*
import org.cot.testtask.client.ImageClient
import org.cot.testtask.mapper.toFile
import org.cot.testtask.repository.FileRepository
import org.cot.testtask.service.ImageParserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.concurrent.Executors

@Service
class ImageParserServiceImpl(
        private val client: ImageClient,
        private val fileRepository: FileRepository,
        @Value("\${app.parser.thread.number}")
        private val threadNumber: Int
) : ImageParserService {
    private val logger = LoggerFactory.getLogger(javaClass)

    private val threadPool = Executors.newFixedThreadPool(threadNumber).asCoroutineDispatcher()
    private val scope = CoroutineScope(threadPool)
    var infinity = true

    @Scheduled(fixedDelay = 1)
    fun start() {
        runBlocking {
            val jobs = List(threadNumber) {
                scope.launch {
                    do {
                        val file = fileRepository.save(client.getImage().toFile())
                        logger.info("Save file: " + file.id)
                    } while (infinity)
                }
            }
            jobs.joinAll()
        }
    }

}