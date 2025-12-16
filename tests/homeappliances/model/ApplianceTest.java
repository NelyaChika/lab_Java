package homeappliances.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApplianceTest {

    private Refrigerator appliance; // Використовуємо конкретний підклас для тестування абстрактного

    @BeforeEach
    void setUp() {
        appliance = new Refrigerator("TestFridge", 150.0, true, 4.0);
    }

    @Test
    void testPlugInUnplug() {
        assertFalse(appliance.isPluggedIn(), "Прилад має бути вимкнений за замовчуванням");

        appliance.plugIn();
        assertTrue(appliance.isPluggedIn(), "Прилад мав увімкнутися");

        appliance.unplug();
        assertFalse(appliance.isPluggedIn(), "Прилад мав вимкнутися");
    }

    @Test
    void testGetters() {
        assertEquals("TestFridge", appliance.getName(), "Неправильне ім'я");
        assertEquals(150.0, appliance.getPower(), 0.001, "Неправильна потужність");
    }

    @Test
    void testToString() {
        String result = appliance.toString();
        assertTrue(result.contains("TestFridge") && result.contains("150.0") && result.contains("[Вимкнено]"), "Неправильний toString для вимкненого стану");

        appliance.plugIn();
        result = appliance.toString();
        assertTrue(result.contains("[Увімкнено]"), "Неправильний toString для увімкненого стану");
    }

    @Test
    void testToDataString() {
        String data = appliance.toDataString();
        assertTrue(data.startsWith("REFRIGERATOR;TestFridge;150.00;false;true;4.00"), "Неправильний формат toDataString");
    }

    @Test
    void testKitchenApplianceSpecific() {
        assertTrue(appliance.hasTimer(), "Має бути таймер");
        assertEquals(4.0, appliance.getTemperature(), 0.001, "Неправильна температура");

        appliance.setTemperature(5.0);
        assertEquals(5.0, appliance.getTemperature(), 0.001, "Сеттер температури не працює");
    }
}