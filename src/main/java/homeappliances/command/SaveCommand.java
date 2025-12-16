package homeappliances.command;

import homeappliances.model.Appliance;
import homeappliances.util.FileManager;
import java.util.List;

public class SaveCommand implements Command {
    private final List<Appliance> appliances;
    private final FileManager fileManager;
    private final String filename;

    public SaveCommand(List<Appliance> appliances, FileManager fileManager, String filename) {
        this.appliances = appliances;
        this.fileManager = fileManager;
        this.filename = filename;
    }

    @Override
    public void execute() {
        try {
            fileManager.save(appliances, filename);
            System.out.println("Збережено у файл: " + filename);
        } catch (Exception e) {
            System.out.println("Помилка збереження: " + e.getMessage());
        }
    }
}

