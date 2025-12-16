package homeappliances.model;

import java.util.Locale;

public class Refrigerator extends KitchenAppliance {
    private double temperature; // °C

    public Refrigerator(String name, double power, boolean hasTimer, double temperature) {
        super(name, power, hasTimer);
        this.temperature = temperature;
    }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    @Override
    public String toDataString() {
        // REFRIGERATOR;name;power;plugged;hasTimer;temperature
        return String.format(Locale.ENGLISH, "REFRIGERATOR;%s;%.2f;%b;%b;%.2f",
                getName(), getPower(), isPluggedIn(), hasTimer(), temperature);
    }

    @Override
    public String toString() {
        return super.toString() + ", температура: " + temperature + "°C";
    }
}
