package org.cot.testtask

import org.cot.testtask.service.impl.ImageParserServiceImpl
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
@MockBean(ImageParserServiceImpl::class)
class TestTaskApplicationTests {

    @Test
    fun contextLoads() {
    }

}
