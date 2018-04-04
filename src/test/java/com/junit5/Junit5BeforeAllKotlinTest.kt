package com.junit5

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

/**
 *
 * http://www.java-allandsundry.com/2018/03/kotlin-and-junit-5-beforeall.html
 *
 * Problem:
 * Exception in thread "main" java.lang.NoSuchMethodError:
 *  org.junit.platform.launcher.Launcher
 *
 * Solution:
 * Upgrade IDEA from 2017.1 to 2017.3+
 *
 * -------------------------------------
 *
 * Problem:
 * SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
 * SLF4J: Defaulting to no-operation (NOP) logger implementation
 * SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
 *
 * Solution:
 * pom.xml
 * <dependency>
 *     <groupId>org.slf4j</groupId>
 *     <artifactId>slf4j-simple</artifactId>
 *     <version>{org.slf4j.version}</version>
 * </dependency>
 *
 *
 */
class Junit5BeforeAllKotlinTest {

    @Test
    fun aTest1() {
        LOGGER.info("aTest1 called")
        LOGGER.info(this.toString())
    }

    @Test
    fun aTest2() {
        LOGGER.info("aTest2 called")
        LOGGER.info(this.toString())
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(Junit5BeforeAllTest::class.java)


        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            LOGGER.info("beforeAll called")
        }

        @AfterAll
        @JvmStatic
        internal fun afterAll() {
            LOGGER.info("afterAll called")
        }
    }
}