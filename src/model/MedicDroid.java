package model;

public class MedicDroid extends Droid {
    public MedicDroid(String name) {
        super(name, 100, 8, 60, 0.7);
    }

    // Медик відновлює себе трохи після кожної дії і може лікувати союзника
    @Override
    protected void onAfterAttack() {
        energy = Math.min(energy + 4, 100);
        heal(3); // пасивна мала регенерація
    }

    // лікує ціль
    public int healAlly(Droid ally) {
        if (!isAlive() || !ally.isAlive()) return 0;
        int amount = 15;
        ally.heal(amount);
        energy = Math.max(0, energy - 10);
        return amount;
    }
}

