package homeappliances.command;

import homeappliances.model.Appliance;
import homeappliances.service.ApplianceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class FindByRangeCommand implements Command {
    // Ініціалізація логгера для поточного класу
    private static final Logger logger = LogManager.getLogger(FindByRangeCommand.class);

    private final List<Appliance> appliances;
    private final ApplianceService service;
    private final Scanner scanner;

    public FindByRangeCommand(List<Appliance> appliances, ApplianceService service, Scanner scanner) {
        this.appliances = appliances;
        this.service = service;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.print("Мінімальна потужність (Вт): ");
            String minInput = scanner.nextLine();
            double min = Double.parseDouble(minInput);

            System.out.print("Максимальна потужність (Вт): ");
            String maxInput = scanner.nextLine();
            double max = Double.parseDouble(maxInput);

            // Логуємо запит користувача
            logger.info("Пошук приладів у діапазоні потужності: від {} до {} Вт.", min, max);

            List<Appliance> res = service.findByPowerRange(appliances, min, max);

            if (res.isEmpty()) {
                logger.info("Результат пошуку: нічого не знайдено для діапазону {}-{}.", min, max);
                System.out.println("Не знайдено приладів у вказаному діапазоні.");
            } else {
                logger.info("Результат пошуку: знайдено {} прилад(ів).", res.size());
                System.out.println("Знайдені прилади:");
                res.forEach(System.out::println);
            }

        } catch (NumberFormatException e) {
            // Логуємо помилку введення як попередження
            logger.warn("Невдала спроба пошуку: користувач ввів некоректне число.");
            System.out.println("Помилка введення числа.");
        } catch (Exception e) {
            // Логуємо будь-які інші непередбачувані помилки (наприклад, null у списку)
            logger.error("Критична помилка при виконанні пошуку за діапазоном: ", e);
            System.out.println("Сталася системна помилка при пошуку.");
        }
    }
}