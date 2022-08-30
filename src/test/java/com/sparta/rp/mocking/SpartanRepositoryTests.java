package com.sparta.rp.mocking;
//don't mock the java api classes, and only mock what you need
import com.sparta.rp.Spartan;
import com.sparta.rp.SpartanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

public class SpartanRepositoryTests {
    private Spartan mockSpartan;
    private Spartan spartan;
    private Spartan spySpartan;

    @BeforeEach
    void init() {
        mockSpartan = Mockito.mock(Spartan.class);
        spartan = new Spartan(2, "Lizzie", "Java", LocalDate.now());
        spySpartan = Mockito.spy(spartan); //partial mock

    }

    @Test
    @DisplayName("Testing Spartan Repository Print Method")
    void testingSpartanRepositoryPrintMethod(){
        SpartanRepository.addSpartan(mockSpartan);
        //Mockito.when(mockSpartan.toString()).thenReturn("Found mock spartan"); //used for mock as mock doesn't have a real return
        //System.out.println(SpartanRepository.getAllSpartans());
        //Mockito.doReturn("Found Mock Spartan").when(mockSpartan.toString()); //doReturn used for spy's
        Mockito.doThrow(NullPointerException.class).when(mockSpartan).setId(Mockito.anyInt());
        System.out.println(SpartanRepository.getAllSpartans());
        //Assertions.assertEquals("Found Mock Spartan" + "\n", SpartanRepository.getAllSpartans()); //don't have assertions and verify in the same test
        Mockito.verify(mockSpartan, Mockito.times(1)).toString();

    }
    @Test
    @DisplayName("Testing Method Order")
    void testingMethodOrder() {
        Mockito.when(mockSpartan.getStartDate())
                .thenReturn(LocalDate.now())
                .thenReturn(LocalDate.MAX);
        System.out.println(mockSpartan.getStartDate());
        System.out.println(mockSpartan.getStartDate());
    }

    @Test
    @DisplayName("Check that the getId method us called on our mock")
    void checkThatTheGetIdMethodUsCalledOnOurMock() {
        SpartanRepository.addSpartan(mockSpartan);
        boolean present = SpartanRepository.findSpartan(1).isPresent();
        Mockito.verify(mockSpartan, Mockito.times(1)).getId();

    }

    @Test
    @DisplayName("Check that spartan methods are called in the right order")
    void checkThatSpartanMethodsAreCalledInTheRightOrder() {
        mockSpartan.setName("Roshni");//no implementation for mock .setName
        mockSpartan.setCourse("Java");
        System.out.println(mockSpartan.getName() + " " + mockSpartan.getCourse());

        InOrder inOrder = inOrder(mockSpartan);
        inOrder.verify(mockSpartan).setName("Roshni");
        inOrder.verify(mockSpartan).setCourse("Java");
        inOrder.verify(mockSpartan).getName();
        inOrder.verify(mockSpartan).getCourse();
    }
}
