# JUnit 5 - An Early Test Drive - Part 1

###### Reference

[https://www.infoq.com/articles/JUnit-5-Early-Test-Drive]()

## Improvements

### Visibility

The most obvious change is that test methods no longer must be public. Package visibility suffices (but private does not), so we can keep our test classes free from the clutter of lots of public keywords.

Theoretically test classes can have default visibility as well. But because of the simple setup we just did, our tools will only scan public classes for annotations. This will change once JUnit 5 support comes around.

### Test Lifecycle
#### @Test
The most basic JUnit 4 annotation is @Test, used to mark methods that are to be run as tests.

The annotation is virtually unchanged, although it no longer takes optional arguments; expected exceptions can now be verified via assertions. (For timeouts there is not yet a replacement.)

#### Before And After
To run code to set up and tear down our tests we can use @BeforeAll, @BeforeEach, @AfterEach, and @AfterAll. They are more appropriately named but semantically identical to JUnit 4’s @BeforeClass, @Before, @After, and @AfterClass.

Because a new instance is created for each test, and @BeforeAll / @AfterAll are only called once for all of them, it is not clear on which instance they should be invoked so they have to be static (as was the case with @BeforeClass and @AfterClass in JUnit 4).

If different methods are annotated with the same annotation, the execution order is deliberately undefined.

#### Disabling Tests
Tests can simply be disabled with @Disabled, which is equivalent to JUnit 4's @Ignored. This is just a special case of a Condition, which we will see later when we talk about extending JUnit.

#### Assertions
After everything was set up and executed, it is ultimately up to assertions to verify the desired behavior. There have been a number of incremental improvements in that area as well:

* Assertion messages are now last on the parameter list. This makes calls with and without messages more uniform as the first two parameters are always the expected and actual value, and the optional argument comes last.
* Using lambdas, assert messages can be created lazily, which can improve performance if creation is a lengthy operation.
* Boolean assertions accept predicates.

Then there is the new `assertAll`, which checks a group of related invocation results and, if the assertion fails, does not short-circuit but prints values for all of them:

```
@Test
void assertRelatedProperties() {
    Developer dev = new Developer("Johannes", "Link");

    assertAll("developer",
   		 () -> assertEquals("Marc", dev.firstName()),
   		 () -> assertEquals("Philipp", dev.lastName())
    );
}
```

This yields the following failure message:

```
org.opentest4j.MultipleFailuresError: developer (2 failures)
    expected: <Marc> but was: <Johannes>
    expected: <Philipp> but was: <Link>
```

Note how the printout includes the last name failure even after the first names had already failed the assertion.

Finally we have `assertThrows` and `expectThrows`, both of which fail the test if the specified exception is not thrown by the called method. But to further assert properties of the exception (e.g. that the message contains certain information), `expectThrows` returns it.

```
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
```

#### Assumptions

Assumptions make it possible to run tests if certain conditions are as expected. An assumption must be phrased as a boolean expression, and if the condition is not met the test exits. This can be used to reduce the run time and verbosity of test suites, especially in the failure case.

```
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
```

Assumptions can either be used to abort tests whose preconditions are not met (`assumeTrue` and `assumeFalse`) or to execute specific parts of a test when a condition holds (`assumimgThat`). The main difference is that aborted tests are reported as disabled, whereas a test that was empty because a condition did not hold shows green.
