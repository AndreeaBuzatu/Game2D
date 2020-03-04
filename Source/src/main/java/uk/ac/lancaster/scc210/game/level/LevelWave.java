package uk.ac.lancaster.scc210.game.level;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.prototypes.Prototype;
import uk.ac.lancaster.scc210.game.ecs.component.*;
import uk.ac.lancaster.scc210.game.waves.Wave;

import java.util.HashSet;
import java.util.Set;

public class LevelWave {
    private final float SPAWN_TIMER = 0.5f;

    private final float COUNT_START = 0;

    private Set<Entity> entities;

    private final Wave wave;

    private final Vector2f spriteStart;

    private final Prototype prototype;

    private float spawnTime;

    private final int numShips;

    private float spawnCountUp;

    private int numLeftToSpawn, entitySize;

    public LevelWave(Wave wave, Vector2f origin, Vector2f destination, int numShips, Prototype prototype) {
        this.wave = wave;
        this.numShips = numShips;
        this.prototype = prototype;

        entities = new HashSet<>();

        spriteStart = origin;

        spawnTime = SPAWN_TIMER;

        spawnCountUp = COUNT_START;

        numLeftToSpawn = numShips;
    }

    Entity spawnNew(Time deltaTime) {
        Entity entity = null;

        // Create the initial ship - ignore the timer for the first one.
        if (numLeftToSpawn == numShips) {
            entity = create();

            entities.add(entity);

            numLeftToSpawn--;
        }

        spawnCountUp += deltaTime.asSeconds();

        spawnTime = entitySize / 150f;

        if (spawnCountUp >= spawnTime) {
            if (!allSpawned()) {
                entity = create();

                entities.add(entity);

                numLeftToSpawn--;

            } else if (!wave.getToRespawn().isEmpty()) {
                entity = wave.getToRespawn().remove();

                TransformableComponent transformableComponent = (TransformableComponent) entity.findComponent(TransformableComponent.class);

                transformableComponent.getTransformable().setPosition(wave.getOrigin());

                entities.add(entity);
            }

            spawnCountUp = COUNT_START;
        }

        return entity;
    }

    public void remove(Entity entity) {
        entities.remove(entity);
    }

    boolean allSpawned() {
        return numLeftToSpawn <= 0;
    }

    boolean complete() {
        // All the spaceships are now dead
        return entities.isEmpty() && allSpawned();
    }

    private Entity create() {
        Entity entity = prototype.create();

        entity.addComponent(new WaveComponent(this));

        entity.addComponent(new EnemyComponent());

        if (entity.hasComponent(SpriteComponent.class)) {
            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            Sprite sprite = spriteComponent.getSprite();

            positionSprite(sprite);

            entitySize = spriteComponent.getSprite().getTexture().getSize().x + 90;
        }

        if (entity.hasComponent(AsteroidComponent.class)) {
            AsteroidComponent asteroidComponent = (AsteroidComponent) entity.findComponent(AsteroidComponent.class);

            asteroidComponent.getCircle().setPosition(spriteStart);

            entitySize = (int) (2 * asteroidComponent.getCircle().getRadius());
        }

        return entity;
    }

    void reset() {
        entities.clear();

        spawnTime = SPAWN_TIMER;

        spawnCountUp = COUNT_START;

        numLeftToSpawn = numShips;
    }

    Wave getWave() {
        return wave;
    }

    public void setEntities(Set<Entity> entities) {
        this.entities = entities;
    }

    public Set<Entity> getEntities() {
        return entities;
    }

    private void positionSprite(Sprite sprite) {
        FloatRect localBounds = sprite.getLocalBounds();

        Vector2f centreMiddle = new Vector2f(localBounds.width / 2, localBounds.height / 2);

        sprite.setOrigin(centreMiddle);

        sprite.setPosition(spriteStart);
    }
}
