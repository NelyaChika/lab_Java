package battle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BattleLog implements Serializable {
    private final List<BattleEvent> events = new ArrayList<>();
    private String title = "Battle";

    public void add(String text) {
        events.add(new BattleEvent(text));
    }

    public void setTitle(String t) { this.title = t; }
    public String getTitle() { return title; }

    public List<BattleEvent> getEvents() { return events; }

    public String renderAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ").append(title).append(" ===\n");
        for (BattleEvent e : events) {
            sb.append(e.render()).append("\n");
        }
        sb.append("=== END ===\n");
        return sb.toString();
    }
}

