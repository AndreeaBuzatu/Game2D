package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.collision.OrientatedBox;
import uk.ac.lancaster.scc210.engine.content.SoundManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.ItemEffectsComponent;
import uk.ac.lancaster.scc210.game.ecs.component.OrientatedBoxComponent;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;
import uk.ac.lancaster.scc210.game.ecs.entity.PlayerFinder;

import java.util.Collection;

public class ItemCollisionSystem extends IterativeSystem {
    // We only have one player but the abstraction works like so - oh well
    private Entity player;


    private final SoundManager soundManager;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public ItemCollisionSystem(World world) {
        super(world, ItemEffectsComponent.class);

        player = PlayerFinder.findPlayer(world);

        soundManager = (SoundManager) world.getServiceProvider().get(SoundManager.class);
    }

    @Override
    public void entityAdded(Entity entity) {
        entities = world.getEntitiesFor(ItemEffectsComponent.class);

        if (player == null) {
            player = PlayerFinder.findPlayer(world);
        }
    }

    @Override
    public void entitiesAdded(Collection<? extends Entity> entities) {
        this.entities = world.getEntitiesFor(ItemEffectsComponent.class);

        if (player == null) {
            player = PlayerFinder.findPlayer(world);
        }
    }

    @Override
    public void entityRemoved(Entity entity) {

    }

    @Override
    public void update(Time deltaTime) {
        // Abort if player can't be found
        if (player == null) {
            return;
        }

        OrientatedBoxComponent playerOrientedBox = (OrientatedBoxComponent) player.findComponent(OrientatedBoxComponent.class);

        for (Entity entity : entities) {
            OrientatedBoxComponent itemOrientedBox = (OrientatedBoxComponent) entity.findComponent(OrientatedBoxComponent.class);

            boolean colliding = OrientatedBox.areColliding(itemOrientedBox.getOrientatedBox(), playerOrientedBox.getOrientatedBox());

            if (colliding) {
                ItemEffectsComponent itemEffectsComponent = (ItemEffectsComponent) entity.findComponent(ItemEffectsComponent.class);

                PlayerComponent playerComponent = (PlayerComponent) player.findComponent(PlayerComponent.class);

                itemEffectsComponent.getItemEffects().parallelStream().forEach(itemEffect -> itemEffect.react(player));

                playerComponent.setCurrentItemEffects(itemEffectsComponent.getItemEffects());

                soundManager.playSound("pickup");

                world.removeEntity(entity);
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }
}
