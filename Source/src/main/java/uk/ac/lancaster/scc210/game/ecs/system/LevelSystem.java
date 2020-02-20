package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.*;
import uk.ac.lancaster.scc210.game.level.Level;
import uk.ac.lancaster.scc210.game.level.LevelStage;

import java.util.Optional;

public class LevelSystem extends IterativeSystem {
    private Optional<Entity> player;

    private Level level;

    private LevelStage currentStage;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public LevelSystem(World world, Level level) {
        super(world);

        this.level = level;

        currentStage = level.getCurrentStage();

        world.addEntities(currentStage.getStationaryEntities());

        player = world.getEntitiesFor(PlayerComponent.class).stream().findFirst();
    }

    @Override
    public void entityRemoved(Entity entity) {
        if (entity.hasComponent(PlayerComponent.class)) {
            LivesComponent livesComponent = (LivesComponent) entity.findComponent(LivesComponent.class);

            if (livesComponent.isDead()) {
                handlePlayerDeath(entity);
            }
        }

        if (entity.hasComponent(WaveComponent.class)) {
            WaveComponent waveComponent = (WaveComponent) entity.findComponent(WaveComponent.class);

            waveComponent.getWave().remove(entity);
        }

        // Remove a stationary entity from the List<Entity> stationaryEntities
        if (entity.hasComponent(StationaryComponent.class)) {
            currentStage.removeStationaryEntity(entity);
        }
    }

    @Override
    public void entityAdded(Entity entity) {
        super.entityAdded(entity);

        player = world.getEntitiesFor(PlayerComponent.class).stream().findFirst();
    }

    @Override
    public void update(Time deltaTime) {
        if (currentStage != null && currentStage.complete()) {
            currentStage = level.changeStage();

        } else {
            if (currentStage != null) {
                currentStage.spawn(world, deltaTime);
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }

    private void handlePlayerDeath(Entity player) {
        LivesComponent livesComponent = (LivesComponent) player.findComponent(LivesComponent.class);

        PlayerComponent playerComponent = (PlayerComponent) player.findComponent(PlayerComponent.class);

        SpriteComponent spriteComponent = (SpriteComponent) player.findComponent(SpriteComponent.class);

        FlashComponent flashComponent = (FlashComponent) player.findComponent(FlashComponent.class);

        world.removeEntities(world.getEntitiesFor(WaveComponent.class));

        world.removeEntities(world.getEntitiesFor(BulletComponent.class));

        // When we remove spaceships there's a % chance they drop items, so clear them again
        world.removeEntities(world.getEntitiesFor(ItemEffectsComponent.class));

        livesComponent.resurrect();

        playerComponent.setScore(0);

        // Stop the player from flashing
        flashComponent.resetToTexture();

        spriteComponent.getSprite().setPosition(playerComponent.getSpawnPoint());

        spriteComponent.getSprite().setRotation(0);

        // Reset item effects
        playerComponent.getCurrentEffects().stream().forEach(itemEffect -> itemEffect.reset(player));

        world.addEntity(player);

        level.reset();
    }

    public void setLevel(Level level) {
        this.level = level;

        currentStage = level.getCurrentStage();
    }
}
