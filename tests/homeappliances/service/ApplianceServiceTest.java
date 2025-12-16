package homeappliances.service;

import homeappliances.model.Appliance;
import homeappliances.model.Refrigerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplianceServiceTest {

    private ApplianceService service;
    private List<Appliance> appliances;

    @BeforeEach
    void setUp() {
        service = new ApplianceService();
        appliances = new ArrayList<>();
        appliances.add(new Refrigerator("Fridge1", 100.0, false, 5.0));
        appliances.add(new Refrigerator("Fridge2", 200.0, true, 4.0));
        appliances.add(new Refrigerator("Fridge3", 150.0, true, 6.0));
    }

    @Test
    void testCalculateTotalPower() {
        // Лише увімкнені: Fridge2 (200) + Fridge3 (150) = 350
        appliances.get(0).unplug(); // Fridge1 вимкнено
        appliances.get(1).plugIn();
        appliances.get(2).plugIn();
        double total = service.calculateTotalPower(appliances);
        assertEquals(350.0, total, 0.001, "Неправильний розрахунок загальної потужності");
    }

    @Test
    void testCalculateTotalPowerEmptyList() {
        assertEquals(0.0, service.calculateTotalPower(new ArrayList<>()), "Потужність для порожнього списку має бути 0");
    }

    @Test
    void testSortByPower() {
        service.sortByPower(appliances);
        assertEquals(100.0, appliances.get(0).getPower(), "Перший елемент не відсортований");
        assertEquals(150.0, appliances.get(1).getPower(), "Другий елемент не відсортований");
        assertEquals(200.0, appliances.get(2).getPower(), "Третій елемент не відсортований");
    }

    @Test
    void testFindByPowerRange() {
        List<Appliance> result = service.findByPowerRange(appliances, 120.0, 180.0);
        assertEquals(1, result.size(), "Неправильна кількість знайдених приладів");
        assertEquals(150.0, result.get(0).getPower(), "Неправильний прилад у діапазоні");
    }

    @Test
    void testFindByPowerRangeNoMatches() {
        List<Appliance> result = service.findByPowerRange(appliances, 300.0, 400.0);
        assertTrue(result.isEmpty(), "Повинен повернути порожній список, якщо нічого не знайдено");
    }
}