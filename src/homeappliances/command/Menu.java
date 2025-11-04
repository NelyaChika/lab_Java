package homeappliances.command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final Map<Integer, ICommand> commands = new LinkedHashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Додає команду до меню.
     * @param command Команда для додавання.
     */
    public void addCommand(ICommand command) {
        // Номер команди автоматично генерується як наступний ключ у Map
        int commandNumber = commands.size() + 1;
        commands.put(commandNumber, command);
    }

    /**
     * Відображає меню та очікує вибір користувача.
     */
    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();

            ICommand selectedCommand = commands.get(choice);

            if (selectedCommand != null) {
                // Виконання команди
                selectedCommand.execute();

                // Якщо обрана команда "Вихід", зупиняємо цикл
                if (selectedCommand instanceof ExitCommand) {
                    running = false;
                }
            } else {
                System.err.println("\n[Помилка] Невірний вибір. Спробуйте ще раз.");
            }
            System.out.println("\n" + "-".repeat(50));
        }
    }

    private void displayMenu() {
        System.out.println("\n*** Меню Управління Електроприладами (Варіант №3) ***");
        commands.forEach((key, command) ->
                System.out.println(key + ". " + command.getName())
        );
    }

    private int getUserChoice() {
        System.out.print("Оберіть дію (введіть номер): ");
        try {
            // Перевіряємо, чи є наступний токен цілим числом
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Очищаємо буфер після nextInt()
                return choice;
            } else {
                scanner.nextLine(); // Очищаємо некоректний ввід
                return -1; // Повертаємо невалідний вибір
            }
        } catch (Exception e) {
            // Обробка винятків (наприклад, закриття потоку)
            return -1;
        }
    }
}
