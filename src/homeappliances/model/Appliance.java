package homeappliances.model;

public abstract class Appliance {
    private String name;
    private double power; // Вати
    private boolean pluggedIn;

    public Appliance(String name, double power) {
        this.name = name;
        this.power = power;
        this.pluggedIn = false;
    }

    // --- базова поведінка ---
    public void plugIn() { this.pluggedIn = true; }
    public void unplug() { this.pluggedIn = false; }
    public boolean isPluggedIn() { return pluggedIn; }

    public String getName() { return name; }
    public double getPower() { return power; }

    // рядок для збереження у файл (TYPE;field1;field2;...)
    public abstract String toDataString();

    @Override
    public String toString() {
        return String.format("%s (%.1f Вт) %s", name, power, (pluggedIn ? "[Увімкнено]" : "[Вимкнено]"));
    }
}
