package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.collision.Cell;
import uk.ac.lancaster.scc210.engine.collision.UniformGrid;
import uk.ac.lancaster.scc210.engine.content.SoundManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.component.PooledComponent;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Bullet collision system.
 */
public class BulletCollisionSystem extends IterativeSystem {
    private final SoundManager soundManager;

    private final UniformGrid uniformGrid;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public BulletCollisionSystem(World world) {
        super(world);

        soundManager = (SoundManager) world.getServiceProvider().get(SoundManager.class);

        uniformGrid = (UniformGrid) world.getServiceProvider().get(UniformGrid.class);
    }

    @Override
    public void entityAdded(Entity entity) {
    }

    @Override
    public void entitiesAdded(Collection<? extends Entity> entities) {
    }

    @Override
    public void entityRemoved(Entity entity) {

    }

    @Override
    public void update(Time deltaTime) {
        List<Cell> cells = uniformGrid.getGrid().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        for (Cell cell : cells) {
            if (cell.getEntities().isEmpty()) {
                continue;
            }

            for (Set<Entity> collision : cell.checkCollision()) {
                if (collision == null) {
                    continue;
                }

                Entity bullet = findBulletEntity(collision);

                Entity flashable = findFlashableEntity(collision);

                if (bullet != null && flashable != null) {
                    boolean isItem = flashable.hasComponent(ItemEffectsComponent.class);

                    Entity bulletCreator = ((BulletComponent) bullet.findComponent(BulletComponent.class)).getCreator();

                    // Enemies are impervious to each others bullets
                    boolean bothEnemies = bulletCreator.hasComponent(EnemyComponent.class) && flashable.hasComponent(EnemyComponent.class);

                    // Just in case the bullet creator hits the current entity. (Compare references).
                    boolean sameEntity = bulletCreator == flashable;

                    if (isItem || bothEnemies || sameEntity) {
                        continue;
                    }

                    PooledComponent pooledComponent = (PooledComponent) bullet.findComponent(PooledComponent.class);

                    FlashComponent flashComponent = (FlashComponent) flashable.findComponent(FlashComponent.class);

                    flashComponent.flash(deltaTime);

                    if (bulletCreator.hasComponent(PlayerComponent.class)) {
                        PlayerComponent playerComponent = (PlayerComponent) bulletCreator.findComponent(PlayerComponent.class);

                        playerComponent.getBulletEffect().react(flashable);

                    } else if (bulletCreator.hasComponent(SpaceShipComponent.class)) {
                        SpaceShipComponent spaceShipComponent = (SpaceShipComponent) bulletCreator.findComponent(SpaceShipComponent.class);

                        spaceShipComponent.getBulletEffect().react(flashable);
                    }

                    if (flashable.hasComponent(SpaceShipComponent.class)) {
                        SpaceShipComponent spaceShipComponent = (SpaceShipComponent) flashable.findComponent(SpaceShipComponent.class);

                        soundManager.playSound(spaceShipComponent.getHitSound());
                    }

                    // Return the bullet back to the pool and remove it from the world
                    world.getPool(pooledComponent.getPoolClass()).returnEntity(bullet);

                    world.removeEntity(bullet);

                    uniformGrid.removeEntity(bullet);
                }
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }

    private Entity findBulletEntity(Set<Entity> entities) {
        return entities.stream()
                .filter(entity -> entity.hasComponent(BulletComponent.class))
                .findFirst().orElse(null);
    }

    private Entity findFlashableEntity(Set<Entity> entities) {
        return entities.stream()
                .filter(entity -> entity.hasComponent(FlashComponent.class))
                .findFirst().orElse(null);
    }
}
