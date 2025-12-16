package homeappliances.model;

import java.util.Locale;

public class TV extends EntertainmentAppliance {
    private double screenSize;
    private String screenType;

    public TV(String name, double power, String type, double screenSize, String screenType) {
        super(name, power, type);
        this.screenSize = screenSize;
        this.screenType = screenType;
    }

    public double getScreenSize() { return screenSize; }
    public String getScreenType() { return screenType; }

    @Override
    public String toDataString() {
        return String.format(Locale.ENGLISH,"TV;%s;%.2f;%b;%s;%.2f;%s",
                getName(), getPower(), isPluggedIn(), getType(), screenSize, screenType);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", екран: %.1f\" %s", screenSize, screenType);
    }
}
