package homeappliances.service;

import homeappliances.model.Appliance;
import java.util.*;

public class ApplianceService {

    public double calculateTotalPower(List<Appliance> appliances) {
        return appliances.stream()
                .filter(Appliance::isPluggedIn)
                .mapToDouble(Appliance::getPower)
                .sum();
    }

    public void sortByPower(List<Appliance> appliances) {
        appliances.sort(Comparator.comparingDouble(Appliance::getPower));
    }

    public List<Appliance> findByPowerRange(List<Appliance> appliances, double min, double max) {
        List<Appliance> res = new ArrayList<>();
        for (Appliance a : appliances) {
            if (a.getPower() >= min && a.getPower() <= max) res.add(a);
        }
        return res;
    }
}
