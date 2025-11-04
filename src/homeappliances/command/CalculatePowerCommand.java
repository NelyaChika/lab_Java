package homeappliances.command;

import homeappliances.model.ApplianceManager;

public class CalculatePowerCommand implements ICommand {
    private final ApplianceManager manager;

    public CalculatePowerCommand(ApplianceManager manager) {
        this.manager = manager;
    }

    @Override
    public String getName() {
        return "Підрахувати сумарну потужність ввімкнених приладів";
    }

    @Override
    public void execute() {
        // Викликаємо метод-виконавець у класі-моделі
        manager.calculateTotalPower();
    }
}