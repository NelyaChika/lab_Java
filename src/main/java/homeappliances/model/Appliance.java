package homeappliances.model;

import java.util.Locale;

public abstract class Appliance {
    private String name;
    private double power;
    private boolean pluggedIn;

    public Appliance(String name, double power) {
        this.name = name;
        this.power = power;
        this.pluggedIn = false;
    }

    public void plugIn() { this.pluggedIn = true; }
    public void unplug() { this.pluggedIn = false; }
    public boolean isPluggedIn() { return pluggedIn; }

    public String getName() { return name; }
    public double getPower() { return power; }

    public abstract String toDataString();

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "%s (%.1f Вт) %s", name, power, (pluggedIn ? "[Увімкнено]" : "[Вимкнено]"));
    }
}
