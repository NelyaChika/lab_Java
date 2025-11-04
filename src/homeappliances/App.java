package homeappliances;

import homeappliances.command.*;
import homeappliances.model.ApplianceManager;

public class App {

    public static void main(String[] args) {

        // 1. Ініціалізація моделі (Одержувач команд)
        ApplianceManager manager = new ApplianceManager();

        // 2. Ініціалізація меню (Викликач команд)
        Menu menu = new Menu();

        // 3. Створення та реєстрація команд

        // Команди керування даними
        menu.addCommand(new CalculatePowerCommand(manager)); // Обов'язкова вимога
        menu.addCommand(new SortCommand(manager));           // Обов'язкова вимога
        // menu.addCommand(new FindCommand(manager));         // Обов'язкова вимога (додати пізніше)

        // Команди файлового обміну (розширення)
        // menu.addCommand(new LoadCommand(manager));          // Додати пізніше
        // menu.addCommand(new SaveCommand(manager));          // Додати пізніше

        // Команди керування приладами (розширення)
        // menu.addCommand(new AddApplianceCommand(manager));  // Додати пізніше
        // menu.addCommand(new TogglePowerCommand(manager));   // Додати пізніше

        // Службові команди
        menu.addCommand(new ExitCommand()); // Вихід

        // 4. Запуск циклу меню
        menu.run();
    }
}
