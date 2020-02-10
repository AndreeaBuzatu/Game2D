package uk.ac.lancaster.scc210.game.level;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.ecs.component.WaveComponent;
import uk.ac.lancaster.scc210.game.prototypes.SpaceShipPrototype;
import uk.ac.lancaster.scc210.game.waves.Wave;

import java.util.HashSet;
import java.util.Set;

public class LevelWave {
    private final float SPAWN_TIMER = 0.5f;

    private final float COUNT_START = 0;

    private Set<Entity> entities;

    private final Wave wave;

    private final Vector2f origin, destination;

    private final SpaceShipPrototype spaceShip;

    private float spawnCountUp;
    private final float spawnTime;

    private final int numShips;

    private int numLeftToSpawn;

    public LevelWave(Wave wave, Vector2f origin, Vector2f destination, int numShips, SpaceShipPrototype spaceShip) {
        this.wave = wave;
        this.origin = origin;
        this.destination = destination;
        this.numShips = numShips;
        this.spaceShip = spaceShip;

        entities = new HashSet<>();

        numLeftToSpawn = numShips;

        spawnTime = SPAWN_TIMER;

        spawnCountUp = COUNT_START;
    }

    public Entity spawnNew(Time deltaTime) {
        // Create the initial ship - ignore the timer for the first one.
        if (numLeftToSpawn == numShips) {
            Entity entity = createShip();

            entities.add(entity);

            numLeftToSpawn--;

            return entity;
        }

        spawnCountUp += deltaTime.asSeconds();

        if (spawnCountUp >= spawnTime && !allSpawned()) {
            Entity entity = createShip();

            entities.add(entity);

            spawnCountUp = 0;

            numLeftToSpawn--;

            return entity;
        }

        return null;
    }

    public void remove(Entity entity) {
        entities.remove(entity);
    }

    private boolean allSpawned() {
        return numLeftToSpawn == 0;
    }

    boolean complete() {
        // All the spaceships are now dead
        return entities.isEmpty() && allSpawned();
    }

    private Entity createShip() {
        Entity entity = spaceShip.create();

        entity.addComponent(new WaveComponent(this));

        SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

        Sprite sprite = spriteComponent.getSprite();

        FloatRect localBounds = sprite.getLocalBounds();

        Vector2f centreMiddle = new Vector2f(localBounds.width / 2, localBounds.height / 2);

        sprite.setOrigin(centreMiddle);

        sprite.setPosition(Vector2f.sub(destination, origin));

        return entity;
    }

    public Wave getWave() {
        return wave;
    }

    public void setEntities(Set<Entity> entities) {
        this.entities = entities;
    }

    public Set<Entity> getEntities() {
        return entities;
    }
}
