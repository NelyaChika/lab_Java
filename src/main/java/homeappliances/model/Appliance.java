package homeappliances.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Locale;

public abstract class Appliance {
    // Створюємо логгер для ієрархії приладів
    private static final Logger logger = LogManager.getLogger(Appliance.class);

    private String name;
    private double power;
    private boolean pluggedIn;

    public Appliance(String name, double power) {
        this.name = name;
        this.power = power;
        this.pluggedIn = false;
        // Логуємо створення об'єкта (корисно для відладки)
        logger.debug("Створено новий прилад: {} ({})", name, this.getClass().getSimpleName());
    }

    public void plugIn() {
        this.pluggedIn = true;
        // Логування основної дії (INFO)
        logger.info("ДІЯ: Прилад '{}' підключено до мережі.", name);
    }

    public void unplug() {
        this.pluggedIn = false;
        logger.info("ДІЯ: Прилад '{}' вимкнено з мережі.", name);
    }

    public boolean isPluggedIn() { return pluggedIn; }
    public String getName() { return name; }
    public double getPower() { return power; }

    public abstract String toDataString();

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "%s (%.1f Вт) %s", name, power, (pluggedIn ? "[Увімкнено]" : "[Вимкнено]"));
    }
}