package homeappliances.command;

import homeappliances.model.Appliance;
import homeappliances.service.ApplianceService;
import java.util.List;

public class SortByPowerCommand implements Command {
    private final List<Appliance> appliances;
    private final ApplianceService service;

    public SortByPowerCommand(List<Appliance> appliances, ApplianceService service) {
        this.appliances = appliances;
        this.service = service;
    }

    @Override
    public void execute() {
        service.sortByPower(appliances);
        System.out.println("Відсортовано за потужністю (зростання):");
        appliances.forEach(System.out::println);
    }
}
