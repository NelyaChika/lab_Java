package main;

import model.Train;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Створюємо список поїздів
        List<Train> trains = new ArrayList<>();
        trains.add(new Train("Київ", 101, "08:30", 50, 20, 40, 10));
        trains.add(new Train("Львів", 202, "12:15", 0, 30, 25, 5));
        trains.add(new Train("Київ", 303, "19:45", 10, 15, 20, 2));

        // Виводимо всі поїзди
        for (Train t : trains) {
            System.out.println(t);
        }
    }
}
