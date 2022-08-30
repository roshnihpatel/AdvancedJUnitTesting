package com.sparta.rp;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledForJreRange;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.JRE;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class SpartanTests {
    //Hooks Block of repeated code -setup / TearDown
    private Spartan spartan;
    @BeforeAll
    static void initAll(TestInfo testInfo){
        System.out.println(testInfo.getTestClass() + " has started");
    }

    @BeforeEach
    void init(TestInfo testInfo){
        spartan = new Spartan(11, "Roshni", "Java", LocalDate.of(2022, 12, 14));
        System.out.println(testInfo.getDisplayName() + " executing");
    }
    @AfterEach
    void tearDown(TestInfo testInfo){
        System.out.println(testInfo.getDisplayName() + " completed");
    }

    @AfterAll
    static  void tearDownAll(TestInfo testInfo){
        System.out.println(testInfo.getTestClass() + " has finished");
    }

    @Test
    //@Disabled("Waiting for Roshni to finish method")
    //@DisabledForJreRange(min = JRE.JAVA_11, max = JRE.JAVA_14)
    @DisabledIf(value = "checkForSpartan", disabledReason = "code has not been completed yet")
    @DisplayName("Check that Spartan is called Roshni")
    void checkThatTheSpartanIsCalledRoshni() {

        Assertions.assertEquals("Roshni", spartan.getName());
    }
    boolean checkForSpartan(){
        return true;
    }

    @Test
    @DisplayName("check if id is greater than 10")
    void checkIfIdIsGreaterThan10() {
        Assertions.assertTrue(spartan.getId() > 10);
    }

    @Test
    @DisplayName("check if id is less than 10000")
    void checkIfIdIsLessThan10000() {
        Assertions.assertTrue(spartan.getId() < 10000);
    }

    @Tag("valueCheck")
    @Test
    @RepeatedTest(value = 6, name = "{displayName} is running {currentRepetition}")
    @DisplayName("Check that the id is a positive number")
    void checkThatTheIdIsAPositiveNumber(){
        Assertions.assertTrue(spartan.getId() >= 0);
    }
    @Tag("valueCheck")
    @Test
    @DisplayName("Check that the id is greater than 10")
    void checkThatTheIdIsGreaterThan10(){
        Assumptions.assumeTrue(spartan.getId() > 0); //test will get ignored if assumption fails
        Assertions.assertTrue(spartan.getId() >= 10);
    }
    @Tag("dateCheck")
    @Test
    @DisplayName("Check that start date is not in the past")
    void checkThatStartDateIsNotInThePast() {
        Assumptions.assumeTrue(spartan.getStartDate() != null);
        Assertions.assertTrue(spartan.getStartDate().isAfter(LocalDate.now()));

    }

    @Nested
    @Tag("nameCheck")
    class NameTests {
        @BeforeEach
        void init(TestInfo testInfo) {
            System.out.println("Nested class:"  + testInfo.getTestMethod());
        }

        @Test
        @DisplayName("check that name contains only letters")
        void checkThatNameContainsOnlyLetters() {
            Assumptions.assumeTrue(spartan.getName() != null);
            Assumptions.assumeTrue(spartan.getName().length() != 0);
            Assertions.assertTrue(Pattern.matches("[A-Za-z]+", spartan.getName()));
        }

        @Test
        @DisplayName("check that name starts with a capital letter")
        void checkThatNameStartsWithACapitalLetter() {
            Assertions.assertTrue(Pattern.matches("[A-Z][a-z]+", spartan.getName()));
        }

    }


    @Nested
    @DisplayName("Nested Tests")
    class NestedTest {
        void nestedExamples(){
            Assertions.assertEquals(1,1);
        }
    }



}
