package homeappliances.command;

import homeappliances.model.Appliance;
import java.util.List;
import java.util.Scanner;

public class TogglePlugCommand implements Command {
    private final List<Appliance> appliances;
    private final Scanner scanner;

    public TogglePlugCommand(List<Appliance> appliances, Scanner scanner) {
        this.appliances = appliances;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (appliances.isEmpty()) {
            System.out.println("Нема приладів.");
            return;
        }
        System.out.print("Введіть індекс приладу: ");
        try {
            int idx = Integer.parseInt(scanner.nextLine());
            if (idx < 0 || idx >= appliances.size()) {
                System.out.println("Невірний індекс.");
                return;
            }
            Appliance a = appliances.get(idx);
            if (a.isPluggedIn()) {
                a.unplug();
                System.out.println("Вимкнено: " + a.getName());
            } else {
                a.plugIn();
                System.out.println("Увімкнено: " + a.getName());
            }
        } catch (NumberFormatException e) {
            System.out.println("Помилка введення індексу.");
        }
    }
}
