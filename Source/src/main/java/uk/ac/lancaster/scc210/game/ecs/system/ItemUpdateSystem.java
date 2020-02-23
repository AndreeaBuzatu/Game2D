package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;
import uk.ac.lancaster.scc210.game.items.ItemEffect;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemUpdateSystem extends IterativeSystem {
    private Entity playerEntity;

    private PlayerComponent playerComponent;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public ItemUpdateSystem(World world) {
        super(world, PlayerComponent.class);
    }

    @Override
    public void entityAdded(Entity entity) {
        super.entityAdded(entity);

        Optional<Entity> player = world.getEntitiesFor(PlayerComponent.class).stream().findFirst();

        player.ifPresent(opt -> {
            playerEntity = opt;

            playerComponent = (PlayerComponent) playerEntity.findComponent(PlayerComponent.class);
        });
    }

    /**
     * Called World.update(). Should be used to update the entities.
     *
     * @param deltaTime deltaTime to update
     */
    @Override
    public void update(Time deltaTime) {
        if (playerComponent.getCurrentEffects().isEmpty()) {
            return;
        }

        List<ItemEffect> itemEffects = playerComponent.getCurrentEffects().stream()
                .filter(ItemEffect::isDead)
                .collect(Collectors.toList());

        itemEffects.forEach(item -> item.reset(playerEntity));

        playerComponent.getCurrentEffects().removeAll(itemEffects);

        playerComponent.getCurrentEffects().forEach(item -> item.update(deltaTime));
    }

    /**
     * Called in World.draw(). Should be used to draw the entities.
     *
     * @param target the surface to draw on, usually a RenderWindow
     */
    @Override
    public void draw(RenderTarget target) {

    }
}
