package homeappliances.command;

import homeappliances.model.ApplianceManager;

public class SortCommand implements ICommand {
    private final ApplianceManager manager;

    public SortCommand(ApplianceManager manager) {
        this.manager = manager;
    }

    @Override
    public String getName() {
        return "Сортувати прилади за потужністю";
    }

    @Override
    public void execute() {
        manager.sortAppliances();
    }
}
