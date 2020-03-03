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

        for (List<Cell> cells : uniformGrid.getGrid()) {
            for (Cell cell : cells) {
                if (cell.getEntities().isEmpty()) {
                    continue;
                }

                List<Entity[]> collided = cell.checkCollision();

               for (Entity[] collision : collided) {
                    if (collision == null) {
                        continue;
                    }

                    Entity player = findPlayer(collision);

                    Entity enemyShip = findEnemyShip(collision);

                    if ((player != null && enemyShip != null) && elapsedTime.asSeconds() > COLLISION_GAP.asSeconds()) {
                        SpaceShipComponent spaceShipComponent = (SpaceShipComponent) player.findComponent(SpaceShipComponent.class);

                        LivesComponent livesComponent = (LivesComponent) player.findComponent(LivesComponent.class);

                        soundManager.playSound(spaceShipComponent.getHitSound());

                        elapsedTime = Time.ZERO;

                        livesComponent.setLives(livesComponent.getLives() - 1);
                    }
                }
            }
        }
    }

    private Entity findPlayer(Entity[] collided) {
        if (collided[0].hasComponent(PlayerComponent.class)) {
            return collided[0];

        } else if (collided[1].hasComponent(PlayerComponent.class)) {
            return collided[1];
        }

        return null;
    }

    private Entity findEnemyShip(Entity[] collided) {
        if (collided[0].hasComponent(EnemyComponent.class)) {
            return collided[0];

        } else if (collided[1].hasComponent(EnemyComponent.class)) {
            return collided[1];
        }

        return null;
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
