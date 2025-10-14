package battle;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BattleEvent implements Serializable {
    private final LocalDateTime time;
    private final String text;

    public BattleEvent(String text) {
        this.time = LocalDateTime.now();
        this.text = text;
    }

    public String render() {
        return "[" + time.toString() + "] " + text;
    }

    @Override
    public String toString() {
        return render();
    }
}
