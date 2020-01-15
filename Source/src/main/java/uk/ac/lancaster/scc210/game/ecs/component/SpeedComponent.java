package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;

public class SpeedComponent implements Component {
    private final int speed;

    public SpeedComponent(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
