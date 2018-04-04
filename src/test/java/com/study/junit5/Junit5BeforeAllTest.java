package com.study.junit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: H13995 魏喆
 * @Description: 
 * @Date: Created in 上午8:34 18/3/30
 * @Modified: by 
 */
//@RunWith(JUnitPlatform.class)
public class Junit5BeforeAllTest {

        private static final Logger LOGGER = LoggerFactory.getLogger(Junit5BeforeAllTest.class);

        @BeforeAll
        static void beforeAll() {
            LOGGER.info("beforeAll called");
        }

        @Test
        public void aTest1() {
            LOGGER.info("aTest1 called");
            LOGGER.info(this.toString());
        }

        @Test
        public void aTest2() {
            LOGGER.info("aTest2 called");
            LOGGER.info(this.toString());
        }

        @AfterAll
        static void afterAll() {
            LOGGER.info("afterAll called");
        }
}
