package homeappliances;

import homeappliances.model.*;
import homeappliances.service.ApplianceService;
import homeappliances.util.FileManager;
import homeappliances.command.*;

// Імпорти для логування
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Main {
    // Створюємо логгер для класу Main
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        // INFO: Програма почала роботу (запишеться у файл)
        logger.info("Додаток 'HomeAppliances' запущено.");

        Scanner scanner = new Scanner(System.in);
        List<Appliance> appliances = new ArrayList<>();

        // Початкове наповнення списку
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

                // Логуємо вибір користувача
                logger.debug("Користувач обрав пункт меню: {}", choice);

            } catch (NumberFormatException e) {
                // WARN: Користувач ввів текст замість цифри (у файл)
                logger.warn("Помилка введення меню: користувач ввів '{}'", line);
                System.out.println("Введіть число.");
                continue;
            }

            Command cmd = menu.get(choice);
            if (cmd != null) {
                try {
                    cmd.execute();
                    if (choice == 8) {
                        new ShowAllCommand(appliances).execute();
                    }
                } catch (Exception e) {
                    // FATAL/ERROR: Якщо команда впала, надсилаємо e-mail
                    logger.fatal("КРИТИЧНА ПОМИЛКА під час виконання команди {}: {}",
                            cmd.getClass().getSimpleName(), e.getMessage(), e);
                }
            } else {
                logger.warn("Невірний вибір пункту меню: {}", choice);
                System.out.println("Невірний вибір.");
            }

        } while (choice != 0);

        logger.info("Додаток завершує роботу за запитом користувача.");

        // Пауза для відправки e-mail перед закриттям, якщо була помилка
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

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