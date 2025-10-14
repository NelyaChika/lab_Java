package app;

import model.*;
import battle.*;
import util.IOHelper;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Droid> droids = new ArrayList<>();
    private static BattleLog lastLog = null;

    public static void main(String[] args) {
        System.out.println("=== Битва дроїдів (Student v1) ===");
        boolean running = true;
        while (running) {
            printMenu();
            String cmd = scanner.nextLine().trim();
            switch (cmd) {
                case "1" -> createDroid();
                case "2" -> listDroids();
                case "3" -> doOneOnOne();
                case "4" -> doTeamBattle();
                case "5" -> saveLastBattle();
                case "6" -> replayFromFile();
                case "0" -> { running = false; System.out.println("Вихід..."); }
                default -> System.out.println("Невідома команда.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nМеню:");
        System.out.println("1 - Створити дроїда");
        System.out.println("2 - Показати список дроїдів");
        System.out.println("3 - Запустити бій 1 на 1");
        System.out.println("4 - Запустити бій команда на команду");
        System.out.println("5 - Записати останній бій у файл");
        System.out.println("6 - Відтворити бій зі збереженого файлу");
        System.out.println("0 - Вийти");
        System.out.print("Вибір: ");
    }

    private static void createDroid() {
        System.out.println("Оберіть тип дроїда: 1-Assault 2-Sniper 3-Medic");
        String t = scanner.nextLine().trim();
        System.out.print("Ім'я дроїда: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Droid" + (droids.size() + 1);
        Droid d = switch (t) {
            case "1" -> new AssaultDroid(name);
            case "2" -> new SniperDroid(name);
            case "3" -> new MedicDroid(name);
            default -> { System.out.println("Невідомий тип, створюємо Assault."); yield new AssaultDroid(name); }
        };
        droids.add(d);
        System.out.println("Створено: " + d.shortInfo());
    }

    private static void listDroids() {
        if (droids.isEmpty()) { System.out.println("Нема створених дроїдів."); return; }
        System.out.println("Список дроїдів:");
        for (Droid d : droids) System.out.println(d.shortInfo());
    }

    private static Droid findById(int id) {
        for (Droid d : droids) if (d.getId() == id) return d;
        return null;
    }

    private static void doOneOnOne() {
        if (droids.size() < 2) { System.out.println("Потрібно мінімум 2 дроїди."); return; }
        listDroids();
        System.out.print("ID першого: ");
        int a = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("ID другого: ");
        int b = Integer.parseInt(scanner.nextLine().trim());
        Droid da = findById(a);
        Droid db = findById(b);
        if (da == null || db == null) { System.out.println("Не знайдено дроїда."); return; }

        // Клонуємо стан дроїдів для бою (щоб не змінювати оригінали)
        Droid ca = cloneForBattle(da);
        Droid cb = cloneForBattle(db);

        OneOnOneBattle battle = new OneOnOneBattle(ca, cb);
        lastLog = battle.run();
        System.out.println(lastLog.renderAll());
    }

    private static void doTeamBattle() {
        if (droids.size() < 2) { System.out.println("Потрібно мінімум 2 дроїди."); return; }
        listDroids();
        System.out.println("Сформуйте команду A: напр. 1 3 4 (IDs через пробіл)");
        List<Droid> teamA = readTeamFromInput();
        System.out.println("Сформуйте команду B: напр. 2 5 (IDs через пробіл)");
        List<Droid> teamB = readTeamFromInput();
        if (teamA.isEmpty() || teamB.isEmpty()) { System.out.println("Команда пуста."); return; }

        // клонувати для бою
        List<Droid> ka = new ArrayList<>();
        List<Droid> kb = new ArrayList<>();
        for (Droid d : teamA) ka.add(cloneForBattle(d));
        for (Droid d : teamB) kb.add(cloneForBattle(d));

        TeamBattle tb = new TeamBattle(ka, kb);
        lastLog = tb.run();
        System.out.println(lastLog.renderAll());
    }

    private static List<Droid> readTeamFromInput() {
        String line = scanner.nextLine().trim();
        String[] parts = line.split("\\s+");
        List<Droid> result = new ArrayList<>();
        for (String p : parts) {
            try {
                int id = Integer.parseInt(p);
                Droid d = findById(id);
                if (d != null) result.add(d);
            } catch (NumberFormatException ignored) {}
        }
        return result;
    }

    private static Droid cloneForBattle(Droid original) {
        // Просто створимо новий дроїд того ж типу з тим самим ім'ям і базовими характеристиками
        if (original instanceof AssaultDroid) return new AssaultDroid(original.getName());
        if (original instanceof SniperDroid) return new SniperDroid(original.getName());
        if (original instanceof MedicDroid) return new MedicDroid(original.getName());
        // fallback
        return new AssaultDroid(original.getName());
    }

    private static void saveLastBattle() {
        if (lastLog == null) { System.out.println("Немає останнього бою для збереження."); return; }
        System.out.print("Введіть ім'я файлу (наприклад battle1.txt): ");
        String fname = scanner.nextLine().trim();
        if (fname.isEmpty()) fname = "battle.txt";
        boolean ok = IOHelper.saveLogToFile(lastLog, fname);
        if (ok) System.out.println("Збережено у " + fname);
        else System.out.println("Помилка при збережені.");
    }

    private static void replayFromFile() {
        System.out.print("Введіть ім'я файлу для відтворення: ");
        String fname = scanner.nextLine().trim();
        String content = IOHelper.readLogFromFile(fname);
        if (content == null) { System.out.println("Файл не знайдено або помилка."); return; }
        System.out.println("\n=== Відтворення бою з файлу ===\n");
        System.out.println(content);
    }
}
