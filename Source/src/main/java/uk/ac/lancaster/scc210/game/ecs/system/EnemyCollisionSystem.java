package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.collision.Cell;
import uk.ac.lancaster.scc210.engine.collision.UniformGrid;
import uk.ac.lancaster.scc210.engine.content.SoundManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Enemy collision system.
 */
public class EnemyCollisionSystem extends IterativeSystem {
    private final Time COLLISION_GAP = Time.getSeconds(1);

    private final SoundManager soundManager;

    private final UniformGrid uniformGrid;

    private Time elapsedTime;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public EnemyCollisionSystem(World world) {
        super(world, EnemyComponent.class);

        soundManager = (SoundManager) world.getServiceProvider().get(SoundManager.class);

        uniformGrid = (UniformGrid) world.getServiceProvider().get(UniformGrid.class);

        elapsedTime = Time.ZERO;
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
        elapsedTime = Time.add(elapsedTime, deltaTime);

        List<Cell> cells = uniformGrid.getGrid().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        for (Cell cell : cells) {
            if (cell.getEntities().isEmpty()) {
                continue;
            }

            Set<Set<Entity>> collided = cell.checkCollision();

            for (Set<Entity> collision : collided) {
                if (collision == null) {
                    continue;
                }

                Entity player = findPlayer(collision);

                Entity enemyShip = findEnemyShip(collision);

                if ((player != null && enemyShip != null) && elapsedTime.asSeconds() > COLLISION_GAP.asSeconds()) {
                    SpaceShipComponent spaceShipComponent = (SpaceShipComponent) player.findComponent(SpaceShipComponent.class);

                    FlashComponent flashComponent = (FlashComponent) player.findComponent(FlashComponent.class);

                    LivesComponent livesComponent = (LivesComponent) player.findComponent(LivesComponent.class);

                    soundManager.playSound(spaceShipComponent.getHitSound());

                    elapsedTime = Time.ZERO;

                    flashComponent.flash(deltaTime);

                    livesComponent.setLives(livesComponent.getLives() - 1);
                }
            }
        }
    }

    private Entity findPlayer(Set<Entity> collided) {
        return collided.stream()
                .filter(entity -> entity.hasComponent(PlayerComponent.class))
                .findFirst().orElse(null);
    }

    private Entity findEnemyShip(Set<Entity> collided) {
        return collided.stream()
                .filter(entity -> entity.hasComponent(EnemyComponent.class) && entity.hasComponent(FlashComponent.class))
                .findFirst().orElse(null);
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
