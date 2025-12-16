package homeappliances.command;

import homeappliances.model.*;
import java.util.List;
import java.util.Scanner;

public class AddCommand implements Command {
    private final List<Appliance> appliances;
    private final Scanner scanner;

    public AddCommand(List<Appliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Виберіть тип приладу: 1-Холодильник, 2-Пральна машина, 3-TV, 4-Термостат");
        String opt = scanner.nextLine();
        try {
            switch (opt) {
                case "1": {
                    System.out.print("Ім'я: "); String name = scanner.nextLine();
                    System.out.print("Потужність (Вт): "); double p = Double.parseDouble(scanner.nextLine());
                    System.out.print("Таймер (true/false): "); boolean hasTimer = Boolean.parseBoolean(scanner.nextLine());
                    System.out.print("Температура (°C): "); double temp = Double.parseDouble(scanner.nextLine());
                    appliances.add(new Refrigerator(name, p, hasTimer, temp));
                    System.out.println("Холодильник додано.");
                    break;
                }
                case "2": {
                    System.out.print("Ім'я: "); String name = scanner.nextLine();
                    System.out.print("Потужність (Вт): "); double p = Double.parseDouble(scanner.nextLine());
                    System.out.print("Таймер (true/false): "); boolean hasTimer = Boolean.parseBoolean(scanner.nextLine());
                    System.out.print("Кількість програм: "); int prog = Integer.parseInt(scanner.nextLine());
                    appliances.add(new WashingMachine(name, p, hasTimer, prog));
                    System.out.println("Пральну машину додано.");
                    break;
                }
                case "3": {
                    System.out.print("Ім'я: "); String name = scanner.nextLine();
                    System.out.print("Потужність (Вт): "); double p = Double.parseDouble(scanner.nextLine());
                    System.out.print("Тип (наприклад Smart TV): "); String type = scanner.nextLine();
                    System.out.print("Діагональ (дюйми): "); double size = Double.parseDouble(scanner.nextLine());
                    System.out.print("Тип екрану: "); String scrType = scanner.nextLine();
                    appliances.add(new TV(name, p, type, size, scrType));
                    System.out.println("TV додано.");
                    break;
                }
                case "4": {
                    System.out.print("Ім'я: "); String name = scanner.nextLine();
                    System.out.print("Потужність (Вт): "); double p = Double.parseDouble(scanner.nextLine());
                    System.out.print("Поточна температура (°C): "); double cur = Double.parseDouble(scanner.nextLine());
                    System.out.print("Цільова температура (°C): "); double tar = Double.parseDouble(scanner.nextLine());
                    appliances.add(new Thermostat(name, p, cur, tar));
                    System.out.println("Термостат додано.");
                    break;
                }
                default:
                    System.out.println("Невірний вибір типу.");
            }
        } catch (Exception e) {
            System.out.println("Помилка при додаванні приладу: " + e.getMessage());
        }
    }
}
