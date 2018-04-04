package com.junit5;

import com.junit5.model.Developer;
import com.junit5.sut.UnitUnderTest;
import org.junit.jupiter.api.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.junit.gen5.api.Assertions.assertEquals;
import static org.junit.gen5.api.Assertions.expectThrows;
import static org.junit.gen5.api.Assumptions.assumeFalse;
import static org.junit.gen5.api.Assumptions.assumeTrue;
import static org.junit.gen5.api.Assumptions.assumingThat;
import static org.junit.jupiter.api.Assertions.*;


/**
 * https://www.infoq.com/articles/JUnit-5-Early-Test-Drive
 * https://www.infoq.com/articles/JUnit-5-Early-Test-Drive-Part-2
 *
 */
@RunWith(JUnitPlatform.class)
@DisplayName("A special test case")
public class JUnit5TestA {

    UnitUnderTest unitUnderTest = new UnitUnderTest();

    @BeforeAll
    static void initializeExternalResources() {
   	 System.out.println("Initializing external resources...");
    }

    @BeforeEach
    void initializeMockObjects() {
   	 System.out.println("Initializing mock objects...");
    }

    @Test
    @DisplayName("╯°□°）╯")
    void someTest() {
   	 System.out.println("Running some test...");
   	 assertTrue(true);
    }

    @Test
    @DisplayName("😱")
    void otherTest() {
   	 assumeTrue(true);

   	 System.out.println("Running another test...");
   	 assertNotEquals(1, 42, "Why wouldn't these be the same?");
    }


    /************************/
    /** Disabling Tests */
    /************************/
    @Test
    @Disabled
    void disabledTest() {
   	 System.exit(1);
    }


    /************************/
    /** Assertions */
    /************************/
    @Test
    void assertRelatedProperties() {
        Developer dev = new Developer("Johannes", "Link");
        // org.junit.gen5.api
        assertAll("developer",
                () -> assertEquals("Marc", dev.firstName()),
                () -> assertEquals("Philipp", dev.lastName())
        );
    }

    @Test
    void assertExceptions() {
        // assert that the method under test
        // throws the expected exception */
        assertThrows(Exception.class, unitUnderTest::methodUnderTest);

        Exception exception = expectThrows(
                Exception.class,
                unitUnderTest::methodUnderTest);
        assertEquals("This shouldn't happen.", exception.getMessage());
    }

    /************************/
    /** Assumptions */
    /************************/

    @Test
    void exitIfFalseIsTrue() {
        assumeTrue(false);
        System.exit(1);
    }

    @Test
    void exitIfTrueIsFalse() {
        assumeFalse(this::truism);
        System.exit(1);
    }

    private boolean truism() {
        return true;
    }

    @Test
    void exitIfNullEqualsString() {
        assumingThat(
                // state an assumption (a false one in this case) ...
                "null".equals(null),
                // … and only execute the lambda if it is true
                () -> System.exit(1)
        );
    }

    /************************/
    /** Call Of The Lambda? */
    /************************/
    @Test
    void callOfTheLambda() {
//        test("someTest", () -> {
//            System.out.println("Running some test...");
//            assertTrue(true);
//        });

        System.out.println("Running some test...");
        assertTrue(true);
    }





    @AfterEach
    void tearDown() {
   	 System.out.println("Tearing down...");
    }

    @AfterAll
    static void freeExternalResources() {
   	 System.out.println("Freeing external resources...");
    }

}