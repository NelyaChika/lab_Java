package homeappliances.command;

import homeappliances.model.Appliance;
import homeappliances.util.FileManager;
import java.util.List;

public class LoadCommand implements Command {
    private final List<Appliance> appliances;
    private final FileManager fileManager;
    private final String filename;

    public LoadCommand(List<Appliance> appliances, FileManager fileManager, String filename) {
        this.appliances = appliances;
        this.fileManager = fileManager;
        this.filename = filename;
    }

    @Override
    public void execute() {
        try {
            List<Appliance> loaded = fileManager.load(filename);
            appliances.clear();
            appliances.addAll(loaded);
            System.out.println("Завантажено з файлу: " + filename);
        } catch (Exception e) {
            System.out.println("Помилка завантаження: " + e.getMessage());
        }
    }
}

