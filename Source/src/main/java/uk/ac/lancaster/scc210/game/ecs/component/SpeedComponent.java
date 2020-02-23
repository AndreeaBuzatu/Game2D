package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;

/**
 * Indicates that an entity has an internal speed.
 */
public class SpeedComponent implements Component {
    private int speed;

    /**
     * Instantiates a new Speed component.
     *
     * @param speed the speed at which the Entity moves
     */
    public SpeedComponent(int speed) {
        this.speed = speed;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
