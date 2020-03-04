package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;

/**
 * The type Enemy component.
 */
public class EnemyComponent implements Component {
    private boolean canFire;

    /**
     * Instantiates a new Enemy component.
     */
    public EnemyComponent() {
        canFire = true;
    }

    /**
     * Can fire boolean.
     *
     * @return the boolean
     */
    public boolean canFire() {
        return canFire;
    }

    /**
     * Sets firing.
     *
     * @param firing the firing
     */
    public void setFiring(boolean firing) {
        this.canFire = firing;
    }
}
