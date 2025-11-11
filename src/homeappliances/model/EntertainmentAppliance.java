package homeappliances.model;

public abstract class EntertainmentAppliance extends Appliance {
    private String type; // наприклад "Smart TV", "Колонки"

    public EntertainmentAppliance(String name, double power, String type) {
        super(name, power);
        this.type = type;
    }

    public String getType() { return type; }

    @Override
    public String toString() {
        return super.toString() + ", тип: " + type;
    }
}

