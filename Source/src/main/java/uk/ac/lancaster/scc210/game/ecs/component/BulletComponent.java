package uk.ac.lancaster.scc210.game.ecs.component;

import uk.ac.lancaster.scc210.engine.ecs.Component;
import uk.ac.lancaster.scc210.engine.ecs.Entity;

public class BulletComponent implements Component {
    private final Entity creator;

    public BulletComponent(Entity creator) {
        this.creator = creator;
    }

    public Entity getCreator() {
        return creator;
    }
}
