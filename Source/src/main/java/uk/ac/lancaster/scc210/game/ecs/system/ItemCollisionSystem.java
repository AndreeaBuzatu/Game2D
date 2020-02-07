package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.ItemEffectsComponent;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

import java.util.Optional;

public class ItemCollisionSystem extends IterativeSystem {
    // We only have one player but the abstraction works like so - oh well
    private Optional<Entity> player;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public ItemCollisionSystem(World world) {
        super(world, ItemEffectsComponent.class);

        player = world.getEntitiesFor(PlayerComponent.class).stream().findFirst();
    }

    @Override
    public void entityAdded(Entity entity) {
        super.entityAdded(entity);

        player = world.getEntitiesFor(PlayerComponent.class).stream().findFirst();
    }

    @Override
    public void update(Time deltaTime) {
        // Abort if player can't be found
        if (player.isEmpty()) {
            return;
        }

        Entity playerEntity = player.get();

        for (Entity entity : entities) {
            SpriteComponent itemSpriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            SpriteComponent playerSpriteComponent = (SpriteComponent) playerEntity.findComponent(SpriteComponent.class);

            boolean collision = itemSpriteComponent.getSprite().getGlobalBounds().contains(playerSpriteComponent.getSprite().getPosition());

            if (collision) {
                ItemEffectsComponent itemEffectsComponent = (ItemEffectsComponent) entity.findComponent(ItemEffectsComponent.class);

                itemEffectsComponent.getItemEffects().parallelStream().forEach(itemEffect -> itemEffect.react(playerEntity));

                world.removeEntity(entity);
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }
}
