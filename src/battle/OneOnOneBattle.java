package battle;

import model.Droid;

public class OneOnOneBattle {
    private final Droid a;
    private final Droid b;
    private final BattleLog log;

    public OneOnOneBattle(Droid a, Droid b) {
        this.a = a;
        this.b = b;
        this.log = new BattleLog();
        log.setTitle("1v1: " + a.getName() + " vs " + b.getName());
    }

    public BattleLog run() {
        log.add("Бій почався між " + a.shortInfo() + " та " + b.shortInfo());
        boolean turnA = true;
        int round = 1;
        while (a.isAlive() && b.isAlive()) {
            log.add("--- Рунд " + round + " ---");
            if (turnA) {
                int dmg = a.attack(b);
                if (dmg > 0)
                    log.add(a.getName() + " влучив у " + b.getName() + " на " + dmg + " HP. [" + b.getHealth() + " HP]");
                else
                    log.add(a.getName() + " промахнувся по " + b.getName() + ".");
            } else {
                int dmg = b.attack(a);
                if (dmg > 0)
                    log.add(b.getName() + " влучив у " + a.getName() + " на " + dmg + " HP. [" + a.getHealth() + " HP]");
                else
                    log.add(b.getName() + " промахнувся по " + a.getName() + ".");
            }
            turnA = !turnA;
            round++;
        }

        String winner = a.isAlive() ? a.getName() : b.getName();
        log.add("Переможець: " + winner);
        return log;
    }
}
