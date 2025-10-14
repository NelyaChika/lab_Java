package battle;

import model.Droid;
import model.MedicDroid;

import java.util.*;

public class TeamBattle {
    private final List<Droid> team1;
    private final List<Droid> team2;
    private final Random rnd = new Random();
    private final BattleLog log = new BattleLog();

    public TeamBattle(List<Droid> team1, List<Droid> team2) {
        this.team1 = team1;
        this.team2 = team2;
        log.setTitle("Командний бій: A(" + team1.size() + ") vs B(" + team2.size() + ")");
    }

    public BattleLog run() {
        log.add("=== Початок командного бою ===");
        int round = 1;

        while (isAnyAlive(team1) && isAnyAlive(team2)) {
            log.add("\n--- Раунд " + round + " ---");
            performTeamAction(team1, team2, "Команда 1");
            performTeamAction(team2, team1, "Команда 2");
            round++;
        }

        String winner;
        if (isAnyAlive(team1)) winner = "Команда 1";
        else if (isAnyAlive(team2)) winner = "Команда 2";
        else winner = "Нічия";

        log.add("=== Кінець бою === Переможець: " + winner);
        return log;
    }

    private boolean isAnyAlive(List<Droid> team) {
        return team.stream().anyMatch(Droid::isAlive);
    }

    private List<Droid> living(List<Droid> team) {
        List<Droid> alive = new ArrayList<>();
        for (Droid d : team) {
            if (d.isAlive()) alive.add(d);
        }
        return alive;
    }

    private Droid pickLowestHealth(List<Droid> team) {
        return team.stream()
                .filter(Droid::isAlive)
                .min(Comparator.comparingInt(Droid::getHealth))
                .orElse(null);
    }

    private void performTeamAction(List<Droid> me, List<Droid> enemy, String tag) {
        List<Droid> myAlive = living(me);
        List<Droid> enemyAlive = living(enemy);
        if (myAlive.isEmpty() || enemyAlive.isEmpty()) return;

        for (Droid d : new ArrayList<>(myAlive)) {
            if (!d.isAlive()) continue;

            // Якщо медик — лікує союзника
            if (d instanceof MedicDroid) {
                MedicDroid m = (MedicDroid) d;
                Droid toHeal = pickLowestHealth(myAlive);
                if (toHeal != null && toHeal.getHealth() < toHeal.getMaxHealth() / 2 && m.getEnergy() >= 10) {
                    int healed = m.healAlly(toHeal);
                    log.add(tag + ": " + m.getName() + " вилікував " + toHeal.getName() +
                            " на " + healed + " HP [" + toHeal.getHealth() + " HP]");
                    continue;
                }
            }

            // Інакше атакує випадкового ворога
            Droid target = enemyAlive.get(rnd.nextInt(enemyAlive.size()));
            int dmg = d.attack(target);
            if (dmg > 0) {
                log.add(tag + ": " + d.getName() + " атакував " + target.getName() +
                        " на " + dmg + " HP [" + target.getHealth() + " HP]");
            } else {
                log.add(tag + ": " + d.getName() + " промахнувся по " + target.getName() + ".");
            }
        }
    }
}
