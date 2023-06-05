package org.cot.testtask.dto

data class ImageDto(
        var url: String,
        var contentType: String?,
        var content: ByteArray
)