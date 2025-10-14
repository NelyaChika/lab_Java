package model;

public class AssaultDroid extends Droid {
    public AssaultDroid(String name) {
        super(name, 120, 18, 50, 0.75);
    }

    @Override
    protected void onAfterAttack() {
        // Assault трохи відновлюється після атаки
        energy = Math.min(energy + 3, 100);
    }
}
