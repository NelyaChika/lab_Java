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
        trains.add(new Train("Одеса", 404, "22:00", 0, 10, 15, 1));
        trains.add(new Train("Київ", 505, "06:10", 5, 8, 12, 0));

        // Викликаємо методи
        System.out.println("=== a) Поїзди до Києва ===");
        printTrainsByDestination(trains, "Київ");

        System.out.println("\n=== b) Поїзди до Києва після 10:00 ===");
        printTrainsByDestinationAndTime(trains, "Київ", "10:00");

        System.out.println("\n=== c) Поїзди до Києва з загальними місцями ===");
        printTrainsWithGeneralSeats(trains, "Київ");
    }

    // a) Поїзди до заданого пункту
    public static void printTrainsByDestination(List<Train> trains, String destination) {
        for (Train t : trains) {
            if (t.getDestination().equalsIgnoreCase(destination)) {
                System.out.println(t);
            }
        }
    }

    // b) Поїзди до пункту після заданого часу
    public static void printTrainsByDestinationAndTime(List<Train> trains, String destination, String time) {
        for (Train t : trains) {
            if (t.getDestination().equalsIgnoreCase(destination)
                    && t.getDepartureTime().compareTo(time) > 0) {
                System.out.println(t);
            }
        }
    }

    // c) Поїзди з загальними місцями
    public static void printTrainsWithGeneralSeats(List<Train> trains, String destination) {
        for (Train t : trains) {
            if (t.getDestination().equalsIgnoreCase(destination) && t.getGeneralSeats() > 0) {
                System.out.println(t);
            }
        }
    }
}
