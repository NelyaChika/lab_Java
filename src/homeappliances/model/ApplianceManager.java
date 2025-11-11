package homeappliances.model;

import java.util.ArrayList;
import java.util.List;


public class ApplianceManager {
    // Припустимо, що тут буде список приладів
    private final List<Object> appliances = new ArrayList<>();

    public ApplianceManager() {
        // Ініціалізація, якщо потрібна.
    }

    public List<Object> getAppliances() {
        return appliances;
    }

    // Методи для команд (повна логіка - Етап 3)
    public void calculateTotalPower() {
        System.out.println("-> [Виконання] Підрахунок сумарної потужності ввімкнених приладів...");
        // Тут буде логіка підрахунку
    }

    public void sortAppliances() {
        System.out.println("-> [Виконання] Сортування приладів за потужністю...");
        // Тут буде логіка сортування
    }

    // ... інші методи ...
}