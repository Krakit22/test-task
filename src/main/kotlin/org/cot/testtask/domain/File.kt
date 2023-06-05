package org.cot.testtask.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "files")
data class File(
        @Id var id: Long?,
        var url: String,
        var size: Int,
        var contentType: String?,
        var content: ByteArray?
)
