package model;

public class SniperDroid extends Droid {
    public SniperDroid(String name) {
        super(name, 80, 30, 40, 0.85);
    }

    @Override
    protected void onAfterAttack() {
        // Якщо енергія достатня — може завдати підсилений постріл раз на деякий час
        if (energy >= 10) {
            energy -= 10;
        } else {
            energy = Math.min(energy + 2, 100);
        }
    }
}
