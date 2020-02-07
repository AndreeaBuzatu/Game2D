package uk.ac.lancaster.scc210.game.items;

import uk.ac.lancaster.scc210.engine.ecs.Entity;

public interface ItemEffect {
    void react(Entity entity);
}
