package org.cot.testtask.mapper

import org.cot.testtask.domain.File
import org.cot.testtask.dto.ImageDto

fun ImageDto.toFile() = File(
        id = null,
        url = url,
        size = content.size,
        contentType = contentType,
        content = content
)