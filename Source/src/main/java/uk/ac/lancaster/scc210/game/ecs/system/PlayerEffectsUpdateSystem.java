package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.bullets.effects.BulletEffect;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;
import uk.ac.lancaster.scc210.game.ecs.entity.PlayerFinder;
import uk.ac.lancaster.scc210.game.items.ItemEffect;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlayerEffectsUpdateSystem extends IterativeSystem {
    private Entity player;

    private PlayerComponent playerComponent;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public PlayerEffectsUpdateSystem(World world) {
        super(world, PlayerComponent.class);
    }

    @Override
    public void entityAdded(Entity entity) {
        if (player == null) {
            player = PlayerFinder.findPlayer(world);

            playerComponent = (PlayerComponent) player.findComponent(PlayerComponent.class);
        }
    }

    @Override
    public void entitiesAdded(Collection<? extends Entity> entities) {
        if (player == null) {
            player = PlayerFinder.findPlayer(world);

            playerComponent = (PlayerComponent) player.findComponent(PlayerComponent.class);
        }
    }

    @Override
    public void entityRemoved(Entity entity) {
    }

    /**
     * Called World.update(). Should be used to update the entities.
     *
     * @param deltaTime deltaTime to update
     */
    @Override
    public void update(Time deltaTime) {
        if (player == null) {
            return;
        }

        updateBulletEffect(deltaTime);

        if (playerComponent.getCurrentItemEffects().isEmpty()) {
            return;
        }

        List<ItemEffect> itemEffects = playerComponent.getCurrentItemEffects().stream()
                .filter(ItemEffect::isDead)
                .collect(Collectors.toList());

        itemEffects.forEach(item -> item.reset(player));

        playerComponent.getCurrentItemEffects().removeAll(itemEffects);

        playerComponent.getCurrentItemEffects().forEach(item -> item.update(deltaTime));
    }

    private void updateBulletEffect(Time deltaTime) {
        BulletEffect bulletEffect = playerComponent.getBulletEffect();

        if (bulletEffect.isDead()) {
            bulletEffect.reset();

            playerComponent.setBulletEffectToDefault();

        } else {
            bulletEffect.update(deltaTime);
        }
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
