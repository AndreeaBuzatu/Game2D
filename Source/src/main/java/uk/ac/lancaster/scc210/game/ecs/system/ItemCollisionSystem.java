package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.collision.OrientatedBox;
import uk.ac.lancaster.scc210.engine.content.SoundBufferManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.ItemEffectsComponent;
import uk.ac.lancaster.scc210.game.ecs.component.OrientatedBoxComponent;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;

import java.util.Optional;

public class ItemCollisionSystem extends IterativeSystem {
    // We only have one player but the abstraction works like so - oh well
    private Optional<Entity> player;

    private final Sound pickupSound;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public ItemCollisionSystem(World world) {
        super(world, ItemEffectsComponent.class);

        player = world.getEntitiesFor(PlayerComponent.class).stream().findFirst();

        SoundBufferManager soundBufferManager = (SoundBufferManager) world.getServiceProvider().get(SoundBufferManager.class);

        SoundBuffer soundBuffer = soundBufferManager.get("pickup");

        pickupSound = new Sound(soundBuffer);
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

        OrientatedBoxComponent playerOrientedBox = (OrientatedBoxComponent) playerEntity.findComponent(OrientatedBoxComponent.class);

        for (Entity entity : entities) {
            OrientatedBoxComponent itemOrientedBox = (OrientatedBoxComponent) entity.findComponent(OrientatedBoxComponent.class);

            boolean colliding = OrientatedBox.areColliding(itemOrientedBox.getOrientatedBox(), playerOrientedBox.getOrientatedBox());

            if (colliding) {
                ItemEffectsComponent itemEffectsComponent = (ItemEffectsComponent) entity.findComponent(ItemEffectsComponent.class);

                PlayerComponent playerComponent = (PlayerComponent) playerEntity.findComponent(PlayerComponent.class);

                itemEffectsComponent.getItemEffects().parallelStream().forEach(itemEffect -> itemEffect.react(playerEntity));

                playerComponent.setCurrentItemEffects(itemEffectsComponent.getItemEffects());

                SoundBufferManager.playSound(pickupSound);

                world.removeEntity(entity);
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }
}
