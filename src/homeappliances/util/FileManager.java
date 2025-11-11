package homeappliances.util;

import homeappliances.model.*;
import java.io.*;
import java.util.*;

public class FileManager {

    /** Зберігає список у файл у простому текстовому форматі (одна сутність на рядок) */
    public void save(List<Appliance> appliances, String filename) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Appliance a : appliances) {
                bw.write(a.toDataString());
                bw.newLine();
            }
        }
    }

    /** Завантажує список з файлу */
    public List<Appliance> load(String filename) throws IOException {
        List<Appliance> list = new ArrayList<>();
        File f = new File(filename);
        if (!f.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.strip().isEmpty()) continue;
                Appliance a = parseLine(line);
                if (a != null) list.add(a);
            }
        }
        return list;
    }

    private Appliance parseLine(String line) {
        // Формат: TYPE;... (залежно від TYPE)
        String[] parts = line.split(";", -1);
        String type = parts[0].toUpperCase(Locale.ROOT);

        try {
            switch (type) {
                case "REFRIGERATOR": {
                    // REFRIGERATOR;name;power;plugged;hasTimer;temperature
                    String name = parts[1];
                    double power = Double.parseDouble(parts[2]);
                    boolean plugged = Boolean.parseBoolean(parts[3]);
                    boolean hasTimer = Boolean.parseBoolean(parts[4]);
                    double temp = Double.parseDouble(parts[5]);
                    Refrigerator r = new Refrigerator(name, power, hasTimer, temp);
                    if (plugged) r.plugIn();
                    return r;
                }
                case "WASHINGMACHINE": {
                    String name = parts[1];
                    double power = Double.parseDouble(parts[2]);
                    boolean plugged = Boolean.parseBoolean(parts[3]);
                    boolean hasTimer = Boolean.parseBoolean(parts[4]);
                    int programs = Integer.parseInt(parts[5]);
                    WashingMachine w = new WashingMachine(name, power, hasTimer, programs);
                    if (plugged) w.plugIn();
                    return w;
                }
                case "TV": {
                    // TV;name;power;plugged;type;screenSize;screenType
                    String name = parts[1];
                    double power = Double.parseDouble(parts[2]);
                    boolean plugged = Boolean.parseBoolean(parts[3]);
                    String tvType = parts[4];
                    double screenSize = Double.parseDouble(parts[5]);
                    String screenType = parts[6];
                    TV tv = new TV(name, power, tvType, screenSize, screenType);
                    if (plugged) tv.plugIn();
                    return tv;
                }
                case "THERMOSTAT": {
                    // THERMOSTAT;name;power;plugged;currentTemp;targetTemp
                    String name = parts[1];
                    double power = Double.parseDouble(parts[2]);
                    boolean plugged = Boolean.parseBoolean(parts[3]);
                    double current = Double.parseDouble(parts[4]);
                    double target = Double.parseDouble(parts[5]);
                    Thermostat t = new Thermostat(name, power, current, target);
                    if (plugged) t.plugIn();
                    return t;
                }
                default:
                    System.out.println("Невідомий тип у файлі: " + type);
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Помилка парсинга рядка: " + line + " -> " + e.getMessage());
            return null;
        }
    }
}
