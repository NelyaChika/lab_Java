package homeappliances.model;

public class WashingMachine extends KitchenAppliance {
    private int programsCount;

    public WashingMachine(String name, double power, boolean hasTimer, int programsCount) {
        super(name, power, hasTimer);
        this.programsCount = programsCount;
    }

    public int getProgramsCount() { return programsCount; }
    public void setProgramsCount(int programsCount) { this.programsCount = programsCount; }

    @Override
    public String toDataString() {
        // WASHINGMACHINE;name;power;plugged;hasTimer;programsCount
        return String.format("WASHINGMACHINE;%s;%.2f;%b;%b;%d",
                getName(), getPower(), isPluggedIn(), hasTimer(), programsCount);
    }

    @Override
    public String toString() {
        return super.toString() + ", програм: " + programsCount;
    }
}
