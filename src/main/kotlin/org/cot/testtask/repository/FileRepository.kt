package org.cot.testtask.repository

import org.cot.testtask.domain.File
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface FileRepository : CoroutineCrudRepository<File, Long>