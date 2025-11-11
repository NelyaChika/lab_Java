package homeappliances.command;

import homeappliances.model.Appliance;
import java.util.List;

public class ShowAllCommand implements Command {
    private final List<Appliance> appliances;

    public ShowAllCommand(List<Appliance> appliances) { this.appliances = appliances; }

    @Override
    public void execute() {
        if (appliances.isEmpty()) {
            System.out.println("Список приладів порожній.");
            return;
        }
        for (int i = 0; i < appliances.size(); i++) {
            System.out.printf("%d: %s%n", i, appliances.get(i));
        }
    }
}
