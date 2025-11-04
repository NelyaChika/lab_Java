package homeappliances.command;

public class ExitCommand implements ICommand {

    @Override
    public String getName() {
        return "Вихід з програми";
    }

    @Override
    public void execute() {
        System.out.println("Програма завершена. До побачення!");
    }
}