package homeappliances.command;

import homeappliances.model.Appliance;
import homeappliances.service.ApplianceService;
import java.util.List;
import java.util.Scanner;

public class FindByRangeCommand implements Command {
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
            double min = Double.parseDouble(scanner.nextLine());
            System.out.print("Максимальна потужність (Вт): ");
            double max = Double.parseDouble(scanner.nextLine());
            List<Appliance> res = service.findByPowerRange(appliances, min, max);
            if (res.isEmpty()) {
                System.out.println("Не знайдено приладів у вказаному діапазоні.");
            } else {
                System.out.println("Знайдені прилади:");
                res.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.out.println("Помилка введення числа.");
        }
    }
}
