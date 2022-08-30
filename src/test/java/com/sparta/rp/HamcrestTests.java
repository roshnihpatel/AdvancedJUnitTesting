package com.sparta.rp;

import org.hamcrest.MatcherAssert;
import  static org.hamcrest.MatcherAssert.*;

import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class HamcrestTests {
    private Spartan spartan;

    @BeforeEach
    void setup() {
        spartan = new Spartan(1, "Roshni", "Java", LocalDate.of(2002, 7,15));
    }

    @Test
    @DisplayName("Check spartan name is Roshni")
    void checkSpartanIsCalledRoshni() {
        assertThat(spartan.getName(), equalTo("Roshni"));
    }
    @Test
    @DisplayName("Check spartan has an id field")
    void checkSpartanHasAnId() {
       assertThat(spartan,hasProperty("id", equalTo(1)));
    }

}
