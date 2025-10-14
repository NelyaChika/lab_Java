package model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Droid implements Serializable {
    private static final AtomicInteger NEXT_ID = new AtomicInteger(1);

    protected final int id;
    protected String name;
    protected int maxHealth;
    public int getMaxHealth() { return maxHealth; }
    protected int health;

    protected int damage;
    protected int energy; // додаткова властивість
    protected double accuracy; // [0..1]

    public Droid(String name, int health, int damage, int energy, double accuracy) {
        this.id = NEXT_ID.getAndIncrement();
        this.name = name;
        this.maxHealth = health;
        this.health = health;
        this.damage = damage;
        this.energy = energy;
        this.accuracy = accuracy;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getHealth() { return health; }
    public boolean isAlive() { return health > 0; }
    public int getDamage() { return damage; }
    public int getEnergy() { return energy; }

    // атакує ціль, повертає фактичний нанесений урон (може бути 0 якщо промах)
    public int attack(Droid target) {
        if (!isAlive() || !target.isAlive()) return 0;
        double roll = Math.random();
        if (roll <= accuracy) {
            int actual = damage;
            target.receiveDamage(actual);
            onAfterAttack();
            return actual;
        } else {
            onAfterAttack();
            return 0; // промах
        }
    }

    // виклик після атаки (наприклад зменшити енергію, регенерація тощо)
    protected void onAfterAttack() {
        // за замовчуванням — трохи регенерації енергії
        energy = Math.min(energy + 1, 100);
    }

    public void receiveDamage(int d) {
        health = Math.max(0, health - d);
    }

    public void heal(int amount) {
        if (!isAlive()) return;
        health = Math.min(maxHealth, health + amount);
    }

    public String shortInfo() {
        return String.format("[%d] %s (%s) HP:%d DMG:%d ENG:%d ACC:%.2f",
                id, name, this.getClass().getSimpleName(), health, damage, energy, accuracy);
    }

    @Override
    public String toString() {
        return shortInfo();
    }
}
