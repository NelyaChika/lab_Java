package homeappliances.command;

import homeappliances.model.Appliance;
import homeappliances.model.Refrigerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TogglePlugCommandTest {

    private List<Appliance> appliances;
    private TogglePlugCommand command;

    @BeforeEach
    void setUp() {
        appliances = new ArrayList<>();
        appliances.add(new Refrigerator("Fridge1", 100.0, false, 5.0));
    }

    @Test
    void testExecuteToggleOn() {
        // Симулюємо ввід "0" для індексу
        System.setIn(new ByteArrayInputStream("0\n".getBytes()));
        command = new TogglePlugCommand(appliances, new Scanner(System.in));
        command.execute();
        assertTrue(appliances.get(0).isPluggedIn(), "Прилад мав увімкнутися");
    }

    @Test
    void testExecuteToggleOff() {
        appliances.get(0).plugIn(); // Спочатку увімкнути
        System.setIn(new ByteArrayInputStream("0\n".getBytes()));
        command = new TogglePlugCommand(appliances, new Scanner(System.in));
        command.execute();
        assertFalse(appliances.get(0).isPluggedIn(), "Прилад мав вимкнутися");
    }

    @Test
    void testExecuteInvalidIndex() {
        System.setIn(new ByteArrayInputStream("5\n".getBytes())); // Невірний індекс
        command = new TogglePlugCommand(appliances, new Scanner(System.in));
        command.execute();
        assertFalse(appliances.get(0).isPluggedIn(), "Стан не мав змінитися при невірному індексі");
    }

    @Test
    void testExecuteEmptyList() {
        command = new TogglePlugCommand(new ArrayList<>(), new Scanner(System.in));
        command.execute(); // Повинен вивести повідомлення, але не крашити
    }
}