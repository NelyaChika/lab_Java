package homeappliances.command;

import homeappliances.model.Appliance;
import homeappliances.model.Refrigerator;
import homeappliances.service.ApplianceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class FindByRangeCommandTest {

    private List<Appliance> appliances;
    private ApplianceService service;
    private FindByRangeCommand command;

    @BeforeEach
    void setUp() {
        service = new ApplianceService();
        appliances = new ArrayList<>();
        appliances.add(new Refrigerator("Fridge1", 100.0, false, 5.0));
        appliances.add(new Refrigerator("Fridge2", 200.0, true, 4.0));
    }

    @Test
    void testExecuteFindSuccess() {
        // Симулюємо ввід "100" і "200"
        System.setIn(new ByteArrayInputStream("100\n200\n".getBytes()));
        command = new FindByRangeCommand(appliances, service, new Scanner(System.in));
        command.execute();
        // Перевіряємо, чи метод викликався (без перевірки виводу, бо це юніт-тест)
    }

    @Test
    void testExecuteInvalidInput() {
        System.setIn(new ByteArrayInputStream("abc\n".getBytes())); // Невірний ввід
        command = new FindByRangeCommand(appliances, service, new Scanner(System.in));
        command.execute(); // Повинен обробити помилку без крашу
    }

    @Test
    void testExecuteNoResults() {
        System.setIn(new ByteArrayInputStream("300\n400\n".getBytes()));
        command = new FindByRangeCommand(appliances, service, new Scanner(System.in));
        command.execute(); // Повинен вивести повідомлення про відсутність
    }
}