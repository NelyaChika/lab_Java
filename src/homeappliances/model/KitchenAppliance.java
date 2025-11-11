package homeappliances.model;

public abstract class KitchenAppliance extends Appliance {
    private boolean hasTimer;

    public KitchenAppliance(String name, double power, boolean hasTimer) {
        super(name, power);
        this.hasTimer = hasTimer;
    }

    public boolean hasTimer() { return hasTimer; }

    @Override
    public String toString() {
        return super.toString() + ", таймер: " + (hasTimer ? "є" : "нема");
    }
}
