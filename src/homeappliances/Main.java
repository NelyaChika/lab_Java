package homeappliances;

import homeappliances.model.*;
import homeappliances.service.ApplianceService;
import homeappliances.util.FileManager;
import homeappliances.command.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Appliance> appliances = new ArrayList<>();
        // початкові прилади
        appliances.add(new Refrigerator("Samsung Fridge", 200, true, -4.0));
        appliances.add(new WashingMachine("Bosch Washer", 1500, true, 12));
        appliances.add(new TV("LG OLED", 120, "Smart TV", 55.0, "OLED"));
        appliances.add(new Thermostat("Xiaomi Thermostat", 50, 19.5, 22.0));

        ApplianceService service = new ApplianceService();
        FileManager fileManager = new FileManager();

        Map<Integer, Command> menu = new LinkedHashMap<>();
        menu.put(1, new ShowAllCommand(appliances));
        menu.put(2, new AddCommand(appliances, scanner));
        menu.put(3, new TogglePlugCommand(appliances, scanner));
        menu.put(4, new CalculatePowerCommand(appliances, service));
        menu.put(5, new SortByPowerCommand(appliances, service));
        menu.put(6, new FindByRangeCommand(appliances, service, scanner));
        menu.put(7, new SaveCommand(appliances, fileManager, "appliances.txt"));
        menu.put(8, new LoadCommand(appliances, fileManager, "appliances.txt"));
        menu.put(0, new ExitCommand());

        int choice = -1;
        do {
            printMenu();
            System.out.print("Вибір: ");
            String line = scanner.nextLine();
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Введіть число.");
                continue;
            }
            Command cmd = menu.get(choice);
            if (cmd != null) {
                cmd.execute();
                if (choice == 8) {
                    // після Load показуємо список
                    new ShowAllCommand(appliances).execute();
                }
            } else {
                System.out.println("Невірний вибір.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== МЕНЮ ===");
        System.out.println("1. Показати всі прилади");
        System.out.println("2. Додати прилад");
        System.out.println("3. Увімкнути/вимкнути прилад");
        System.out.println("4. Підрахувати загальну потужність (увімкнені)");
        System.out.println("5. Сортувати прилади за потужністю");
        System.out.println("6. Знайти прилади за діапазоном потужності");
        System.out.println("7. Зберегти у файл");
        System.out.println("8. Завантажити з файлу");
        System.out.println("0. Вийти");
    }
}
