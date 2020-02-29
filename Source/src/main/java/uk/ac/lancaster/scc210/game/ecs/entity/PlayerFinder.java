package uk.ac.lancaster.scc210.game.ecs.entity;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;

public class PlayerFinder {
    /**
     * Finds the first entity with PlayerComponent from the given world
     *
     * @param world The world at this point in time
     * @return Player|null
     */
    public static Entity findPlayer(World world) {
        return world.getEntitiesFor(PlayerComponent.class).stream().findFirst().orElse(null);
    }
}
