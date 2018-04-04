package com.study.junit5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@SpringBootTest
class BeforeAllSampleTest(@Value("\${some.key}") val someKey: String) {

    companion object {
        @BeforeAll
        @JvmStatic
        fun beforeClass() {
            System.setProperty("some.key", "some-value")
        }

        @AfterAll
        @JvmStatic
        fun afterClass() {
            System.clearProperty("some.key")
        }
    }

    @Test
    fun testValidateProperties() {
        assertThat(someKey).isEqualTo("some-value")
    }

//    @Configuration
//    class SpringConfig
}