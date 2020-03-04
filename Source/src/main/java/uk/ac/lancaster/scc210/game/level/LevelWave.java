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

/**
 * The type Level wave.
 */
public class LevelWave {
    private final float ENTITY_GAP = 150f;

    private final Time SPAWN_TIMER = Time.getSeconds(0.5f);

    private final Time COUNT_START = Time.ZERO;

    private Set<Entity> entities;

    private final Wave wave;

    private final Vector2f spriteStart;

    private final Prototype prototype;

    private Time spawnTime, spawnCountUp, entitySize;

    private final int numShips;

    private int numLeftToSpawn;

    /**
     * Instantiates a new Level wave.
     *
     * @param wave        the wave
     * @param origin      the origin
     * @param destination the destination
     * @param numShips    the num ships
     * @param prototype   the prototype
     */
    public LevelWave(Wave wave, Vector2f origin, Vector2f destination, int numShips, Prototype prototype) {
        this.wave = wave;
        this.numShips = numShips;
        this.prototype = prototype;

        entities = new HashSet<>();

        spriteStart = origin;

        spawnTime = SPAWN_TIMER;

        spawnCountUp = COUNT_START;

        numLeftToSpawn = numShips;

        entitySize = Time.ZERO;
    }

    /**
     * Spawn new entity.
     *
     * @param deltaTime the delta time
     * @return the entity
     */
    Entity spawnNew(Time deltaTime) {
        Entity entity = null;

        // Create the initial ship - ignore the timer for the first one.
        if (numLeftToSpawn == numShips) {
            entity = create();

            entities.add(entity);

            numLeftToSpawn--;

            spawnCountUp = Time.add(spawnCountUp, deltaTime);

            return entity;
        }

        spawnCountUp = Time.add(spawnCountUp, deltaTime);

        spawnTime = Time.div(entitySize, ENTITY_GAP);

        if (spawnCountUp.asSeconds() >= spawnTime.asSeconds()) {
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

    /**
     * Remove.
     *
     * @param entity the entity
     */
    public void remove(Entity entity) {
        entities.remove(entity);
    }

    /**
     * All spawned boolean.
     *
     * @return the boolean
     */
    boolean allSpawned() {
        return numLeftToSpawn <= 0;
    }

    /**
     * Complete boolean.
     *
     * @return the boolean
     */
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

            // Express the size as some amount of time
            entitySize = Time.getSeconds(spriteComponent.getSprite().getTexture().getSize().x + 90);
        }

        if (entity.hasComponent(AsteroidComponent.class)) {
            AsteroidComponent asteroidComponent = (AsteroidComponent) entity.findComponent(AsteroidComponent.class);

            asteroidComponent.getCircle().setPosition(spriteStart);

            // Express the size as some amount of time
            entitySize =  Time.getSeconds(asteroidComponent.getCircle().getRadius() * 2);
        }

        return entity;
    }

    /**
     * Reset.
     */
    void reset() {
        entities.clear();

        spawnTime = SPAWN_TIMER;

        spawnCountUp = COUNT_START;

        numLeftToSpawn = numShips;
    }

    /**
     * Gets wave.
     *
     * @return the wave
     */
    Wave getWave() {
        return wave;
    }

    /**
     * Sets entities.
     *
     * @param entities the entities
     */
    public void setEntities(Set<Entity> entities) {
        this.entities = entities;
    }

    /**
     * Gets entities.
     *
     * @return the entities
     */
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
