package com.sparta.rp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class SpartanRepository {
    private static final ArrayList<Spartan> spartans = new ArrayList<>();
    public static void addSpartan(Spartan spartan) {
        spartans.add(spartan);
    }
    public static Optional<Spartan> findSpartan(int id) {
        Spartan returnedSpartan = null;
        for (Spartan spartan : spartans) {
            if (spartan.getId() == id) {
                returnedSpartan = spartan;
            }
        }
        return Optional.ofNullable(returnedSpartan);
    }
    public static String getAllSpartans() {
        StringBuilder SpartansInArray = new StringBuilder();
        for (Spartan spartan : spartans) {
            SpartansInArray.append(spartan.toString()).append("\n");
        }
        return SpartansInArray.toString();
    }
    public static boolean removeSpartan(int id) {
        return spartans.removeIf(spartan -> spartan.getId() == id);
    }

    public static void main(String[] args) {
        addSpartan(new Spartan(1, "Roshni", "Java", LocalDate.of(2022,11,4)));
        addSpartan(new Spartan(2, "Lizzie", "DevOps", LocalDate.of(2021,7,2)));
        addSpartan(new Spartan(3, "Hannah", "Business", LocalDate.of(2020,1,1)));
        addSpartan(new Spartan(4, "Lara", "Java", LocalDate.of(2022,9,4)));

    }
}
