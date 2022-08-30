package com.sparta.rp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class ParameterizedTests {

    @ParameterizedTest(name = "[{index}] of {displayName} :  {argumentsWithNames}")
    @ValueSource(ints = {11,20,30,51,71,15})
    @DisplayName("Check for int higher than 10")
    void checkIntHigherThan10(int num){
        Assertions.assertTrue(num >10);
    }
    @ParameterizedTest
    //@CsvSource({"Roshni", "Hanna", "Lizzie", "Lara"})
    @NullAndEmptySource
    @CsvFileSource(resources = "/names.csv")
    @DisplayName("Using cvs source for test")
    void runCSVTests(String name){
        Assertions.assertTrue(name.length() > 3);
    }

    @ParameterizedTest
    @MethodSource("sourceMethod")
    @DisplayName("Using a method source")
    void usingAMethodSource(int number, String name) {
        Assertions.assertEquals(name.length(), number);

    }

    public static Stream<Arguments> sourceMethod() {
        return Stream.of(
                Arguments.of(6,"Roshni"),
                Arguments.of(5, "Hanna"),
                Arguments.of(6, "Lizzie"),
                Arguments.of(4, "Lara")
        );
    }
    @ParameterizedTest
    @ValueSource(ints = {1,3,5,13,7,8,9,})
    @DisplayName("testing for exceptions")
    void testingForException(int number){
        ArrayList<Integer> numberList = new ArrayList<>(Arrays.asList(1,2,3));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> numberList.get(number));
    }



}
