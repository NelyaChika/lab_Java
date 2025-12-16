package homeappliances.command;

import homeappliances.model.Appliance;
import homeappliances.service.ApplianceService;
import java.util.List;

public class CalculatePowerCommand implements Command {
    private final List<Appliance> appliances;
    private final ApplianceService service;

    public CalculatePowerCommand(List<Appliance> appliances, ApplianceService service) {
        this.appliances = appliances;
        this.service = service;
    }

    @Override
    public void execute() {
        double total = service.calculateTotalPower(appliances);
        System.out.printf("Загальна потужність увімкнених приладів: %.2f Вт%n", total);
    }
}
