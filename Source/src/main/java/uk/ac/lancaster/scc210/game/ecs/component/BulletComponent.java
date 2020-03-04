package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.engine.ecs.Entity;

/**
 * The type Bullet component.
 */
public class BulletComponent implements Component {
    private final Entity creator;

    /**
     * Instantiates a new Bullet component.
     *
     * @param creator the creator
     */
    public BulletComponent(Entity creator) {
        this.creator = creator;
    }

    /**
     * Gets creator.
     *
     * @return the creator
     */
    public Entity getCreator() {
        return creator;
    }
}
