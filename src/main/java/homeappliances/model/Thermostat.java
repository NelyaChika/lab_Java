package homeappliances.model;

import java.util.Locale;

public class Thermostat extends Appliance {
    private double currentTemperature;
    private double targetTemperature;

    public Thermostat(String name, double power, double currentTemperature, double targetTemperature) {
        super(name, power);
        this.currentTemperature = currentTemperature;
        this.targetTemperature = targetTemperature;
    }

    public double getCurrentTemperature() { return currentTemperature; }
    public void setCurrentTemperature(double currentTemperature) { this.currentTemperature = currentTemperature; }

    public double getTargetTemperature() { return targetTemperature; }
    public void setTargetTemperature(double targetTemperature) { this.targetTemperature = targetTemperature; }

    public void adjustTemperature() {
        if (!isPluggedIn()) {
            System.out.println("Термостат вимкнений.");
            return;
        }
        if (currentTemperature < targetTemperature) {
            System.out.println("Підігріваємо... (умовно)");
            currentTemperature = Math.min(targetTemperature, currentTemperature + 0.5);
        } else if (currentTemperature > targetTemperature) {
            System.out.println("Охолоджуємо... (умовно)");
            currentTemperature = Math.max(targetTemperature, currentTemperature - 0.5);
        } else {
            System.out.println("Температура вже на цільовому рівні.");
        }
    }

    @Override
    public String toDataString() {
        return String.format(Locale.ENGLISH,"THERMOSTAT;%s;%.2f;%b;%.2f;%.2f",
                getName(), getPower(), isPluggedIn(), currentTemperature, targetTemperature);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", поточна: %.1f°C, цільова: %.1f°C", currentTemperature, targetTemperature);
    }
}

