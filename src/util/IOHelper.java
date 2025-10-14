package util;

import battle.BattleLog;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class IOHelper {

    public static boolean saveLogToFile(BattleLog log, String filename) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8))) {
            writer.write(log.renderAll());
            return true;
        } catch (IOException e) {
            e.printStackTrace();  // Тут ми обробляємо виняток
            return false;
        }
    }

    public static String readLogFromFile(String filename) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }
}
